package com.example.backend.controllers;

import com.example.backend.models.binding.UserLoginBindingModel;
import com.example.backend.models.binding.UserRegistrationBindingModel;
import com.example.backend.models.entity.enumeration.GroupNameEnum;
import com.example.backend.models.service.UserLoginServiceModel;
import com.example.backend.models.service.UserRegistrationServiceModel;
import com.example.backend.service.UserService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;

@Controller
@RequestMapping("/portal/users")
public class UserController {

    private final UserService userService;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, Gson gson, ModelMapper modelMapper) {
        this.userService = userService;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userModel")
    public UserRegistrationBindingModel userModel() {
        return new UserRegistrationBindingModel();
    }

    @ModelAttribute("userLoginModel")
    public UserLoginBindingModel userLoginModel() {
        return new UserLoginBindingModel();
    }


    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("groupName", GroupNameEnum.values());

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationBindingModel userRequest,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userRequest);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            return "redirect:/users/register";
        }

        UserRegistrationServiceModel serviceModel = modelMapper
                .map(userRequest, UserRegistrationServiceModel.class);

        userService.register(serviceModel);

        return "redirect:/users/login";
    }

    @GetMapping("/register/verify")
    public String verifyUser(@RequestParam(required = false) String token) {
        if(StringUtils.isEmpty(token)) {
            return "Token error!";
        }
        try {
            userService.verifyUser(token);
        }catch (Exception e) {
            return "Verify error!";
        }

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }
}
