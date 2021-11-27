package com.example.backend.service;


import com.example.backend.models.binding.TripOrganizationBindingModel;
import com.example.backend.models.entity.Trip;
import com.example.backend.models.service.CreateTripServiceModel;
import com.example.backend.models.service.TripOrganizationServiceModel;
import com.example.backend.models.service.VehicleGroupServiceModel;
import com.example.backend.models.view.TripViewModel;
import com.example.backend.models.view.VehicleVewModel;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TripService {

    Page<TripViewModel> findAllTrips(Integer pageNo, Integer pageSize, String sortBy);

    List<TripViewModel> findAllTripsByPassenger(UserDetails principal);

    TripViewModel createNewTrip(CreateTripServiceModel createTripServiceModel);

    TripViewModel findById(String id);

    Trip findByIdForInternalUse(String id);

    boolean joinNewPassenger(String id, String tripId);

    TripViewModel addVehicleToExistingTrip(VehicleGroupServiceModel vehicleGroupServiceModel, String tripId);

    List<VehicleVewModel> getAllAvailableVehicleForTrip(String id);

    void editOrganisation(TripOrganizationServiceModel tripOrganizationServiceModel, String tripId);
}
