package com.example.backend.service.impl;

import com.example.backend.models.entity.Town;
import com.example.backend.models.view.TownViewModel;
import com.example.backend.repository.TownRepository;
import com.example.backend.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initTowns() {

        if (townRepository.count() != 0) {
            return;
        }

        Town town = new Town();
        town.setName("Стара Загора");

        Town town2 = new Town();
        town2.setName("Варна");

        Town town3 = new Town();
        town3.setName("Пловдив");

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
}
