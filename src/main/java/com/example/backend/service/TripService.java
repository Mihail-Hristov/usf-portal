package com.example.backend.service;


import com.example.backend.model.entity.Trip;
import com.example.backend.model.service.CreateTripServiceModel;
import com.example.backend.model.service.PassengersGroupServiceModel;
import com.example.backend.model.service.TripOrganizationServiceModel;
import com.example.backend.model.service.VehicleGroupServiceModel;
import com.example.backend.model.view.PassengerViewModel;
import com.example.backend.model.view.TripViewModel;
import com.example.backend.model.view.VehicleVewModel;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface TripService {

    Page<TripViewModel> findAllTripsPagination(Integer pageNo, Integer pageSize, String sortBy);

    List<TripViewModel> findAllTripsByPassenger(UserDetails principal);

    List<TripViewModel> findFirstTwoUpcomingTrips();

    TripViewModel createNewTrip(CreateTripServiceModel createTripServiceModel);

    TripViewModel findById(String id);

    Trip findByIdForInternalUse(String id);

    boolean joinNewPassenger(String id, String tripId);

    TripViewModel addVehicleToExistingTrip(VehicleGroupServiceModel vehicleGroupServiceModel, String tripId);

    List<VehicleVewModel> getAllAvailableVehicleForTrip(String id);

    void editOrganisation(TripOrganizationServiceModel tripOrganizationServiceModel, String tripId);

    List<PassengerViewModel> getAllUnJoinedPassengersInTripWith(String id);

    void addPassengersToExistingTrip(PassengersGroupServiceModel passengersGroupServiceModel, String tripId);
}
