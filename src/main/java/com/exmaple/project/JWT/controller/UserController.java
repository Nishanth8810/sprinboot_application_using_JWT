package com.exmaple.project.JWT.controller;

import com.exmaple.project.JWT.configuration.JwtService;
import com.exmaple.project.JWT.entity.JwtRequest;
import com.exmaple.project.JWT.entity.JwtResponse;
import com.exmaple.project.JWT.entity.User;
import com.exmaple.project.JWT.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    JwtService jwtService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/authenticate")
    public JwtResponse createJwtTokens(@RequestBody JwtRequest jwtRequest)
            throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }

    @GetMapping("/home")
    public String test() {
        return "welcome";
    }


    @GetMapping("/forAdmin")
    public String userHome(HttpServletRequest httpServletRequest) {
        System.out.println(httpServletRequest.getUserPrincipal().getName());
        return "admin";
    }

    @GetMapping("/forUser")
    public String adminHome() {
        return "user";
    }



}
