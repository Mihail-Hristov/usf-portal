package com.example.backend.service.impl;

import com.example.backend.exceprtion.ObjectNotFoundException;
import com.example.backend.model.entity.Passenger;
import com.example.backend.model.entity.User;
import com.example.backend.model.service.UserRegistrationServiceModel;
import com.example.backend.model.view.PassengerViewModel;
import com.example.backend.repository.PassengerRepository;
import com.example.backend.service.PassengerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final ModelMapper modelMapper;

    public PassengerServiceImpl(PassengerRepository passengerRepository, ModelMapper modelMapper) {
        this.passengerRepository = passengerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addNewPassenger(UserRegistrationServiceModel userRequest, User user) {

        Passenger passenger = new Passenger();

        if(!user.getNickname().isBlank()) {
            passenger.setName(user.getNickname());
        }else {
            passenger.setName(userRequest.getFirstName() + " " + userRequest.getLastName());
        }

        passenger.setDriver(userRequest.isDriver());
        passenger.setUser(user);

        try {
            this.passengerRepository.save(passenger);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<PassengerViewModel> findAllPassengerWithUserId() {
        return passengerRepository.findAllByUserNotNull()
                .stream()
                .map(passenger -> modelMapper.map(passenger, PassengerViewModel.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<PassengerViewModel> findAllPassengers() {

        return passengerRepository.findAllBy()
                .stream()
                .map(passenger -> modelMapper.map(passenger, PassengerViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Passenger findPassengerById(String id) {

        Passenger passenger = passengerRepository.findById(id).orElse(null);

        if (passenger == null) {
            throw new ObjectNotFoundException(id);
        }

        return passenger;
    }

    @Override
    public Passenger findPassengerBuUserId(String id) {

        Passenger passenger = passengerRepository.findPassengerByUserId(id).orElse(null);

        if (passenger == null) {
            throw new ObjectNotFoundException(id);
        }

        return passenger;
    }
}
