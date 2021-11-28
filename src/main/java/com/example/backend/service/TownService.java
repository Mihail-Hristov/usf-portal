package com.example.backend.service;

import com.example.backend.model.entity.Town;
import com.example.backend.model.service.TownCreateServiceModel;
import com.example.backend.model.view.TownViewModel;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface TownService {

    public void initTowns();

    public Set<TownViewModel> getAllTowns();

    public Town findTownById(String id);

    public Page<TownViewModel> getAllTownPagination(Integer pageNo, Integer pageSize, String sortBy);

    public void createNewTown(TownCreateServiceModel townCreateServiceModel);
}
