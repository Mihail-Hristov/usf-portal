package com.example.backend.service;

import com.example.backend.models.entity.UserRole;
import com.example.backend.models.entity.enumeration.UserRoleNameEnum;

public interface UserRoleService {

    public void initUsersRole();

    public UserRole findUserRoleByName(UserRoleNameEnum name);
}
