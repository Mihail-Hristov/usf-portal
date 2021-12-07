package com.example.backend.model.service;

import com.example.backend.model.view.PassengerViewModel;

import java.util.List;

public class PassengersGroupServiceModel {

    private List<String> passengers;

    public PassengersGroupServiceModel() {
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<String> passengers) {
        this.passengers = passengers;
    }
}
