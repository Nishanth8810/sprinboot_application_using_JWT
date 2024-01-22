package com.exmaple.project.JWT.controller;

import com.exmaple.project.JWT.configuration.JwtService;
import com.exmaple.project.JWT.entity.JwtRequest;
import com.exmaple.project.JWT.entity.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    JwtService jwtService;

    @PostMapping("/authenticate")
    public JwtResponse createJwtTokens(@RequestBody JwtRequest jwtRequest)
            throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }


    @GetMapping("/home")
    public String test() {
        return "welcome";
    }


    @GetMapping("/forUser")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public String userHome(){
        return "user";
    }



}
