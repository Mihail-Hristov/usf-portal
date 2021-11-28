package com.example.backend.service;

import com.example.backend.models.entity.Country;
import com.example.backend.models.service.CountryCreateServiceModel;
import com.example.backend.models.view.CountryViewModel;
import com.example.backend.models.view.TownViewModel;
import org.springframework.data.domain.Page;

import java.util.Set;
import java.util.UUID;

public interface CountryService {

    public void initCountries();

    public Set<CountryViewModel> getAllCountries();

    public Country findCountryById(String id);

    public void createNewCountry(CountryCreateServiceModel countryCreateServiceModel);

    public Page<CountryViewModel> getAllCountriesPagination(Integer pageNo, Integer pageSize, String sortBy);
}
