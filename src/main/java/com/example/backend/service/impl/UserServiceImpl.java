package com.example.backend.service.impl;

import com.example.backend.exceprtion.ObjectNotFoundException;
import com.example.backend.model.entity.*;
import com.example.backend.model.entity.enumeration.GroupNameEnum;
import com.example.backend.model.entity.enumeration.UserRoleNameEnum;
import com.example.backend.model.service.UserDetailsServiceModel;
import com.example.backend.model.service.UserEditServiceModel;
import com.example.backend.model.service.UserRegistrationServiceModel;
import com.example.backend.model.view.UserDetailsViewModel;
import com.example.backend.model.view.UserViewModel;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.*;
import com.example.backend.service.event.UserCreatedEvent;
import com.example.backend.util.AccountVerificationEmailContext;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;
    private final GroupNameService groupNameService;
    private final ModelMapper modelMapper;
    private final EmailService emailService;
    private final SecureTokenService secureTokenService;
    private final PassengerService passengerService;
    private final SpringTemplateEngine templateEngine;
    private final ApplicationEventPublisher eventPublisher;
    private final Environment env;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleService userRoleService, GroupNameService groupNameService, ModelMapper modelMapper, EmailService emailService, SecureTokenService secureTokenService, PassengerService passengerService, SpringTemplateEngine templateEngine, ApplicationEventPublisher eventPublisher, Environment env) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
        this.groupNameService = groupNameService;
        this.modelMapper = modelMapper;
        this.emailService = emailService;
        this.secureTokenService = secureTokenService;
        this.passengerService = passengerService;
        this.templateEngine = templateEngine;
        this.eventPublisher = eventPublisher;
        this.env = env;
    }

    @Override
    @Transactional
    public boolean verifyUser(String token) throws Exception {

        SecureToken secureToken = secureTokenService.findByToken(token);

        if (Objects.isNull(secureToken) || !token.equals(secureToken.getToken()) || secureToken.isExpired()) {
            throw new Exception("Token is not valid!");
        }

        User user  = secureToken.getUser();

        user.setActive(true);
        userRepository.save(user);

        secureTokenService.removeTokenByToken(token);
        return true;
    }

    @Override
    @Transactional
    public boolean register(UserRegistrationServiceModel userRequest) {

        User user = modelMapper.map(userRequest, User.class);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setActive(false);

        user.getRoles().add(userRoleService.findUserRoleByName(UserRoleNameEnum.GUEST));

        if (userRequest.getGroupName().equals("USF")) {
            user.getRoles().add(userRoleService.findUserRoleByName(UserRoleNameEnum.USF_MEMBER));
            user.setGroupName(groupNameService.findGroupByName(GroupNameEnum.USF));
        }else {
            switch (userRequest.getGroupName()) {
                case "BET_BOYS" -> {
                    user.getRoles().add(userRoleService.findUserRoleByName(UserRoleNameEnum.GROUP_MEMBER));
                    user.setGroupName(groupNameService.findGroupByName(GroupNameEnum.BET_BOYS));
                }
                case "RED_DOGS" -> {
                    user.getRoles().add(userRoleService.findUserRoleByName(UserRoleNameEnum.GROUP_MEMBER));
                    user.setGroupName(groupNameService.findGroupByName(GroupNameEnum.RED_DOGS));
                }
                case "OTHER" -> {
                    user.getRoles().add(userRoleService.findUserRoleByName(UserRoleNameEnum.GUEST));
                    user.setGroupName(groupNameService.findGroupByName(GroupNameEnum.OTHER));
                }
            }
        }

        this.userRepository.save(user);

        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(this, user, userRequest);
        eventPublisher.publishEvent(userCreatedEvent);

        sendRegistrationConfirmationEmail(user);

        return true;
    }

    @Override
    public void sendRegistrationConfirmationEmail(User user) {
        SecureToken secureToken = secureTokenService.createSecureToken();
        secureToken.setUser(user);
        secureTokenService.saveSecureToken(secureToken);

        AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext(env);

        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());

        Context context = new Context();
        context.setVariable("name", user.getFirstName());
        String baseURL = "http://localhost:8080";
        context.setVariable("URL", emailContext.buildVerificationUrl(baseURL, secureToken.getToken()));

        emailContext.setTemplateLocation(templateEngine.process("emailForRegistration", context));


        try {
            emailService.sendEmail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Page<UserViewModel> getAllUsersPagination(Integer pageNo, Integer pageSize, String sortBy) {

        PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return userRepository.findAll(pageable)
                .map(this::asUserViewModel);
    }

    private UserViewModel asUserViewModel(User user) {
        return modelMapper.map(user, UserViewModel.class);
    }

    @Override
    public List<UserViewModel> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean usernameAlreadyExist(String email) {
        return userRepository.existsUserByUsernameIgnoreCase(email);
    }

    @Override
    public User findUserById(String id) {

        User user = userRepository.findFirstById(id).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException(id);
        }

        return user;
    }

    @Override
    public User getUserByUsername(String username) {

        User user = userRepository.findFirstByUsername(username).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException(username);
        }

        return user;
    }

    @Override
    public UserDetailsViewModel getUserById(String id) {

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException(id);
        }

        return modelMapper.map(user, UserDetailsViewModel.class);
    }

    @Override
    public UserDetailsViewModel editUserFromAdmin(String userId, UserDetailsServiceModel userRequest) {

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException(userId);
        }

        user.setUsername(userRequest.getUsername());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setNickname(userRequest.getNickname());

        GroupName groupName = groupNameService.findGroupByName(userRequest.getGroupName());
        user.setGroupName(groupName);

        this.serUserRoles(user, userRequest);

        user.setActive(userRequest.isActive());

        userRepository.save(user);

        return modelMapper.map(user, UserDetailsViewModel.class);
    }

    @Override
    public void setProfilePicture(Picture picture, UserDetails principal) {
        User user = userRepository.findFirstByUsername(principal.getUsername()).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException(principal.getUsername());
        }

        user.setProfilePicture(picture);
        userRepository.save(user);
    }

    @Override
    public void editUser(UserEditServiceModel userEditServiceModel, UserDetails principal) {
        User user = userRepository.findFirstByUsername(principal.getUsername()).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException(principal.getUsername());
        }

        user.setNickname(userEditServiceModel.getNickname());
        user.setFirstName(userEditServiceModel.getFirstName());
        user.setLastName(userEditServiceModel.getLastName());

        userRepository.save(user);
    }

    private User serUserRoles(User user, UserDetailsServiceModel userRequest) {

        UserRole userRoleTripAdmin = userRoleService.findUserRoleByName(UserRoleNameEnum.TRIP_ADMIN);
        UserRole userRoleMerchAdmin = userRoleService.findUserRoleByName(UserRoleNameEnum.MERCH_ADMIN);
        UserRole userRoleFeeAdmin = userRoleService.findUserRoleByName(UserRoleNameEnum.MEMBERSHIP_FEE_ADMIN);

        if (userRequest.isTripAdmin()) {
            user.getRoles().add(userRoleTripAdmin);
        }else if(user.getRoles().contains(userRoleTripAdmin)) {
            user.getRoles().remove(userRoleTripAdmin);
        }

        if (userRequest.isMerchAdmin()) {
            user.getRoles().add(userRoleMerchAdmin);
        }else if(user.getRoles().contains(userRoleMerchAdmin)) {
            user.getRoles().remove(userRoleMerchAdmin);
        }

        if (userRequest.isMembershipFeeAdmin()) {
            user.getRoles().add(userRoleFeeAdmin);
        }else if(user.getRoles().contains(userRoleFeeAdmin)) {
            user.getRoles().remove(userRoleFeeAdmin);
        }

        return user;
    }

    @Override
    public void setLastLoginIp(String ip, String username) {
        User user = getUserByUsername(username);

        if (user == null) {
            throw new ObjectNotFoundException(username);
        }

        user.setLastLoginFromIp(ip);
        userRepository.save(user);
    }
}
