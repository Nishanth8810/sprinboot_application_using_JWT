package com.exmaple.project.JWT.repository;

import com.exmaple.project.JWT.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
