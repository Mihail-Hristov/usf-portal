package com.example.backend.model.entity;

import com.example.backend.model.entity.enumeration.GroupNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "group_names")
public class GroupName extends BaseEntity{

    private GroupNameEnum name;

    public GroupName() {
    }

    @Enumerated(EnumType.STRING)
    public GroupNameEnum getName() {
        return name;
    }

    public void setName(GroupNameEnum name) {
        this.name = name;
    }
}

