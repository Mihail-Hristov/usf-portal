package com.example.backend.service;

import com.example.backend.models.entity.Country;
import com.example.backend.models.view.CountryViewModel;
import com.example.backend.models.view.TownViewModel;

import java.util.Set;
import java.util.UUID;

public interface CountryService {

    public void initCountries();

    public Set<CountryViewModel> getAllCountries();

    public Country findCountryById(String id);
}
