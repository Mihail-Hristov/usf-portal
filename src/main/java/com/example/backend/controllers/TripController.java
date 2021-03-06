package com.example.backend.controllers;

import com.example.backend.model.binding.CreateTripBindingModel;
import com.example.backend.model.binding.PassengersGroupBindingModel;
import com.example.backend.model.binding.VehicleGroupBindingModel;
import com.example.backend.model.service.CreateTripServiceModel;
import com.example.backend.model.service.PassengersGroupServiceModel;
import com.example.backend.model.service.VehicleGroupServiceModel;
import com.example.backend.model.view.PassengerViewModel;
import com.example.backend.model.view.VehicleVewModel;
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
                        @RequestParam(name = "pageSize", defaultValue = "7") Integer pageSize,
                        @RequestParam(name = "sortBy", defaultValue = "matchDay") String sortBy) {

        model.addAttribute("trips", tripService.findAllTripsPagination(pageNo, pageSize, sortBy));
        model.addAttribute("vehiclesCount", vehicleService.getAllVehicles().size());
        model.addAttribute("passengersCount", passengerService.findAllPassengers().size());

        return "trips";
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

    @GetMapping("/{id}/view")
    public String viewTrip(@PathVariable String id, Model model) {

        model.addAttribute("trip", tripService.findById(id));

        return "trip-view";
    }

    @PreAuthorize("hasRole('TRIP_ADMIN')")
    @GetMapping("/add/passengers/{id}")
    public String addPassengers(@PathVariable String id, Model model) {

        List<PassengerViewModel> passengers = tripService.getAllUnJoinedPassengersInTripWith(id);

        model.addAttribute("form", passengers);
        model.addAttribute("tripId", id);

        return "trip-passenger-add";
    }

    @PreAuthorize("hasRole('TRIP_ADMIN')")
    @PostMapping("/add/passengers/{id}")
    public String addPassengersToTrip(@ModelAttribute PassengersGroupBindingModel passengersGroupBindingModel,
                                      @PathVariable String id) {

        tripService.addPassengersToExistingTrip(modelMapper.map(passengersGroupBindingModel, PassengersGroupServiceModel.class), id);

        return "redirect:/portal/trips";

    }
}
