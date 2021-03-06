package com.example.backend.model.service;

import java.util.List;

public class CarsOrganisationsServiceModel {

    private String vehicleId;
    private List<String> passengers;

    public CarsOrganisationsServiceModel() {
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<String> passengers) {
        this.passengers = passengers;
    }
}
