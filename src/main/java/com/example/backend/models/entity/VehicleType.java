package com.example.backend.models.entity;

import com.example.backend.models.entity.enumeration.VehicleTypeEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle_types")
public class VehicleType extends BaseEntity{

    private VehicleTypeEnum name;

    public VehicleType() {
    }

    @Enumerated(EnumType.STRING)
    public VehicleTypeEnum getName() {
        return name;
    }

    public void setName(VehicleTypeEnum name) {
        this.name = name;
    }
}
