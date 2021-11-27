package com.example.backend.models.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "passengers")
public class Passenger extends BaseEntity{

    private String name;
    private boolean isDriver;
    private User user;
    private List<Trip> trips;

    public Passenger() {
    }

    @Column(nullable = false, length = 125)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }

    @OneToOne(fetch = FetchType.LAZY)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany(mappedBy = "tripPassengers")
    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
