package com.example.backend.repository;

import com.example.backend.models.entity.Colour;
import com.example.backend.models.entity.enumeration.ColourEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ColourRepository extends JpaRepository<Colour, String> {

    public Colour findColourByName(ColourEnum name);
}
