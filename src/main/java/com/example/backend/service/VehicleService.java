package com.example.backend.service;


import com.example.backend.models.entity.Vehicle;
import com.example.backend.models.service.CreateVehicleServiceModel;
import com.example.backend.models.view.VehicleVewModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VehicleService {

    void create(CreateVehicleServiceModel addCarServiceModel);

    List<VehicleVewModel> getAllVehicles();

    Page<VehicleVewModel> getAllVehiclesPagination(Integer pageNo, Integer pageSize, String sortBy);

    Vehicle findById(String id);

    List<VehicleVewModel> getAllAvailableVehicle(List<Vehicle> usedVehicles);
}
