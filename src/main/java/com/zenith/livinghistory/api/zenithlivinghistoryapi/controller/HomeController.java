package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.ContentRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Content;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

/**
 * Created by gkc on 30/09/2017.
 */
@RestController
public class HomeController {
	private ContentRepository contentRepository;

	public HomeController(ContentRepository contentRepository) {
		this.contentRepository = contentRepository;
	}

	/* pinar - Sorting contents by most recent ones - createdAt is used as sorting date */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Content>> getAll() {
		List<Content> contents = this.contentRepository.findAll();
		contents.sort(Comparator.comparing(Content::getCreatedAt));
		return new ResponseEntity<>(contents, HttpStatus.OK);
	}
}
