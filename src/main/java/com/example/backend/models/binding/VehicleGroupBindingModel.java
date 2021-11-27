package com.example.backend.models.binding;

import com.example.backend.models.view.VehicleVewModel;

import java.util.ArrayList;
import java.util.List;

public class VehicleGroupBindingModel {

    private List<VehicleVewModel> vehicles;

    public VehicleGroupBindingModel() {
        this.vehicles = new ArrayList<>();
    }

    public void addVehicle(VehicleVewModel vehicleVewModel) {
        this.vehicles.add(vehicleVewModel);
    }

    public List<VehicleVewModel> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleVewModel> vehicles) {
        this.vehicles = vehicles;
    }
}
