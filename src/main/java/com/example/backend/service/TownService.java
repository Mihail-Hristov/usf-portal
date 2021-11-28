package com.example.backend.service;

import com.example.backend.models.entity.Town;
import com.example.backend.models.service.TownCreateServiceModel;
import com.example.backend.models.view.TownViewModel;
import org.springframework.data.domain.Page;

import java.util.Set;
import java.util.UUID;

public interface TownService {

    public void initTowns();

    public Set<TownViewModel> getAllTowns();

    public Town findTownById(String id);

    public Page<TownViewModel> getAllTownPagination(Integer pageNo, Integer pageSize, String sortBy);

    public void createNewTown(TownCreateServiceModel townCreateServiceModel);
}
