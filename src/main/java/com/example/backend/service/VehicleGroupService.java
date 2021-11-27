package com.example.backend.service;

import com.example.backend.models.entity.VehicleGroup;

public interface VehicleGroupService {

    void saveNewVehicleGroup(VehicleGroup vehicleGroup);

    VehicleGroup getVehicleGroupById(String id);
}
