package com.example.backend.init;

import com.example.backend.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final GroupNameService groupNameService;
    private final UserRoleService userRoleService;
    private final CountryService countryService;
    private final TownService townService;
    private final ColourService colourService;
    private final UserService userService;
    private final PassengerService passengerService;

    public DBInit(GroupNameService groupNameService, UserRoleService userRoleService, CountryService countryService, TownService townService, ColourService colourService, UserService userService, PassengerService passengerService) {
        this.groupNameService = groupNameService;
        this.userRoleService = userRoleService;
        this.countryService = countryService;
        this.townService = townService;
        this.colourService = colourService;
        this.userService = userService;
        this.passengerService = passengerService;
    }

    @Override
    public void run(String... args) throws Exception {
        groupNameService.initGroups();
        userRoleService.initUsersRole();
        countryService.initCountries();
        townService.initTowns();
        colourService.initColours();
        userService.initAdmin();
        passengerService.initPassengers();
    }
}
