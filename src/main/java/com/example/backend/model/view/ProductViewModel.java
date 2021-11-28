package com.example.backend.model.view;

import com.example.backend.model.entity.Colour;
import com.example.backend.model.entity.Picture;

import java.math.BigDecimal;

public class ProductViewModel {

    private String id;
    private String name;
    private String description;
    private Colour colour;
    private BigDecimal price;
    private Picture picture;

    public ProductViewModel() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
