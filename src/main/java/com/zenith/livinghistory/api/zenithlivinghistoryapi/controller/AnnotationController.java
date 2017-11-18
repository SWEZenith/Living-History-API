package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.AnnotationRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.ContentRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Annotation;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Content;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
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
        Content content = contentRepository.findOne(annotation.getTarget().getId());
        List<Annotation> annotations = content.getAnnotations();
        annotations.add(annotation);
        content.setAnnotations(annotations);
        contentRepository.save(content);
        return new ResponseEntity<>(annotation, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Annotation>> getAll() {
        return new ResponseEntity<>(this.annotationRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Annotation> get(@PathVariable("id") String id) {
        return new ResponseEntity<>(annotationRepository.findOne(id), HttpStatus.OK);
    }
}
