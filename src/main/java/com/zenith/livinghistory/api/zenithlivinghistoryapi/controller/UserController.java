package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.ContentRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Content;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.request.ContentsByCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private ContentRepository contentRepository;

    public UserController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{username}/contents")
    public List<Content> get(@PathVariable String username) {
        return contentRepository.findContentsByUsername(username);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/contents")
    public List<Content> get(@RequestBody ContentsByCreator request) {
        return contentRepository.findContentsByCreator(request.getCreator());
    }
}
