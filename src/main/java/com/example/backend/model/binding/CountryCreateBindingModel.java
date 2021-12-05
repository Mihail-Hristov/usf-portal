package com.example.backend.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryCreateBindingModel {

    private String name;

    public CountryCreateBindingModel() {
    }

    @NotNull
    @Size(min = 3, message = "Задължително е да посочите име, което е поне 3 символа!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
