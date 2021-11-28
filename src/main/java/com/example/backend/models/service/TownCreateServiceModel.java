package com.example.backend.models.service;

import com.example.backend.models.entity.Country;

public class TownCreateServiceModel {

    private String name;
    private String country;

    public TownCreateServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
