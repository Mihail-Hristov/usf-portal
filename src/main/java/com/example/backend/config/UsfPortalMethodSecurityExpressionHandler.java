package com.example.backend.config;

import com.example.backend.service.TripService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class UsfPortalMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final TripService tripService;

    public UsfPortalMethodSecurityExpressionHandler(TripService tripService) {
        this.tripService = tripService;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
            Authentication authentication, MethodInvocation invocation) {

        TripAdminSecurityExpressionRoot root = new TripAdminSecurityExpressionRoot(authentication);

        root.setTripService(tripService);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(getTrustResolver());
        root.setRoleHierarchy(getRoleHierarchy());

        return root;
    }
}
