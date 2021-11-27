package com.example.backend.models.binding;

import java.util.List;

public class CarsOrganisationsBindingModel {

    private String vehicleId;
    private List<String> passengers;

    public CarsOrganisationsBindingModel() {
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
