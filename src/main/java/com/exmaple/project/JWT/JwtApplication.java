package com.exmaple.project.JWT;

import com.exmaple.project.JWT.configuration.JwtService;
import com.exmaple.project.JWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JwtApplication  {

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }

}
