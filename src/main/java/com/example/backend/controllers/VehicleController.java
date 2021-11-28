package com.example.backend.controllers;

import com.example.backend.model.binding.AddVehicleBindingModel;
import com.example.backend.model.service.CreateVehicleServiceModel;
import com.example.backend.service.VehicleService;
import com.example.backend.service.PassengerService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/portal/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;
    private final ModelMapper modelMapper;
    private final PassengerService passengerService;

    public VehicleController(VehicleService vehicleService, ModelMapper modelMapper, PassengerService passengerService) {
        this.vehicleService = vehicleService;
        this.modelMapper = modelMapper;
        this.passengerService = passengerService;
    }

    @ModelAttribute
    public AddVehicleBindingModel addCarBindingModel() {
        return new AddVehicleBindingModel();
    }

    @PreAuthorize("hasRole('TRIP_ADMIN')")
    @GetMapping("/all")
    public String showAllCars(Model model,
                              @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
                              @RequestParam(name = "pageSize", defaultValue = "6") Integer pageSize,
                              @RequestParam(name = "sortBy", defaultValue = "description") String sortBy) {

        model.addAttribute("passengers", passengerService.findAllPassengerWithUserId());
        model.addAttribute("vehicles", vehicleService.getAllVehiclesPagination(pageNo, pageSize, sortBy));

        return "vehicles";
    }


    @PreAuthorize("hasRole('TRIP_ADMIN')")
    @PostMapping("/add")
    public String createCar(@Valid AddVehicleBindingModel addVehicleBindingModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addVehicleBindingModel", addVehicleBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addVehicleBindingModel", bindingResult);

            return "redirect:all";
        }

        vehicleService.create(modelMapper.map(addVehicleBindingModel, CreateVehicleServiceModel.class));

        return "redirect:all";
    }
}
