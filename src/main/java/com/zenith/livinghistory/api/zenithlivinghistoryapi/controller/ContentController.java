package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.ContentRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Content;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/contents")
public class ContentController {
    private ContentRepository contentRepository;

    public ContentController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Content> create(@RequestBody Content content) {
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
}
