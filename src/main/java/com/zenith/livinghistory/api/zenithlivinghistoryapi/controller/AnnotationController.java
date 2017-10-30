package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.AnnotationRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Annotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/annotations/")
public class AnnotationController {
    private AnnotationRepository annotationRepository;

    public AnnotationController(AnnotationRepository annotationRepository) {
        this.annotationRepository = annotationRepository;
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/")
//    public ResponseEntity<Void> create(@RequestBody Annotation annotation) {
//        Integer index = this.annotations.size();
//        annotation.setId("http://localhost:8080/api/v1/annotations/" + ++index);
//
//        annotations.add(annotation);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Allow", "PUT,GET,OPTIONS,HEAD,DELETE,PATCH");
//        headers.add("Location", "http://example.org/annotations/anno1");
//        headers.add("Content-Type", "application/ld+json; profile=\"http://www.w3.org/ns/anno.jsonld\"");
//
//        return new ResponseEntity(annotation, headers, HttpStatus.CREATED);
//    }

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
}
