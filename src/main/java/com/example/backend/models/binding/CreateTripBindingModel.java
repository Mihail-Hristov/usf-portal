package com.example.backend.models.binding;

import com.example.backend.models.entity.Country;
import com.example.backend.models.entity.Town;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.UUID;

public class CreateTripBindingModel {

    private String name;
    private String destinationTown;
    private String destinationCountry;
    private LocalDate matchDay;
    private boolean toSentAnEmail;

    public CreateTripBindingModel() {
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

    @FutureOrPresent
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
