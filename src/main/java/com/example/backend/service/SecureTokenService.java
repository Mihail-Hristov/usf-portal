package com.example.backend.service;

import com.example.backend.models.entity.SecureToken;

public interface SecureTokenService {

    public SecureToken createSecureToken();

    public void saveSecureToken(SecureToken token);

    public SecureToken findByToken(String token);

    public void removeToken(SecureToken token);

    public void removeTokenByToken(String token);
}
