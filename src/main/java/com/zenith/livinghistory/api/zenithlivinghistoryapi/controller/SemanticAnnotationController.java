package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.common.LRUCache;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.common.SparQL.Queries;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.common.SparQL.SparQLExecutor;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.AnnotationRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.ContentRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Annotation;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Content;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/semantic/")
public class SemanticAnnotationController {

    //region Private Members

    private AnnotationRepository annotationRepository;

    private ContentRepository contentRepository;

    private static LRUCache<String, JsonObject> propertyRepository = new LRUCache<>(3);

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

        boolean isCity = this.isCity(annotation.getBody().getId());
        String[] tags = content.getTags();

        if(isCity) {

        } else {

        }
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
