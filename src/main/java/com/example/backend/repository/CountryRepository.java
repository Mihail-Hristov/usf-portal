package com.example.backend.repository;

import com.example.backend.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    public Set<Country> findAllByOrderByNameAsc();

    public Country findAllById(String id);

    Country findCountryByName(String name);
}
