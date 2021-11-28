package com.example.backend.service;

import com.example.backend.model.entity.VehicleType;
import com.example.backend.model.entity.enumeration.VehicleTypeEnum;

public interface VehicleTypeService {

    public void initVehicleType();

    public VehicleType findVehicleTypeByName(VehicleTypeEnum name);
}
