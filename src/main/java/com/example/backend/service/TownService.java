package com.example.backend.service;

import com.example.backend.models.entity.Town;
import com.example.backend.models.view.TownViewModel;

import java.util.Set;
import java.util.UUID;

public interface TownService {

    public void initTowns();
    public Set<TownViewModel> getAllTowns();
    public Town findTownById(String id);
}
