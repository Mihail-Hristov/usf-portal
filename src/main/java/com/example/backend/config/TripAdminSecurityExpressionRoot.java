package com.example.backend.config;


import com.example.backend.models.entity.enumeration.UserRoleNameEnum;
import com.example.backend.service.TripService;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class TripAdminSecurityExpressionRoot extends SecurityExpressionRoot implements
        MethodSecurityExpressionOperations {

    private TripService tripService;
    private Object filterObject;
    private Object returnObject;

    public TripAdminSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean isTripAdmin(String id) {
        Collection<? extends GrantedAuthority> authorities = currentUserName();

        authorities
                .stream()
                .anyMatch(a -> a.equals(UserRoleNameEnum.TRIP_ADMIN));

        return true;
    }

    private Collection<? extends GrantedAuthority> currentUserName() {
        Authentication authentication = getAuthentication();

        if (authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getAuthorities();
        }

        return null;
    }

    public void setTripService(TripService tripService) {
        this.tripService = tripService;
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }
}
