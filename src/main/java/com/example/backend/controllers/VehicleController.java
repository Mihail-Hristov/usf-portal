package com.example.backend.controllers;

import com.example.backend.models.binding.AddVehicleBindingModel;
import com.example.backend.models.service.CreateVehicleServiceModel;
import com.example.backend.service.VehicleService;
import com.example.backend.service.PassengerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/all")
    public String showAllCars(Model model) {

        model.addAttribute("passengers", passengerService.findAllPassengerWithUserId());
        model.addAttribute("vehicles", vehicleService.getAllVehicles());

        return "vehicles";
    }


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
