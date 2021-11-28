package com.example.backend.controllers.interceptor;

import com.example.backend.service.IpStatsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final IpStatsService ipStatsService;

    public LoginInterceptor(IpStatsService ipStatsService) {
        this.ipStatsService = ipStatsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String ipAddress = request.getHeader("X-Forward-For");

        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        ipStatsService.saveLastIp(ipAddress);

        return true;
    }
}
