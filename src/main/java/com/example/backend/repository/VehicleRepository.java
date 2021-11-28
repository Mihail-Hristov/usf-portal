package com.example.backend.repository;

import com.example.backend.model.entity.Vehicle;
import com.example.backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    boolean existsCarByOwnerAndMakeAndModel(User user, String make, String model);

    List<Vehicle> findAllBy();

    List<Vehicle> findAllByIdNotIn(Collection<String> id);
}
