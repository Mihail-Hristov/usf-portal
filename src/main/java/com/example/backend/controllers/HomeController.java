package com.example.backend.controllers;

import com.example.backend.service.ProductService;
import com.example.backend.service.TripService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final TripService tripService;
    private final ProductService productService;

    public HomeController(TripService tripService, ProductService productService) {
        this.tripService = tripService;
        this.productService = productService;
    }

    @GetMapping("/portal")
    public String portalHome(Model model,
                             @AuthenticationPrincipal UserDetails principal) {

        model.addAttribute("upcomingTrips", tripService.findFirstTwoUpcomingTrips());
        model.addAttribute("myProducts", productService.getAllProducts());

        return "portal-home";
    }

}
