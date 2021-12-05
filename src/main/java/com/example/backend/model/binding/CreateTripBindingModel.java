package com.example.backend.model.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CreateTripBindingModel {

    private String name;
    private String destinationTown;
    private String destinationCountry;
    private LocalDate matchDay;
    private boolean toSentAnEmail;

    public CreateTripBindingModel() {
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
    public String getDestinationTown() {
        return destinationTown;
    }

    public void setDestinationTown(String destinationTown) {
        this.destinationTown = destinationTown;
    }

    @NotNull
    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    @FutureOrPresent(message = "Въведете бъдеща дата!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
