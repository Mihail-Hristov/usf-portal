package com.example.backend.service.impl;

import com.example.backend.exceprtion.ObjectNotFoundException;
import com.example.backend.model.entity.User;
import com.example.backend.service.IpStatsService;
import com.example.backend.service.SecureTokenService;
import com.example.backend.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class IpStatsServiceImpl implements IpStatsService {

    private final UserService userService;

    public IpStatsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void saveLastIp(String ip) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            userService.setLastLoginIp(ip, authentication.getName());
        }
    }
}
