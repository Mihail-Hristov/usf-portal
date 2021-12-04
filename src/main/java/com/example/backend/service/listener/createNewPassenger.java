package com.example.backend.service.listener;

import com.example.backend.model.entity.User;
import com.example.backend.service.PassengerService;
import com.example.backend.service.event.UserCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class createNewPassenger {

    private final PassengerService passengerService;

    public createNewPassenger(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @EventListener
    public void onUserRegister(UserCreatedEvent event) {

        passengerService.addNewPassenger(event.getUserRequest(), event.getUser());
    }
}
