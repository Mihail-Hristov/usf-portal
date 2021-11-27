package com.example.backend.config;

import com.example.backend.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends GlobalMethodSecurityConfiguration {

    @Autowired
    private UsfPortalMethodSecurityExpressionHandler usfPortalMethodSecurityExpressionHandler;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return usfPortalMethodSecurityExpressionHandler;
    }

    @Bean
    public UsfPortalMethodSecurityExpressionHandler createExpressionHandler(TripService tripService) {
        return new UsfPortalMethodSecurityExpressionHandler(tripService);
    }

}
