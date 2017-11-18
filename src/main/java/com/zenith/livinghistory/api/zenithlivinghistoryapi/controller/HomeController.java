package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gkc on 30/09/2017.
 */
@RestController
public class HomeController {
    @RequestMapping("/")
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("This is Living-History Api running on Google Cloud", HttpStatus.OK);
    }
}