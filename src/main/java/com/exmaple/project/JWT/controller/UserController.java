package com.exmaple.project.JWT.controller;

import com.exmaple.project.JWT.configuration.JwtService;
import com.exmaple.project.JWT.entity.JwtRequest;
import com.exmaple.project.JWT.entity.JwtResponse;
import com.exmaple.project.JWT.entity.User;
import com.exmaple.project.JWT.repository.UserRepository;
import com.exmaple.project.JWT.service.UserService;
import com.exmaple.project.JWT.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    JwtService jwtService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> createJwtTokens(@RequestBody JwtRequest jwtRequest)
            throws Exception {
        JwtResponse jwtResponse=jwtService.createJwtToken(jwtRequest);
        if (jwtResponse==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(jwtResponse,HttpStatus.OK);
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


//    @PostMapping("/user/{userId}/upload-profile-picture")
//    public ResponseEntity<String> uploadProfilePicture(@PathVariable int userId,
//                                                       @RequestParam("file")
//                                                       MultipartFile file) throws IOException {
//
//
//        userService.uploadProfilePicture(userId, file);
//        return ResponseEntity.ok("Profile picture uploaded successfully");
//    }
//
//    @GetMapping("/user/{userId}/profile-picture")
//    public ResponseEntity<byte[]> getProfilePicture(@PathVariable int userId) {
//        byte[] imageData = userService.getProfilePicture(userId);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_JPEG);
//
//        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
//    }

    public static File resizeImage(MultipartFile inputFile) throws IOException, IOException {
        File resizedFile = File.createTempFile("resized-", "." + getFileExtension(inputFile));

        Thumbnails.of(inputFile.getInputStream())
                .size(100, 100)
                .toFile(resizedFile);

        return resizedFile;
    }

    private static String getFileExtension(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        return originalFileName != null ? originalFileName.substring(originalFileName.lastIndexOf(".") + 1) : null;
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            String userEmail = jwtUtil.getUserNameFromToken(jwtToken);
            User user = userRepository.findByEmail(userEmail);

            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("user/update/{userId}")
    public String updateUser(@PathVariable int userId, @RequestBody User updatedUser) {

        System.out.println("it worked");
        // Implement logic to update user details in the service
        User savedUser = userService.updateUser(userId, updatedUser);
        return "sucess";
    }
}

