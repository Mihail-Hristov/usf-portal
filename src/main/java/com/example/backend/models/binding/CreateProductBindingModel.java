package com.example.backend.models.binding;


import com.example.backend.models.entity.enumeration.ColourEnum;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class CreateProductBindingModel {

    private String name;
    private String description;
    private ColourEnum colour;
    private BigDecimal price;
    private MultipartFile picture;
    private int xs;
    private int s;
    private int m;
    private int l;
    private int xl;
    private int xxl;
    private boolean toSentAnEmail;

    public CreateProductBindingModel() {
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

    public ColourEnum getColour() {
        return colour;
    }

    public void setColour(ColourEnum colour) {
        this.colour = colour;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public int getXs() {
        return xs;
    }

    public void setXs(int xs) {
        this.xs = xs;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getXl() {
        return xl;
    }

    public void setXl(int xl) {
        this.xl = xl;
    }

    public int getXxl() {
        return xxl;
    }

    public void setXxl(int xxl) {
        this.xxl = xxl;
    }

    public boolean isToSentAnEmail() {
        return toSentAnEmail;
    }

    public void setToSentAnEmail(boolean toSentAnEmail) {
        this.toSentAnEmail = toSentAnEmail;
    }
}
