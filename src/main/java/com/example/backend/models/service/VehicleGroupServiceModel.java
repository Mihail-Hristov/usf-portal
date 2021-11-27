package com.example.backend.models.service;

import com.example.backend.models.entity.Vehicle;

import java.util.List;
import java.util.Set;

public class VehicleGroupServiceModel {

    private List<Vehicle> vehicles;

    public VehicleGroupServiceModel() {
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
