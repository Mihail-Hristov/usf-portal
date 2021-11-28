package com.example.backend.util;

import com.example.backend.model.entity.SecureToken;
import com.example.backend.service.SecureTokenService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Schedule {

    private final SecureTokenService secureTokenService;

    public Schedule(SecureTokenService secureTokenService) {
        this.secureTokenService = secureTokenService;
    }

    @Scheduled(cron = "0 34 22 * * 7")
    public void deleteExpiredSecureToken() {
        secureTokenService.deleteAllExpiredSecureTokens();
    }

}
