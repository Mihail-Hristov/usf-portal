package com.example.backend.repository;

import com.example.backend.models.entity.VehicleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleGroupRepository extends JpaRepository<VehicleGroup, String> {

    VehicleGroup getVehicleGroupById(String id);
}
