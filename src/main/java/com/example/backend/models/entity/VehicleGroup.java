package com.example.backend.models.entity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

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
