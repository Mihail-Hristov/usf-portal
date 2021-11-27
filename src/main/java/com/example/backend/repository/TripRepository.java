package com.example.backend.repository;

import com.example.backend.models.entity.Passenger;
import com.example.backend.models.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TripRepository extends JpaRepository<Trip, String> {

    public List<Trip> findAllByTripPassengersContainsOrderByMatchDayDesc(Passenger passenger);


}
