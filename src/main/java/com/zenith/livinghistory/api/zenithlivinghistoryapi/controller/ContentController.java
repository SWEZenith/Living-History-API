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
    public List<Content> getAll() {
        return this.contentRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Content get(@PathVariable("id") String id) {
        return contentRepository.findOne(id);
    }
}
