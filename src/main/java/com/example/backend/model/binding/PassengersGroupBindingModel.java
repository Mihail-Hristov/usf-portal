package com.example.backend.model.binding;

import com.example.backend.model.view.PassengerViewModel;

import java.util.ArrayList;
import java.util.List;

public class PassengersGroupBindingModel {

    private List<String> passengers;

    public PassengersGroupBindingModel() {
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<String> passengers) {
        this.passengers = passengers;
    }
}
