package com.example.backend.model.entity;

import com.example.backend.model.entity.enumeration.UserRoleNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{

    private UserRoleNameEnum name;

    public UserRole() {
    }

    @Enumerated(EnumType.STRING)
    public UserRoleNameEnum getName() {
        return name;
    }

    public void setName(UserRoleNameEnum name) {
        this.name = name;
    }
}
