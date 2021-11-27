package com.example.backend.repository;

import com.example.backend.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsUserByUsernameIgnoreCase(String username);

    Optional<User> findFirstByUsername(String username);

    Optional<User> findFirstById(String id);

    List<User> findAll();
}
