package com.example.backend.models.binding;

import com.example.backend.models.entity.User;
import com.example.backend.models.entity.VehicleType;
import com.example.backend.models.entity.enumeration.VehicleTypeEnum;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class AddVehicleBindingModel {

    private String owner;
    private String description;
    private String make;
    private String model;
    private VehicleTypeEnum type;
    private int capacity;
    private double fuelConsumption;

    public AddVehicleBindingModel() {
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @NotNull
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NotNull
    public VehicleTypeEnum getType() {
        return type;
    }

    public void setType(VehicleTypeEnum type) {
        this.type = type;
    }

    @NotNull
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @NotNull
    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
}
