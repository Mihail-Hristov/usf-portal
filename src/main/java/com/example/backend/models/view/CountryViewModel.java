package com.example.backend.models.view;

import java.util.UUID;

public class CountryViewModel {

    private String id;
    private String name;

    public CountryViewModel() {
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
}
