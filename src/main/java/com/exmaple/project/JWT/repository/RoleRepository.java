package com.exmaple.project.JWT.repository;

import com.exmaple.project.JWT.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, String> {

}
