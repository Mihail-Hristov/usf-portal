package com.example.backend.repository;

import com.example.backend.model.entity.UserRole;
import com.example.backend.model.entity.enumeration.UserRoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    public UserRole findAllByName(UserRoleNameEnum name);
}
