package com.example.backend.model.service;

import com.example.backend.model.view.PassengerViewModel;

import java.util.List;

public class PassengersGroupServiceModel {

    private List<PassengerViewModel> passengerViewModelList;

    public PassengersGroupServiceModel() {
    }

    public List<PassengerViewModel> getPassengerViewModelList() {
        return passengerViewModelList;
    }

    public void setPassengerViewModelList(List<PassengerViewModel> passengerViewModelList) {
        this.passengerViewModelList = passengerViewModelList;
    }

}
