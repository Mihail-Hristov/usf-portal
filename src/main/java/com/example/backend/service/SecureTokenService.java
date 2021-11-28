package com.example.backend.service;

import com.example.backend.model.entity.SecureToken;

public interface SecureTokenService {

    public SecureToken createSecureToken();

    public void saveSecureToken(SecureToken token);

    public SecureToken findByToken(String token);

    public void removeToken(SecureToken token);

    public void removeTokenByToken(String token);

    public void deleteAllExpiredSecureTokens();
}
