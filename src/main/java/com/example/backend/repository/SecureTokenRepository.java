package com.example.backend.repository;

import com.example.backend.model.entity.SecureToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SecureTokenRepository extends JpaRepository<SecureToken, String> {

    SecureToken findFirstByToken(String token);

    void removeByToken(String token);

    List<SecureToken> findAllByExpireAtBefore(LocalDateTime localDateTime);
}
