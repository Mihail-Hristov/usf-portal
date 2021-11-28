package com.example.backend.service;

import com.example.backend.model.entity.Colour;
import com.example.backend.model.entity.enumeration.ColourEnum;
import com.example.backend.model.view.ColourViewModel;

import java.util.Set;

public interface ColourService {

    public void initColours();

    public Set<ColourViewModel> findAllColours();

    public Colour findColourByName(ColourEnum name);
}
