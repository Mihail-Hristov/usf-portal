package com.example.backend.repository;

import com.example.backend.models.entity.SecureToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SecureTokenRepository extends JpaRepository<SecureToken, String> {

    SecureToken findFirstByToken(String token);

    void removeByToken(String token);
}
