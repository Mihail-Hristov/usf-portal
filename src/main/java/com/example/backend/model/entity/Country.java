package com.example.backend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity{

    private String name;

    public Country() {
    }

    @Column(nullable = false, unique = true, length = 25)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
