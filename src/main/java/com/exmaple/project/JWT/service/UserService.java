package com.exmaple.project.JWT.service;

import com.exmaple.project.JWT.entity.Role;
import com.exmaple.project.JWT.entity.User;
import com.exmaple.project.JWT.repository.RoleRepository;
import com.exmaple.project.JWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
            if (userRepository.existsByEmail(user.getEmail())){
                return false;
            }
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

    public User updateUser(int userId, User updatedUser) {
        User user=userRepository.findById(userId).orElseThrow();
        user.setEmail(updatedUser.getEmail());
      return   userRepository.save(user);
    }

}

