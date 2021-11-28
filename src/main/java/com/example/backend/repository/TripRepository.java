package com.example.backend.repository;

import com.example.backend.model.entity.Passenger;
import com.example.backend.model.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, String> {

    public List<Trip> findAllByTripPassengersContainsOrderByMatchDayDesc(Passenger passenger);


}
