package com.example.backend.repository;

import com.example.backend.model.entity.GroupName;
import com.example.backend.model.entity.enumeration.GroupNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupNameRepository extends JpaRepository<GroupName, String> {

    public GroupName findAllByName(GroupNameEnum name);

}
