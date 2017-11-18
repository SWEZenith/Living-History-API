package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.User;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.request.SignUpRequest;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {
    private UserService userService;


    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup", produces = "application/json")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest request) {
//        TODO: We should check thet request.getPassword1() and request.getPassword2() are equal.
        User user = userService.findByEmail(request.getEmail());

//        TODO: Do this kind of validations using filters.
        if (user != null) {
            return new ResponseEntity<>("\"This email exist in our database.\"", HttpStatus.BAD_REQUEST);
        }
        user = userService.findByUsername(request.getUsername());
        if (user != null) {
            return new ResponseEntity<>("\"This username exist in our database\"", HttpStatus.BAD_REQUEST);
        }

        userService.createUser(request);

        return new ResponseEntity<>("\"User successfully created in our database\"", HttpStatus.CREATED);
    }
}
