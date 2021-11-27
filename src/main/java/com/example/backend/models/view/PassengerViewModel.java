package com.example.backend.models.view;

import com.example.backend.models.entity.User;

import java.util.UUID;

public class PassengerViewModel {

    private String id;
    private String name;
    private boolean isDriver;
    private User user;

    public PassengerViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
