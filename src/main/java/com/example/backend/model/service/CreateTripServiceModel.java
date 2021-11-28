package com.example.backend.model.service;

import java.time.LocalDate;

public class CreateTripServiceModel {

    private String name;
    private String destinationTown;
    private String destinationCountry;
    private LocalDate matchDay;
    private boolean toSentAnEmail;

    public CreateTripServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestinationTown() {
        return destinationTown;
    }

    public void setDestinationTown(String destinationTown) {
        this.destinationTown = destinationTown;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public LocalDate getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(LocalDate matchDay) {
        this.matchDay = matchDay;
    }

    public boolean isToSentAnEmail() {
        return toSentAnEmail;
    }

    public void setToSentAnEmail(boolean toSentAnEmail) {
        this.toSentAnEmail = toSentAnEmail;
    }
}
