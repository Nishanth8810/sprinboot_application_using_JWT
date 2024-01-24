package com.exmaple.project.JWT.repository;

import com.exmaple.project.JWT.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String username);

    boolean existsByEmail(String term);

    List<User> findByEmailContainingIgnoreCase(String term);
}
