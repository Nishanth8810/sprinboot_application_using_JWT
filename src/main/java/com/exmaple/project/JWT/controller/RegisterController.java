package com.exmaple.project.JWT.controller;

import com.exmaple.project.JWT.entity.User;
import com.exmaple.project.JWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody User user) {
        boolean isRegistered = userService.registerUser(user);

        if (isRegistered) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


}
