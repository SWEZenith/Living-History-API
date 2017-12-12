package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.common.ReverseGeocodingProvider;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.AnnotationRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.ContentRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Annotation;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Content;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.LocationBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/contents")
public class ContentController {

    private ContentRepository contentRepository;
    private AnnotationRepository annotationRepository;

    public ContentController(ContentRepository contentRepository, AnnotationRepository annotationRepository) {
        this.contentRepository = contentRepository;
        this.annotationRepository = annotationRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Content> create(@RequestBody Content content) {

        LocationBody locationBody = content.getLocation();
        if (locationBody != null && locationBody.getLatitude() != 0 && locationBody.getLongitude() != 0) {
            ReverseGeocodingProvider geocodingProvider = new ReverseGeocodingProvider();
            List<String> placeNames = geocodingProvider.getPlaceNamesofLocation(
                    locationBody.getLatitude(),
                    locationBody.getLongitude());

            if(placeNames.size() > 0) {

                String[] placeNamesArr = new String[placeNames.size()];
                placeNamesArr = placeNames.toArray(placeNamesArr);
                locationBody.setTags(placeNamesArr);
            }
        }

		contentRepository.insert(content);
        return new ResponseEntity<>(content, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Content>> getAll() {
        return new ResponseEntity<>(this.contentRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Content> get(@PathVariable("id") String id) {
        return new ResponseEntity<>(contentRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{keyword}")
    public ResponseEntity<List<Content>> searchContent(@PathVariable("keyword") String keyword) {


        List<Annotation> annotations = annotationRepository.findAnnotationsByKeyword(keyword);
        List<Content> contents = contentRepository.findContentByKeyword(keyword);

        if (annotations.size() > 0) {

            for (int i = 0; i < annotations.size(); i++) {

                String[] parts = annotations.get(i).getTarget().getId().split("/");
                String storyIdentifier = parts[parts.length-1].split("#")[0];

                Content content = contentRepository.findContentByStoryItemId(storyIdentifier);
                boolean doesContentAlreadyExist = contents.stream().anyMatch(c -> c.getId().equals(content.getId()));

                if(doesContentAlreadyExist == false)
                    contents.add(content);
            }
        }

        return new ResponseEntity<>(contents, HttpStatus.OK);
    }
}
