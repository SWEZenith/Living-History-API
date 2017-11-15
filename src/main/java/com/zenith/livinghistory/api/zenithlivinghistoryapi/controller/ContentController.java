package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model.ContentModel;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.ContentRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Content;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/contents/")
public class ContentController {
    private ContentRepository contentRepository;

    public ContentController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/")
//    public ResponseEntity<Void> create(@RequestBody Content content) {
//        Integer index = this.content.size();
//        annotation.setId("http://localhost:8080/api/v1/content/" + ++index);
//
//        content.add(annotation);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Allow", "PUT,GET,OPTIONS,HEAD,DELETE,PATCH");
//        headers.add("Location", "http://example.org/content/anno1");
//        headers.add("Content-Type", "application/ld+json; profile=\"http://www.w3.org/ns/anno.jsonld\"");
//
//        return new ResponseEntity(content, headers, HttpStatus.CREATED);
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    // TODO: get dto instead of model
    public ResponseEntity<ContentModel> create(@RequestBody ContentModel content) {
		contentRepository.save(content);
        return new ResponseEntity<>(content, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    // TODO: return responseEntity
    public List<ContentModel> getAll() {
        return this.contentRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    // todo: id might be string
    public ContentModel get(@PathVariable("id") Long id) {
        return contentRepository.findOne(id);
    }
}
