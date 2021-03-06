package com.example.backend.controllers;

import com.example.backend.model.binding.UserLoginBindingModel;
import com.example.backend.model.binding.UserRegistrationBindingModel;
import com.example.backend.model.entity.enumeration.GroupNameEnum;
import com.example.backend.model.service.UserRegistrationServiceModel;
import com.example.backend.service.UserService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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

    @ModelAttribute
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
    public String register(@Valid UserRegistrationBindingModel userRegistrationBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationBindingModel", bindingResult);

            return "redirect:/portal/users/register";
        }

        UserRegistrationServiceModel serviceModel = modelMapper
                .map(userRegistrationBindingModel, UserRegistrationServiceModel.class);

        userService.register(serviceModel);

        return "redirect:/portal/users/login";
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

        return "redirect:/portal/users/login";
    }

    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }

    @PostMapping("/login-error")
    public String loginError(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
            String username,
            RedirectAttributes redirectAttributes
            ) {

        redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:/portal/users/login";
    }
}
