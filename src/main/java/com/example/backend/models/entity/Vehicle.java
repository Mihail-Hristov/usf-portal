package com.example.backend.models.entity;

import com.example.backend.models.entity.enumeration.VehicleTypeEnum;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vehicles")
public class Vehicle extends BaseEntity{

    private User owner;
    private String description;
    private String make;
    private String model;
    private VehicleType type;
    private int capacity;
    private double fuelConsumption;
    private List<Trip> trips;

    public Vehicle() {
    }

    @ManyToOne
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Column(length = 70, nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false, length = 20)
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Column(nullable = false, length = 20)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @ManyToOne
    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    @Column(nullable = false)
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Column(nullable = false)
    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @ManyToMany(mappedBy = "vehicles")
    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(description, vehicle.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
