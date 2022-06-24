package com.example.springsecurityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springsecurityapp.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
