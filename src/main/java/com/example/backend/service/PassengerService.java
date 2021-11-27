package com.example.backend.service;

import com.example.backend.models.entity.Passenger;
import com.example.backend.models.entity.User;
import com.example.backend.models.service.UserRegistrationServiceModel;
import com.example.backend.models.view.PassengerViewModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PassengerService {

    boolean addNewPassenger(UserRegistrationServiceModel userRequest, User user);

    public List<PassengerViewModel> findAllPassengerWithUserId();

    public List<PassengerViewModel> findAllPassengers();

    public Passenger findPassengerById(String id);

    public Passenger findPassengerBuUserId(String id);
}
