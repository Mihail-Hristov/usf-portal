package com.example.backend.service.impl;

import com.example.backend.models.entity.BaseEntity;
import com.example.backend.models.entity.Trip;
import com.example.backend.models.entity.Vehicle;
import com.example.backend.models.entity.User;
import com.example.backend.models.service.CreateVehicleServiceModel;
import com.example.backend.models.view.VehicleVewModel;
import com.example.backend.repository.VehicleRepository;
import com.example.backend.service.TripService;
import com.example.backend.service.VehicleService;
import com.example.backend.service.UserService;
import com.example.backend.service.VehicleTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleTypeService vehicleTypeService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleTypeService vehicleTypeService, ModelMapper modelMapper, UserService userService) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeService = vehicleTypeService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void create(CreateVehicleServiceModel addCarServiceModel) {

        Vehicle vehicle = modelMapper.map(addCarServiceModel, Vehicle.class);
        User user = userService.findUserById(addCarServiceModel.getOwner());

        vehicle.setType(vehicleTypeService.findVehicleTypeByName(addCarServiceModel.getType()));
        vehicle.setOwner(user);

        this.vehicleRepository.save(vehicle);
    }

    @Transactional
    @Override
    public List<VehicleVewModel> getAllVehicles() {
        return vehicleRepository.findAllBy()
                .stream()
                .map(car -> modelMapper.map(car, VehicleVewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Vehicle findById(String id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public List<VehicleVewModel> getAllAvailableVehicle(List<Vehicle> usedVehicles) {

        Collection<String> ids = usedVehicles
                .stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());

        List<VehicleVewModel> test;

        if (ids.size() == 0) {
            test =  vehicleRepository.findAllBy()
                    .stream()
                    .map(vehicle -> modelMapper.map(vehicle, VehicleVewModel.class))
                    .collect(Collectors.toList());
        }else {
            test = vehicleRepository.findAllByIdNotIn(ids)
                    .stream()
                    .map(vehicle -> modelMapper.map(vehicle, VehicleVewModel.class))
                    .collect(Collectors.toList());
        }

        return test;
    }
}
