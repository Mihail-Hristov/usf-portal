package com.example.backend.repository;

import com.example.backend.models.entity.Passenger;
import com.example.backend.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String> {

    List<Passenger> findAllByUserNotNull();

    @Query("SELECT p FROM Passenger p WHERE p.user.id = ?1 ")
    Optional<Passenger> findPassengerByUserId(String userId);

    List<Passenger> findAllBy();
}
