package com.example.backend.service.impl;

import com.example.backend.model.entity.UserRole;
import com.example.backend.model.entity.enumeration.UserRoleNameEnum;
import com.example.backend.repository.UserRoleRepository;
import com.example.backend.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void initUsersRole() {

        if (userRoleRepository.count() != 0) {
            return;
        }

        for (UserRoleNameEnum userRoleNameEnum : UserRoleNameEnum.values()) {
            UserRole userRole = new UserRole();
            userRole.setName(userRoleNameEnum);
            userRoleRepository.save(userRole);
        }
    }

    @Override
    public UserRole findUserRoleByName(UserRoleNameEnum name) {
        return userRoleRepository.findAllByName(name);
    }
}
