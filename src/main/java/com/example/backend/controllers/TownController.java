package com.example.backend.controllers;

import com.example.backend.model.binding.TownCreateBindingModel;
import com.example.backend.model.service.TownCreateServiceModel;
import com.example.backend.service.CountryService;
import com.example.backend.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/portal/towns")
public class TownController {

    private final TownService townService;
    private final CountryService countryService;
    private final ModelMapper modelMapper;

    public TownController(TownService townService, CountryService countryService, ModelMapper modelMapper) {
        this.townService = townService;
        this.countryService = countryService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public TownCreateBindingModel townCreateBindingModel() {
        return new TownCreateBindingModel();
    }

    @PreAuthorize("hasRole('TRIP_ADMIN')")
    @GetMapping("/all")
    public String towns(Model model,
                         @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
                         @RequestParam(name = "pageSize", defaultValue = "6") Integer pageSize,
                         @RequestParam(name = "sortBy", defaultValue = "name") String sortBy) {

        model.addAttribute("towns", townService.getAllTownPagination(pageNo, pageSize, sortBy));
        model.addAttribute("destinationCountry", countryService.getAllCountries());

        return "towns";
    }

    @PreAuthorize("hasRole('TRIP_ADMIN')")
    @PostMapping("/create")
    public String createTown(@Valid TownCreateBindingModel townCreateBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("townCreateBindingModel", townCreateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.townCreateBindingModel", bindingResult);

            return "redirect:/portal/towns/all";
        }

        townService.createNewTown(modelMapper.map(townCreateBindingModel, TownCreateServiceModel.class));

        return "redirect:/portal/towns/all";
    }
}
