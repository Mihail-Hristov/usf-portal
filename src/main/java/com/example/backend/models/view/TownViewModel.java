package com.example.backend.models.view;

import java.util.UUID;

public class TownViewModel {

    private String id;
    private String name;

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
}
