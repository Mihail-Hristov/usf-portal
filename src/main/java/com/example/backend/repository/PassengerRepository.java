package com.example.backend.repository;

import com.example.backend.model.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String> {

    List<Passenger> findAllByUserNotNull();

    @Query("SELECT p FROM Passenger p WHERE p.user.id = ?1 ")
    Optional<Passenger> findPassengerByUserId(String userId);

    List<Passenger> findAllBy();

    List<Passenger> findAllByIdNotIn(Collection<String> id);

    Passenger findPassengerByName(String name);
}
