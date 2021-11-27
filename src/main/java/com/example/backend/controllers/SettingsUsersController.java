package com.example.backend.controllers;

import com.example.backend.models.binding.UserDetailsBindingModel;
import com.example.backend.models.entity.enumeration.GroupNameEnum;
import com.example.backend.models.service.UserDetailsServiceModel;
import com.example.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/portal/settings")
public class SettingsUsersController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public SettingsUsersController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/users")
    public String users(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "users";
    }

    @GetMapping("/users/details/{id}")
    public String userDetails(@PathVariable String id, Model model) {

        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("groupName", GroupNameEnum.values());

        Set<String> roles = userService.findUserById(id)
                .getRoles()
                .stream()
                .map(userRole -> userRole.getName().name())
                .collect(Collectors.toSet());

        model.addAttribute("roles", roles);

        return"user-details";
    }

    @PatchMapping ("/users/details/{id}")
    public String userDetails(@Valid UserDetailsBindingModel userDetailsBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              @PathVariable String id) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userDetailsBindingModel", userDetailsBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDetailsBindingModel", bindingResult);

            return "redirect:/settings/users/details/" + id;
        }

        userService.editUserFromAdmin(id, modelMapper.map(userDetailsBindingModel, UserDetailsServiceModel.class));

        return "redirect:/portal/settings/users";
    }
}
