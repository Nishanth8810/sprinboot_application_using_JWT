package com.exmaple.project.JWT;

import com.exmaple.project.JWT.configuration.JwtService;
import com.exmaple.project.JWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JwtApplication implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//    User user=new User();
//    user.setEmail("aaa");
//    user.setPassword(bCryptPasswordEncoder.encode("aaa"));
//    userRepository.save(user);


    }
}
