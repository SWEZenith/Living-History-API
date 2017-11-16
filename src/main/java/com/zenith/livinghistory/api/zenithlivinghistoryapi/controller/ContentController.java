package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model.ContentItemModel;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model.ContentModel;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model.UserModel;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.ContentRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.UserRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.request.HistoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/contents")
public class ContentController {
    private ContentRepository contentRepository;
    private UserRepository userRepository;

    @Autowired
    public ContentController(ContentRepository contentRepository, UserRepository userRepository) {
        this.contentRepository = contentRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody HistoryRequest content) {
        ContentModel model = new ContentModel();

        UserModel creator = userRepository.findFirstByJsonldId(content.getCreator());
        model.setCreator(creator);

        List<ContentItemModel> contentItemModels = content
                .getHistoryItems()
                .stream()
                .map(historyItem -> {
                    ContentItemModel contentItemModel = new ContentItemModel();
                    contentItemModel.setType(historyItem.getType());
                    contentItemModel.setContent(historyItem.getContent());
                    // contentItemModel.setHistory(); // ?
                    return contentItemModel;
                })
                .collect(Collectors.toList());

        model.setItems(contentItemModels);
        model.setTitle(content.getTitle());
        model.setDescription(content.getDescription());

		contentRepository.save(model);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ContentModel>> getAll() {
        return new ResponseEntity<>(this.contentRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ContentModel> get(@PathVariable("id") String jsonldHistoryId) {
        return new ResponseEntity<>(contentRepository.findFirstByJsonLdContentId(jsonldHistoryId), HttpStatus.OK);
    }
}
