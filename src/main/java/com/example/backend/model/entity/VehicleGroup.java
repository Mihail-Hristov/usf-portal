package com.example.backend.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicle_group")
public class VehicleGroup extends BaseEntity{

    private List<Passenger> passengers;

    public VehicleGroup() {
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
