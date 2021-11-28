package com.example.backend.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "trips")
public class Trip extends BaseEntity{

    private String name;
    private String startSpot;
    private LocalDate matchDay;
    private List<Passenger> availablePassengers;
    private List<Passenger> tripPassengers;
    private Map<Vehicle, VehicleGroup> vehicles = new LinkedHashMap<>();
    private Town destinationTown;
    private Country destinationCountry;
    private LocalDate createdOn;
    private User owner;

    public Trip() {
    }

    @Column(nullable = false, length = 70)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "start_spot")
    public String getStartSpot() {
        return startSpot;
    }

    public void setStartSpot(String startSpot) {
        this.startSpot = startSpot;
    }

    @Column(name = "match_day")
    public LocalDate getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(LocalDate matchDay) {
        this.matchDay = matchDay;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    public List<Passenger> getAvailablePassengers() {
        return availablePassengers;
    }

    public void setAvailablePassengers(List<Passenger> availablePassengers) {
        this.availablePassengers = availablePassengers;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    public List<Passenger> getTripPassengers() {
        return tripPassengers;
    }

    public void setTripPassengers(List<Passenger> tripPassengers) {
        this.tripPassengers = tripPassengers;
    }

    @ManyToOne(optional = false)
    public Town getDestinationTown() {
        return destinationTown;
    }

    public void setDestinationTown(Town destinationTown) {
        this.destinationTown = destinationTown;
    }

    @ManyToOne
    public Country getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(Country destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    @OneToMany(fetch = FetchType.LAZY)
    public Map<Vehicle, VehicleGroup> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Map<Vehicle, VehicleGroup> vehicles) {
        this.vehicles = vehicles;
    }

    @Column(name = "created_on", nullable = false)
    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    @ManyToOne
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @PrePersist
    public void beforeCreate() {
        setCreatedOn(LocalDate.now());
    }
}
