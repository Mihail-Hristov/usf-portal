package com.example.backend.service.impl;


import com.example.backend.models.entity.VehicleType;
import com.example.backend.models.entity.enumeration.VehicleTypeEnum;
import com.example.backend.repository.VehicleTypeRepository;
import com.example.backend.service.VehicleTypeService;
import org.springframework.stereotype.Service;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;

    public VehicleTypeServiceImpl(VehicleTypeRepository vehicleTypeRepository) {

        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    @Override
    public void initVehicleType() {
        if (vehicleTypeRepository.count() != 0) {
            return;
        }

        for (VehicleTypeEnum vehicleTypeEnum : VehicleTypeEnum.values()) {
            VehicleType vehicleType = new VehicleType();
            vehicleType.setName(vehicleTypeEnum);
            vehicleTypeRepository.save(vehicleType);
        }
    }

    @Override
    public VehicleType findVehicleTypeByName(VehicleTypeEnum name) {
        return vehicleTypeRepository.findAllByName(name);
    }
}
