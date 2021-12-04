package com.example.backend.service.event;

import com.example.backend.model.entity.User;
import com.example.backend.model.service.UserRegistrationServiceModel;
import org.springframework.context.ApplicationEvent;

public class UserCreatedEvent extends ApplicationEvent {

    private User user;
    private UserRegistrationServiceModel userRequest;

    public UserCreatedEvent(Object source, User user, UserRegistrationServiceModel userRequest) {
        super(source);
        this.user = user;
        this.userRequest = userRequest;
    }

    public User getUser() {
        return user;
    }

    public UserRegistrationServiceModel getUserRequest() {
        return userRequest;
    }
}
