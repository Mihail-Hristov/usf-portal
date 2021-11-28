package com.example.backend.model.service;

import com.example.backend.model.entity.Vehicle;

import java.util.List;

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
