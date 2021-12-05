package com.example.backend.model.binding;

import com.example.backend.model.view.PassengerViewModel;

import java.util.ArrayList;
import java.util.List;

public class PassengersGroupBindingModel {

    private List<String> passengerViewModelList;

    public PassengersGroupBindingModel() {
        passengerViewModelList = new ArrayList<>();
    }

    public void addPassenger(String id) {
        passengerViewModelList.add(id);
    }

    public List<String> getPassengerViewModelList() {
        return passengerViewModelList;
    }

    public void setPassengerViewModelList(List<String> passengerViewModelList) {
        this.passengerViewModelList = passengerViewModelList;
    }
}
