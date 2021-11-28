package com.example.backend.controllers;

import com.example.backend.model.binding.CountryCreateBindingModel;
import com.example.backend.model.service.CountryCreateServiceModel;
import com.example.backend.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/portal/countries")
public class CountryController {

    private final CountryService countryService;
    private final ModelMapper modelMapper;

    public CountryController(CountryService countryService, ModelMapper modelMapper) {
        this.countryService = countryService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public CountryCreateBindingModel countryCreateBindingModel() {
        return new CountryCreateBindingModel();
    }

    @PreAuthorize("hasRole('TRIP_ADMIN')")
    @GetMapping("/all")
    public String countries(Model model,
                            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
                            @RequestParam(name = "pageSize", defaultValue = "4") Integer pageSize,
                            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy) {

        model.addAttribute("countries", countryService.getAllCountriesPagination(pageNo, pageSize, sortBy));

        return "countries";
    }

    @PreAuthorize("hasRole('TRIP_ADMIN')")
    @PostMapping("/create")
    public String createCountry(@Valid CountryCreateBindingModel countryCreateBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("countryCreateBindingModel", countryCreateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.countryCreateBindingModel", bindingResult);
        }

        countryService.createNewCountry(modelMapper.map(countryCreateBindingModel, CountryCreateServiceModel.class));

        return "redirect:/portal/countries/all";
    }
}
