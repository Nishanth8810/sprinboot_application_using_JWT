package com.exmaple.project.JWT.service;

import com.exmaple.project.JWT.entity.Role;
import com.exmaple.project.JWT.entity.User;
import com.exmaple.project.JWT.repository.RoleRepository;
import com.exmaple.project.JWT.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public boolean registerUser(User user) {
        try {
            User user1= new User();
            Role role = roleRepository.findById("User").orElseThrow();
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user1.setRole(userRoles);
            user1.setEmail(user.getEmail());
            user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user1);
            return true;
        } catch (Exception e) {
            return false;
        }
        }

//    public void uploadProfilePicture(int userId, MultipartFile file) {
//        // Retrieve the user from the database
//        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
//
//        // Convert and set the image data
//        try {
//            user.setProfilePicture(file.getBytes());
//        } catch (IOException e) {
//            // Handle exception
//        }
//
//        // Save the updated user entity
//        userRepository.save(user);
//    }
//
//    public byte[] getProfilePicture(int userId) {
//        return userRepository.findById(userId).get().getProfilePicture();
//    }


//    public byte[] getProfilePicture(int userId) {
//    }
}

