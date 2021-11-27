package com.example.backend.service.impl;

import com.example.backend.models.entity.Country;
import com.example.backend.models.view.CountryViewModel;
import com.example.backend.repository.CountryRepository;
import com.example.backend.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void initCountries() {

        if (countryRepository.count() != 0) {
            return;
        }

        Country country = new Country();
        country.setName("България");

        countryRepository.save(country);
    }

    @Override
    public Set<CountryViewModel> getAllCountries() {
        return countryRepository.findAllByOrderByNameAsc()
                .stream()
                .map(country -> modelMapper.map(country, CountryViewModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Country findCountryById(String id) {
        return countryRepository.findAllById(id);
    }
}
