package com.example.backend.repository;

import com.example.backend.model.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TownRepository extends JpaRepository<Town, String> {

    public Set<Town> findAllByOrderByNameAsc();

    public Town findAllById(String id);
}
