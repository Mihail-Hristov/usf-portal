package com.example.backend.model.binding;

import com.example.backend.model.entity.enumeration.VehicleTypeEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotNull
    @Size(min = 5, max = 20, message = "Името трябва да е между 5 и 20 символа включително!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "Задължително е да се посочи марка!")
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @NotNull(message = "Задължително е да се посочи модел!")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NotNull(message = "Задължително е да се посочи тип!")
    public VehicleTypeEnum getType() {
        return type;
    }

    public void setType(VehicleTypeEnum type) {
        this.type = type;
    }

    @NotNull(message = "Задължително е да се посочи капацитет!")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @NotNull(message = "Задължително е да се посочи разход!")
    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
}
