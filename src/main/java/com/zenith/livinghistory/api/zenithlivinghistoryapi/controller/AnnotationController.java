package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model.AnnotationModel;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model.ContentModel;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.AnnotationRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.ContentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/annotations")
public class AnnotationController {

    private AnnotationRepository annotationRepository;
    private ContentRepository contentRepository;

    public AnnotationController(AnnotationRepository annotationRepository, ContentRepository contentRepository) {
        this.annotationRepository = annotationRepository;
        this.contentRepository = contentRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AnnotationModel> create(@RequestBody @Valid AnnotationModel annotation) {
        ContentModel content = contentRepository.findOne(annotation.getTarget().getId());
        List<AnnotationModel> annotations = content.getAnnotations();
        annotations.add(annotation);
        content.setAnnotations(annotations);
        contentRepository.save(content);
        return new ResponseEntity<>(annotation, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AnnotationModel> getAll() {
        return this.annotationRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public AnnotationModel get(@PathVariable("id") String id) {
        return annotationRepository.findOne(id);
    }
}
