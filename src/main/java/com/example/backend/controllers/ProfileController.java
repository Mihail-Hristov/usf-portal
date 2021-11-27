package com.example.backend.controllers;

import com.example.backend.models.binding.PictureBindingModel;
import com.example.backend.models.binding.UserEditBindingModel;
import com.example.backend.models.service.UserEditServiceModel;
import com.example.backend.service.CloudinaryService;
import com.example.backend.service.PictureService;
import com.example.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/portal/profiles")
public class ProfileController {

    private final CloudinaryService cloudinaryService;
    private final PictureService pictureService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public ProfileController(CloudinaryService cloudinaryService, PictureService pictureService, UserService userService, ModelMapper modelMapper) {
        this.cloudinaryService = cloudinaryService;
        this.pictureService = pictureService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/settings")
    public String settingsPage(Model model,
                               @AuthenticationPrincipal UserDetails principal) {

        model.addAttribute("user", userService.getUserByUsername(principal.getUsername()));

        return "settings";
    }

    @PostMapping("/settings/pictures/add")
    public String addPicture(PictureBindingModel pictureBindingModel,
                             @AuthenticationPrincipal UserDetails principal) throws IOException {

        var picture = pictureService.createPicture(pictureBindingModel.getPicture());

        userService.setProfilePicture(pictureService.savePicture(picture), principal);

        return "redirect:/portal";
    }

    @PatchMapping("/settings/edit")
    public String editUser(@Valid UserEditBindingModel userEditBindingModel,
                           BindingResult bindingResult,
                           @AuthenticationPrincipal UserDetails principal) {

        userService.editUser(modelMapper.map(userEditBindingModel, UserEditServiceModel.class), principal);

        return "redirect:/portal/profiles/settings";
    }
}
