package com.example.backend.model.entity;

import com.example.backend.model.entity.enumeration.ColourEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "product_colours")
public class Colour extends BaseEntity{

    private ColourEnum name;

    public Colour() {
    }

    @Enumerated(EnumType.STRING)
    public ColourEnum getName() {
        return name;
    }

    public void setName(ColourEnum name) {
        this.name = name;
    }
}
