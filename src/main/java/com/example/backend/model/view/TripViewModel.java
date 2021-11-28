package com.example.backend.model.view;

import com.example.backend.model.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TripViewModel {

    private String id;
    private String name;
    private String startSpot;
    private LocalDate MatchDay;
    private List<Passenger> availablePassengers;
    private List<Passenger> tripPassengers;
    private Map<Vehicle, VehicleGroup> vehicles;
    private Town destinationTown;
    private Country destinationCountry;

    public TripViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartSpot() {
        return startSpot;
    }

    public void setStartSpot(String startSpot) {
        this.startSpot = startSpot;
    }

    public LocalDate getMatchDay() {
        return MatchDay;
    }

    public void setMatchDay(LocalDate matchDay) {
        MatchDay = matchDay;
    }

    public List<Passenger> getAvailablePassengers() {
        return availablePassengers;
    }

    public void setAvailablePassengers(List<Passenger> availablePassengers) {
        this.availablePassengers = availablePassengers;
    }

    public List<Passenger> getTripPassengers() {
        return tripPassengers;
    }

    public void setTripPassengers(List<Passenger> tripPassengers) {
        this.tripPassengers = tripPassengers;
    }

    public Map<Vehicle, VehicleGroup> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Map<Vehicle, VehicleGroup> vehicles) {
        this.vehicles = vehicles;
    }

    public Town getDestinationTown() {
        return destinationTown;
    }

    public void setDestinationTown(Town destinationTown) {
        this.destinationTown = destinationTown;
    }

    public Country getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(Country destinationCountry) {
        this.destinationCountry = destinationCountry;
    }
}
