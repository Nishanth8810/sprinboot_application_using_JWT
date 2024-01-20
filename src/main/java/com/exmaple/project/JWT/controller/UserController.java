package com.exmaple.project.JWT.controller;

import com.exmaple.project.JWT.configuration.JwtFilter;
import com.exmaple.project.JWT.configuration.JwtService;
import com.exmaple.project.JWT.entity.JwtRequest;
import com.exmaple.project.JWT.entity.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    JwtService jwtService;
    @PostMapping("/authenticate")
    public JwtResponse createJwtTokens(@RequestBody JwtRequest jwtRequest)
            throws Exception {
        System.out.println("aojhasidg");
        return jwtService.createJwtToken(jwtRequest);
    }

    @GetMapping("/home")
    public String test(){
        return "welcome";
    }

}
