package com.example.backend.service.impl;

import com.example.backend.models.entity.VehicleGroup;
import com.example.backend.repository.VehicleGroupRepository;
import com.example.backend.service.VehicleGroupService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VehicleGroupServiceImpl implements VehicleGroupService {

    private final VehicleGroupRepository vehicleGroupRepository;

    public VehicleGroupServiceImpl(VehicleGroupRepository vehicleGroupRepository) {
        this.vehicleGroupRepository = vehicleGroupRepository;
    }

    @Override
    public void saveNewVehicleGroup(VehicleGroup vehicleGroup) {
        vehicleGroupRepository.save(vehicleGroup);
    }

    @Transactional
    @Override
    public VehicleGroup getVehicleGroupById(String id) {
        return vehicleGroupRepository.getVehicleGroupById(id);
    }
}
