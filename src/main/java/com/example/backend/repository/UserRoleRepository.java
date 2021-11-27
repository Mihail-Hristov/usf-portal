package com.example.backend.repository;

import com.example.backend.models.entity.UserRole;
import com.example.backend.models.entity.enumeration.UserRoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    public UserRole findAllByName(UserRoleNameEnum name);
}
