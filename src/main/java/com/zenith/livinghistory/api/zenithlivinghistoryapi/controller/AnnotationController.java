package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model.AnnotationModel;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model.ContentModel;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.AnnotationRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.ContentRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.request.AnnotationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/annotations")
public class AnnotationController {

    private AnnotationRepository annotationRepository;
    private ContentRepository contentRepository;

    @Autowired
    public AnnotationController(AnnotationRepository annotationRepository, ContentRepository contentRepository) {
        this.annotationRepository = annotationRepository;
        this.contentRepository = contentRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    // TODO: do not return model, return dto instead
    public ResponseEntity<AnnotationModel> create(@RequestBody @Valid AnnotationModel annotation) {
        // TODO: get dto instead of model
        AnnotationModel savedAnnotation = annotationRepository.save(annotation);
        ContentModel content = contentRepository.findOne(annotation.getContent().getId());
        List<AnnotationModel> annotations = content.getAnnotations();
        if (annotations == null) {
            annotations = new ArrayList<>();
        }
        annotations.add(savedAnnotation);
        content.setAnnotations(annotations);
        contentRepository.save(content);
        return new ResponseEntity<>(annotation, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid AnnotationRequest annotationRequest) {

        AnnotationModel model = new AnnotationModel();

        ContentModel content = contentRepository.findFirstByJsonLdContentId(annotationRequest.getId());
        model.setContent(content);

        model.setBody(annotationRequest.getBody());


        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    // TODO: do not return model return dto instead
    public List<AnnotationModel> getAll() {
        return this.annotationRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    // TODO: id might be string?
    public ResponseEntity<AnnotationModel> get(@PathVariable("id") long id) {
        return new ResponseEntity<>(annotationRepository.findOne(id), HttpStatus.OK);
    }
}
