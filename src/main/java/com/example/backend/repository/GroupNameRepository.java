package com.example.backend.repository;

import com.example.backend.models.entity.GroupName;
import com.example.backend.models.entity.enumeration.GroupNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroupNameRepository extends JpaRepository<GroupName, String> {

    public GroupName findAllByName(GroupNameEnum name);

}
