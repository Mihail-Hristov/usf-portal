package com.example.backend.controllers;

import com.example.backend.models.binding.TripOrganizationBindingModel;
import com.example.backend.models.service.TripOrganizationServiceModel;
import com.example.backend.service.TripService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TripEditRestController {

    private final TripService tripService;
    private final ModelMapper modelMapper;

    public TripEditRestController(TripService tripService, ModelMapper modelMapper) {
        this.tripService = tripService;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("hasRole('TRIP_ADMIN')")
    @PatchMapping("/api/trip/edit/{tripId}")
    public String editTrip(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable String tripId,
            @RequestBody TripOrganizationBindingModel requestParam) {

        TripOrganizationServiceModel tripOrganizationServiceModel = modelMapper.map(requestParam, TripOrganizationServiceModel.class);


        tripService.editOrganisation(tripOrganizationServiceModel, tripId);



        return "redirect:/trips";
    }
}
