package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.common.LRUCache;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.common.SparQL.DTO.QueryProperty;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.common.SparQL.PropertyRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.common.SparQL.Queries;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.common.SparQL.SparQLExecutor;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.common.SparQL.enums.IRITypes;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.AnnotationRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.ContentRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Annotation;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Content;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/semantic/")
public class SemanticAnnotationController {

    //region Private Members

    private AnnotationRepository annotationRepository;

    private ContentRepository contentRepository;

    private static LRUCache<String, JsonObject> propertyRepository = new LRUCache<>(10);

    //endregion

    //region Constructors

    /**
     * Ctor.
     * @param annotationRepository - Annotation Repository.
     */
    public SemanticAnnotationController(AnnotationRepository annotationRepository, ContentRepository contentRepository) {
        this.annotationRepository = annotationRepository;
        this.contentRepository = contentRepository;
    }

    //endregion

    //region Private Methods

    private boolean isCity(String iri) {

        String queryString = String.format(Queries.isCity, iri);
        SparQLExecutor executor = new SparQLExecutor();
        JsonArray response = new JsonArray();

        boolean isCity = false;

        try {
            response = executor.execute(queryString);

            if (response.size() > 0)
                isCity = true;

        } catch (IOException e) {
            e.printStackTrace();
        }


        return isCity;
    }

    private JsonObject getCityProperties(String iri) {

        String queryString = String.format(Queries.getCityProperties, iri);
        SparQLExecutor executor = new SparQLExecutor();
        JsonObject city = new JsonObject();

        try {
            JsonArray response = executor.execute(queryString);
            if (response.size() > 0)
                city = response.get(0).getAsJsonObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return city;
    }

    private JsonObject getIndividualProperties(String iri) {

        String queryString = String.format(Queries.getIndividualProperties, iri);
        SparQLExecutor executor = new SparQLExecutor();
        JsonObject individual = new JsonObject();

        try {
            JsonArray results = executor.execute(queryString);
            if (results.size() > 0)
                individual = results.get(0).getAsJsonObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return individual;
    }

    private void createContentTagsImplicitly(Content content, Annotation annotation) {

        String iri = annotation.getBody().getId();
        List<String> existingTags = new ArrayList<>(Arrays.asList(content.getTags()));

        IRITypes iriType = this.isCity(iri) ? IRITypes.City : IRITypes.Individual;
        List<String> newTags = getTagsOfIRI(iri, iriType);

        for (String tag : newTags) {

            if (!existingTags.contains(tag))
                existingTags.add(tag);
        }

        content.setTags(existingTags.toArray(new String[existingTags.size()]));
        contentRepository.save(content);
    }

    private List<String> getTagsOfIRI(String iri, IRITypes iriType) {

        List<String> tags = new ArrayList<>();

        for (QueryProperty property : PropertyRepository.getProperties(iriType)) {

            String queryString = String.format(
                    Queries.getTags,
                    property.isDirect() ? "?value" : "?label",
                    iri,
                    property.getName(),
                    property.isDirect()
                            ? Queries.langFilter
                            : (String.format(Queries.subQuery, property.getValueProperty()))
            );

            SparQLExecutor executor = new SparQLExecutor();

            try {
                JsonArray results = executor.execute(queryString);

                for (int i = 0; i < results.size(); i++){

                    JsonObject item = results.get(i).getAsJsonObject();

                    if (property.isDirect() && item.get("value") != null)
                        tags.add(item.get("value").getAsJsonObject().get("value").getAsString());
                    else if(!property.isDirect() && item.get("label") != null)
                        tags.add(item.get("label").getAsJsonObject().get("value").getAsString());
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return tags;
    }

    //endregion

    //region Public Methods

    @RequestMapping(method = RequestMethod.GET, value = "/entities/{keyword}")
    public ResponseEntity<Object> getSemanticBodies(@PathVariable("keyword") String keyword){

        String queryString = String.format(Queries.semanticBody, keyword);
        SparQLExecutor executor = new SparQLExecutor();
        JsonArray response = new JsonArray();

        try {
            response = executor.execute(queryString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> post(@RequestBody @Valid Annotation annotation) {

        String[] parts = annotation.getTarget().getId().split("/");
        String targetId = parts[parts.length - 1].split("#")[0];

        Content content = contentRepository.findContentByStoryItemId(targetId);
        List<Annotation> annotations = content.getAnnotations();
        annotations.add(annotation);
        content.setAnnotations(annotations);
        contentRepository.save(content);

        new Thread(() -> { this.createContentTagsImplicitly(content, annotation); }).start();

        return new ResponseEntity<>(annotation, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/properties")
    public ResponseEntity<Object> getBodyProperties(@RequestBody  String body) {

        String iri = new JsonParser().parse(body).getAsJsonObject().get("iri").getAsString();
        JsonObject response = propertyRepository.get(iri);

        if (response == null) {

            boolean isCity = this.isCity(iri);

            if (isCity)
                response = getCityProperties(iri);
            else
                response = getIndividualProperties(iri);

            response.addProperty("type", (isCity ? "City" : "Person"));
            propertyRepository.put(iri, response);
        }

        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    //endregion
}
