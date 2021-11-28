package com.example.backend.controllers;

import com.example.backend.models.binding.CreateTripBindingModel;
import com.example.backend.models.binding.VehicleGroupBindingModel;
import com.example.backend.models.service.CreateTripServiceModel;
import com.example.backend.models.service.VehicleGroupServiceModel;
import com.example.backend.models.view.VehicleVewModel;
import com.example.backend.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/portal/trips")
public class TripController {

    private final TripService tripService;
    private final VehicleService vehicleService;
    private final PassengerService passengerService;
    private final CountryService countryService;
    private final TownService townService;
    private final ModelMapper modelMapper;

    public TripController(ModelMapper modelMapper, TripService tripService, VehicleService vehicleService, PassengerService passengerService, CountryService countryService, TownService townService) {
        this.modelMapper = modelMapper;
        this.tripService = tripService;
        this.vehicleService = vehicleService;
        this.passengerService = passengerService;
        this.countryService = countryService;
        this.townService = townService;
    }

    @ModelAttribute
    private CreateTripBindingModel addTripBindingModel() {
        return new CreateTripBindingModel();
    }


    @GetMapping()
    public String trips(Model model,
                        @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
                        @RequestParam(name = "pageSize", defaultValue = "4") Integer pageSize,
                        @RequestParam(name = "sortBy", defaultValue = "matchDay") String sortBy) {

        model.addAttribute("trips", tripService.findAllTripsPagination(pageNo, pageSize, sortBy));

        return "/trips";
    }

    @PreAuthorize("hasRole('TRIP_ADMIN')")
    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        model.addAttribute("passengers", passengerService.findAllPassengers());
        model.addAttribute("destinationTown", townService.getAllTowns());
        model.addAttribute("destinationCountry", countryService.getAllCountries());

        return "trips-create";
    }


    @GetMapping("/details/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("trip", tripService.findById(id));
        return "trip-details";
    }

    @PreAuthorize("hasRole('TRIP_ADMIN')")
    @PostMapping("/create")
    public String createTrip(@Valid CreateTripBindingModel createTripBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createTripBindingModel", createTripBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createTripBindingModel", bindingResult);
            return "redirect:create";
        }

        tripService.createNewTrip(modelMapper.map(createTripBindingModel, CreateTripServiceModel.class));

        return "redirect:/portal/trips";
    }

    @GetMapping("/confirm-invitation")
    public String joinToTrip(@RequestParam String id, String tripId) {

        tripService.joinNewPassenger(id, tripId);

        return "redirect:/trips";
    }

    @PreAuthorize("hasRole('TRIP_ADMIN')")
    @GetMapping("/add/vehicles/{id}")
        public String addVehicle(@PathVariable String id, Model model) {

        VehicleGroupBindingModel vehiclesForm = new VehicleGroupBindingModel();

        List<VehicleVewModel> veh = tripService.getAllAvailableVehicleForTrip(id);

        for (VehicleVewModel vehicleVewModel : tripService.getAllAvailableVehicleForTrip(id)) {
            vehiclesForm.addVehicle(vehicleVewModel);
        }

        model.addAttribute("form", vehiclesForm);
        model.addAttribute("tripId", id);

        return "trip-vehicle-add";
    }

    @PreAuthorize("hasRole('TRIP_ADMIN')")
    @PostMapping("/add/vehicles/{id}")
    public String addVehiclesToTrip(@ModelAttribute VehicleGroupBindingModel vehiclesForm,
                                    @PathVariable String id) {

        tripService.addVehicleToExistingTrip(modelMapper.map(vehiclesForm, VehicleGroupServiceModel.class), id);

        return "redirect:/portal/trips";

    }
}
