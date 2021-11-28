package com.example.backend.model.service;

import java.util.List;

public class TripOrganizationServiceModel {

    private List<CarsOrganisationsServiceModel> carsOrganisations;
    private List<String> passengers;

    public TripOrganizationServiceModel() {
    }

    public List<CarsOrganisationsServiceModel> getCarsOrganisations() {
        return carsOrganisations;
    }

    public void setCarsOrganisations(List<CarsOrganisationsServiceModel> carsOrganisations) {
        this.carsOrganisations = carsOrganisations;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<String> passengers) {
        this.passengers = passengers;
    }
}
