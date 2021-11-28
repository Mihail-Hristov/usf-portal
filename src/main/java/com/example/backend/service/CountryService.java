package com.example.backend.service;

import com.example.backend.model.entity.Country;
import com.example.backend.model.service.CountryCreateServiceModel;
import com.example.backend.model.view.CountryViewModel;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface CountryService {

    public void initCountries();

    public Set<CountryViewModel> getAllCountries();

    public Country findCountryById(String id);

    public void createNewCountry(CountryCreateServiceModel countryCreateServiceModel);

    public Page<CountryViewModel> getAllCountriesPagination(Integer pageNo, Integer pageSize, String sortBy);
}
