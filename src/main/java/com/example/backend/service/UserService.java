package com.example.backend.service;

import com.example.backend.model.entity.Picture;
import com.example.backend.model.entity.User;
import com.example.backend.model.service.UserDetailsServiceModel;
import com.example.backend.model.service.UserEditServiceModel;
import com.example.backend.model.service.UserRegistrationServiceModel;
import com.example.backend.model.view.UserDetailsViewModel;
import com.example.backend.model.view.UserViewModel;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    void initAdmin();

    boolean register(UserRegistrationServiceModel userRequest);

    public void sendRegistrationConfirmationEmail(User user);

    boolean verifyUser(String token) throws Exception;

    Page<UserViewModel> getAllUsersPagination(Integer pageNo, Integer pageSize, String sortBy);

    List<UserViewModel> getAllUsers();

    boolean usernameAlreadyExist(String username);

    User findUserById(String id);

    User getUserByUsername(String username);

    UserDetailsViewModel getUserById(String id);

    UserDetailsViewModel editUserFromAdmin(String id, UserDetailsServiceModel userDetailsServiceModel);

    void editUser(UserEditServiceModel userEditServiceModel, UserDetails principal);

    void setProfilePicture(Picture picture, UserDetails principal);

    void setLastLoginIp(String ip, String username);
}
