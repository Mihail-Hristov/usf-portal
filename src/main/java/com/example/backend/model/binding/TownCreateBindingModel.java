package com.example.backend.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TownCreateBindingModel {

    private String name;
    private String country;

    public TownCreateBindingModel() {
    }

    @NotNull
    @Size(min = 3, max = 20, message = "Въведе име между 3 и 20 символа!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
