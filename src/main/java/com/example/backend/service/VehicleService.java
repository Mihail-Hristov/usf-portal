package com.example.backend.service;


import com.example.backend.models.entity.Vehicle;
import com.example.backend.models.service.CreateVehicleServiceModel;
import com.example.backend.models.view.VehicleVewModel;

import java.util.List;

public interface VehicleService {

    void create(CreateVehicleServiceModel addCarServiceModel);

    List<VehicleVewModel> getAllVehicles();

    Vehicle findById(String id);

    List<VehicleVewModel> getAllAvailableVehicle(List<Vehicle> usedVehicles);
}
