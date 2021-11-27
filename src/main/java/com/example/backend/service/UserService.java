package com.example.backend.service;

import com.example.backend.models.binding.UserDetailsBindingModel;
import com.example.backend.models.entity.Picture;
import com.example.backend.models.entity.User;
import com.example.backend.models.service.UserDetailsServiceModel;
import com.example.backend.models.service.UserEditServiceModel;
import com.example.backend.models.service.UserLoginServiceModel;
import com.example.backend.models.service.UserRegistrationServiceModel;
import com.example.backend.models.view.UserDetailsViewModel;
import com.example.backend.models.view.UserViewModel;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    boolean register(UserRegistrationServiceModel userRequest);

    public void sendRegistrationConfirmationEmail(User user);

    boolean verifyUser(String token) throws Exception;

    Page<UserViewModel> getAllUsersToDisplay(Integer pageNo, Integer pageSize, String sortBy);

    List<UserViewModel> getAllUsers();

    boolean usernameAlreadyExist(String username);

    User findUserById(String id);

    User getUserByUsername(String username);

    UserDetailsViewModel getUserById(String id);

    UserDetailsViewModel editUserFromAdmin(String id, UserDetailsServiceModel userDetailsServiceModel);

    void editUser(UserEditServiceModel userEditServiceModel, UserDetails principal);

    void setProfilePicture(Picture picture, UserDetails principal);
}
