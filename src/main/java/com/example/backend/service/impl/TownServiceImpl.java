package com.example.backend.service.impl;

import com.example.backend.model.entity.Country;
import com.example.backend.model.entity.Town;
import com.example.backend.model.service.TownCreateServiceModel;
import com.example.backend.model.view.TownViewModel;
import com.example.backend.repository.TownRepository;
import com.example.backend.service.CountryService;
import com.example.backend.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final CountryService countryService;
    private final ModelMapper modelMapper;

    public TownServiceImpl(TownRepository townRepository, CountryService countryService, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.countryService = countryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initTowns() {

        if (townRepository.count() != 0) {
            return;
        }

        Country country = countryService.getCountryByName("България");

        Town town = new Town();
        town.setName("Стара Загора");
        town.setCountry(country);

        Town town2 = new Town();
        town2.setName("Варна");
        town2.setCountry(country);

        Town town3 = new Town();
        town3.setName("Пловдив");
        town3.setCountry(country);

        townRepository.save(town);
        townRepository.save(town2);
        townRepository.save(town3);
    }

    @Override
    public Set<TownViewModel> getAllTowns() {
        return townRepository.findAllByOrderByNameAsc()
                .stream()
                .map(town -> modelMapper.map(town, TownViewModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Town findTownById(String id) {
        return townRepository.findAllById(id);
    }

    @Override
    public Page<TownViewModel> getAllTownPagination(Integer pageNo, Integer pageSize, String sortBy) {

        PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return townRepository.findAll(pageable)
                .map(this::asTownViewModel);
    }

    private TownViewModel asTownViewModel(Town town) {
        return modelMapper.map(town, TownViewModel.class);
    }

    @Override
    public void createNewTown(TownCreateServiceModel townCreateServiceModel) {

        Town town = modelMapper.map(townCreateServiceModel, Town.class);
        Country country = countryService.findCountryById(townCreateServiceModel.getCountry());

        town.setCountry(country);

        townRepository.save(town);
    }
}
