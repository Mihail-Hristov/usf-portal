package com.example.backend.repository;

import com.example.backend.models.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    public Set<Country> findAllByOrderByNameAsc();

    public Country findAllById(String id);
}
