package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.AnnotationRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Annotation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/annotations/")
public class AnnotationController {

    private AnnotationRepository annotationRepository;

    public AnnotationController(AnnotationRepository annotationRepository) {
        this.annotationRepository = annotationRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<Annotation> create(@RequestBody @Valid Annotation annotation) {
        annotationRepository.insert(annotation);
        return new ResponseEntity<>(annotation, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<Annotation> getAll() {
        return this.annotationRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Annotation get(@PathVariable("id") String id) {
        return annotationRepository.findOne(id);
    }

    /* GetAnnotationsByContentID eklenecek */
}
