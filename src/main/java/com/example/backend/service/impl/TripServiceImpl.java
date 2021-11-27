package com.example.backend.service.impl;

import com.example.backend.exceprtion.ObjectNotFoundException;
import com.example.backend.models.entity.*;
import com.example.backend.models.service.CarsOrganisationsServiceModel;
import com.example.backend.models.service.CreateTripServiceModel;
import com.example.backend.models.service.TripOrganizationServiceModel;
import com.example.backend.models.service.VehicleGroupServiceModel;
import com.example.backend.models.view.TripViewModel;
import com.example.backend.models.view.VehicleVewModel;
import com.example.backend.repository.TripRepository;
import com.example.backend.service.*;
import com.example.backend.util.TripInvitationEmailContext;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final VehicleGroupService vehicleGroupService;
    private final CountryService countryService;
    private final VehicleService vehicleService;
    private final TownService townService;
    private final SpringTemplateEngine templateEngine;
    private final EmailService emailService;
    private final PassengerService passengerService;
    private final Environment env;

    public TripServiceImpl(TripRepository tripRepository, UserService userService, ModelMapper modelMapper, VehicleGroupService vehicleGroupService, CountryService countryService, VehicleService vehicleService, TownService townService, SpringTemplateEngine templateEngine, EmailService emailService, PassengerService passengerService, Environment env) {
        this.tripRepository = tripRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.vehicleGroupService = vehicleGroupService;
        this.countryService = countryService;
        this.vehicleService = vehicleService;
        this.townService = townService;
        this.templateEngine = templateEngine;
        this.emailService = emailService;
        this.passengerService = passengerService;
        this.env = env;
    }

    @Transactional
    @Override
    public Page<TripViewModel> findAllTrips(Integer pageNo, Integer pageSize, String sortBy) {

        PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return tripRepository.findAll(pageable)
                .map(this::asTrip);

    }

    private TripViewModel asTrip(Trip trip) {
        return modelMapper.map(trip, TripViewModel.class);
    }

    @Override
    public TripViewModel createNewTrip(CreateTripServiceModel createTripServiceModel) {

        Trip trip = modelMapper.map(createTripServiceModel, Trip.class);
        trip.setDestinationCountry(countryService.findCountryById(createTripServiceModel.getDestinationCountry()));
        trip.setDestinationTown(townService.findTownById(createTripServiceModel.getDestinationTown()));

        TripViewModel tripViewModel = modelMapper.map(tripRepository.save(trip), TripViewModel.class);

        if (createTripServiceModel.isToSentAnEmail()) {
            userService.getAllUsers()
                    .forEach(user -> {
                        TripInvitationEmailContext email = new TripInvitationEmailContext(env);
                        email.init(user);

                        Context context = new Context();
                        context.setVariable("name", user.getFirstName());
                        context.setVariable("to", trip.getDestinationTown().getName());
                        String baseURL = "http://localhost:8080";
                        context.setVariable("URL", email.buildInvitationUrl(baseURL, user.getId(), trip.getId()));

                        email.setTemplateLocation(templateEngine.process("tripInvitationEmail", context));

                        try {
                            emailService.sendEmail(email);
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                    });
        }

        return tripViewModel;
    }

    @Transactional
    @Override
    public TripViewModel findById(String id) {

        Trip trip = tripRepository.findById(id).orElse(null);

        if (trip == null) {
            throw new ObjectNotFoundException(id);
        }

        return modelMapper.map(trip, TripViewModel.class);
    }

    @Transactional
    @Override
    public boolean joinNewPassenger(String id, String tripId) {

        Passenger passenger = passengerService.findPassengerBuUserId(id);

        Trip trip = tripRepository.findById(tripId).orElse(null);

        if (trip == null) {
            throw new ObjectNotFoundException(tripId);
        }
        trip.getTripPassengers().add(passenger);
        trip.getAvailablePassengers().add(passenger);

        tripRepository.save(trip);

        return true;
    }

    @Transactional
    @Override
    public TripViewModel addVehicleToExistingTrip(VehicleGroupServiceModel vehicleGroupServiceModel, String tripId) {

        Trip trip = tripRepository.findById(tripId).orElse(null);

        if (trip == null) {
            throw new ObjectNotFoundException(tripId);
        }


        vehicleGroupServiceModel
                .getVehicles()
                .forEach(v -> {
                    if (v.getId() != null) {
                        Vehicle vehicle = vehicleService.findById(v.getId());
                        VehicleGroup vehicleGroup = new VehicleGroup();
                        vehicleGroup.setPassengers(new ArrayList<>());
                        vehicleGroupService.saveNewVehicleGroup(vehicleGroup);
                        trip.getVehicles().put(vehicle, vehicleGroup);
                    }
                });

        tripRepository.save(trip);

        return null;
    }

    @Transactional
    @Override
    public List<VehicleVewModel> getAllAvailableVehicleForTrip(String id) {

        Trip trip = tripRepository.findById(id).orElse(null);

        if (trip == null) {
            throw new ObjectNotFoundException(id);
        }

        List<Vehicle> usedVehicles = new ArrayList<>(trip.getVehicles().keySet());

        return vehicleService.getAllAvailableVehicle(usedVehicles);
    }

    @Override
    public Trip findByIdForInternalUse(String id) {

        Trip trip = tripRepository.findById(id).orElse(null);

        if (trip == null) {
            throw new ObjectNotFoundException(id);
        }

        return trip;
    }

    @Transactional
    @Override
    public void editOrganisation(TripOrganizationServiceModel tripOrganizationServiceModel, String tripId) {

        Trip trip = tripRepository.findById(tripId).orElse(null);

        if (trip == null) {
            throw new ObjectNotFoundException(tripId);
        }

        Map<Vehicle, VehicleGroup> vehicles = trip.getVehicles();

        for (CarsOrganisationsServiceModel CarsOrganizations : tripOrganizationServiceModel.getCarsOrganisations()) {
            Vehicle vehicle = vehicleService.findById(CarsOrganizations.getVehicleId());

            List<Passenger> passengers = new ArrayList<>();

            if (CarsOrganizations.getPassengers().size() != 0) {
                for (String passengerId : CarsOrganizations.getPassengers()) {
                    Passenger passenger = passengerService.findPassengerById(passengerId);
                    passengers.add(passenger);
                }
            }

            vehicles.get(vehicle).getPassengers().clear();
            vehicles.get(vehicle).setPassengers(passengers);


        }


        List<Passenger> availablePassengers = new ArrayList<>();

        if (tripOrganizationServiceModel.getPassengers().size() != 0) {
            for (String passengerId : tripOrganizationServiceModel.getPassengers()) {
                Passenger passenger = passengerService.findPassengerById(passengerId);
                availablePassengers.add(passenger);
            }
        }

        trip.getAvailablePassengers().clear();
        trip.setAvailablePassengers(availablePassengers);

        tripRepository.save(trip);
    }

    @Transactional
    @Override
    public List<TripViewModel> findAllTripsByPassenger(UserDetails principal) {

        User user = userService.getUserByUsername(principal.getUsername());
        Passenger passenger = passengerService.findPassengerBuUserId(user.getId());

        return tripRepository.findAllByTripPassengersContainsOrderByMatchDayDesc(passenger)
                .stream()
                .map(trip -> modelMapper.map(trip, TripViewModel.class))
                .collect(Collectors.toList());
    }
}
