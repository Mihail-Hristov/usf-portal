package com.example.backend.repository;

import com.example.backend.model.entity.Colour;
import com.example.backend.model.entity.enumeration.ColourEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColourRepository extends JpaRepository<Colour, String> {

    public Colour findColourByName(ColourEnum name);
}
