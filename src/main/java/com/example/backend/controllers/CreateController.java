package com.example.backend.controllers;

import com.example.backend.models.binding.AddVehicleBindingModel;
import com.example.backend.service.VehicleService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/portal/create")
public class CreateController {

    private final VehicleService vehicleService;
    private final Gson gson;

    public CreateController(VehicleService vehicleService, Gson gson) {
        this.vehicleService = vehicleService;
        this.gson = gson;
    }

    @ModelAttribute
    private AddVehicleBindingModel addCarBindingModel() {
        return new AddVehicleBindingModel();
    }




}
