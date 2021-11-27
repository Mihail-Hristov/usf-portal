package com.example.backend.repository;

import com.example.backend.models.entity.VehicleType;
import com.example.backend.models.entity.enumeration.VehicleTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, String> {

    public VehicleType findAllByName(VehicleTypeEnum name);
}
