package com.example.backend.service.impl;

import com.example.backend.model.entity.Country;
import com.example.backend.model.service.CountryCreateServiceModel;
import com.example.backend.model.view.CountryViewModel;
import com.example.backend.repository.CountryRepository;
import com.example.backend.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;
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

    @Override
    public void createNewCountry(CountryCreateServiceModel countryCreateServiceModel) {

        Country country = modelMapper.map(countryCreateServiceModel, Country.class);

        countryRepository.save(country);
    }

    @Override
    public Page<CountryViewModel> getAllCountriesPagination(Integer pageNo, Integer pageSize, String sortBy) {

        PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return countryRepository.findAll(pageable)
                .map(this::asCountryViewModel);

    }

    private CountryViewModel asCountryViewModel(Country country) {
        return modelMapper.map(country, CountryViewModel.class);
    }

    @Override
    public Country getCountryByName(String name) {
        return countryRepository.findCountryByName(name);
    }
}
