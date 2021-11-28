package com.example.backend.models.view;

import com.example.backend.models.entity.Country;

import java.util.UUID;

public class TownViewModel {

    private String id;
    private String name;
    private Country country;

    public TownViewModel() {
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
