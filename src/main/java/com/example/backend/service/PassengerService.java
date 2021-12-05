package com.example.backend.service;

import com.example.backend.model.entity.Passenger;
import com.example.backend.model.entity.User;
import com.example.backend.model.service.UserRegistrationServiceModel;
import com.example.backend.model.view.PassengerViewModel;

import java.util.List;

public interface PassengerService {

    boolean addNewPassenger(UserRegistrationServiceModel userRequest, User user);

    void initPassengers();

    public List<PassengerViewModel> findAllPassengerWithUserId();

    public List<PassengerViewModel> findAllPassengers();

    public Passenger findPassengerById(String id);

    public Passenger findPassengerBuUserId(String id);

    List<PassengerViewModel> getAllAvailablePassengers(List<Passenger> passengers);
}
