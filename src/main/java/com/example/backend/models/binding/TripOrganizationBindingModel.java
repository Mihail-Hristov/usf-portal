package com.example.backend.models.binding;

import java.util.List;

public class TripOrganizationBindingModel {

    private List<CarsOrganisationsBindingModel> carsOrganisations;
    private List<String> passengers;

    public TripOrganizationBindingModel() {
    }

    public List<CarsOrganisationsBindingModel> getCarsOrganisations() {
        return carsOrganisations;
    }

    public void setCarsOrganisations(List<CarsOrganisationsBindingModel> carsOrganisations) {
        this.carsOrganisations = carsOrganisations;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<String> passengers) {
        this.passengers = passengers;
    }
}
