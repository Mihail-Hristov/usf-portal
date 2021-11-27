package com.example.backend.service.impl;

import com.example.backend.models.entity.Colour;
import com.example.backend.models.entity.enumeration.ColourEnum;
import com.example.backend.models.view.ColourViewModel;
import com.example.backend.repository.ColourRepository;
import com.example.backend.service.ColourService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ColourServiceImpl implements ColourService {

    private final ColourRepository colourRepository;
    private final ModelMapper modelMapper;

    public ColourServiceImpl(ColourRepository colourRepository, ModelMapper modelMapper) {
        this.colourRepository = colourRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initColours() {

        if (colourRepository.count() != 0) {
            return;
        }

        for (ColourEnum colourEnum : ColourEnum.values()) {
            Colour colour = new Colour();
            colour.setName(colourEnum);
            colourRepository.save(colour);
        }
    }

    @Override
    public Set<ColourViewModel> findAllColours() {
        return colourRepository.findAll()
                .stream()
                .map(colour -> modelMapper.map(colour, ColourViewModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Colour findColourByName(ColourEnum name) {
        return colourRepository.findColourByName(name);
    }
}
