package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.AnnotationRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.ContentRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Annotation;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Content;
import org.springframework.http.HttpStatus;
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

    public AnnotationController(AnnotationRepository annotationRepository, ContentRepository contentRepository) {
        this.annotationRepository = annotationRepository;
        this.contentRepository = contentRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Annotation> create(@RequestBody @Valid Annotation annotation) {
        Annotation savedAnnotation = annotationRepository.save(annotation);
        Content content = contentRepository.findOne(annotation.getTarget().getId());
        List<Annotation> annotations = content.getAnnotations();
        if (annotations == null) {
            annotations = new ArrayList<>();
        }
        annotations.add(savedAnnotation);
        content.setAnnotations(annotations);
        contentRepository.save(content);
        return new ResponseEntity<>(annotation, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Annotation> getAll() {
        return this.annotationRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Annotation get(@PathVariable("id") String id) {
        return annotationRepository.findOne(id);
    }
}
