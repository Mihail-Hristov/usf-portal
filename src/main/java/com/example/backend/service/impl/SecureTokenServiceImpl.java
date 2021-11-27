package com.example.backend.service.impl;

import com.example.backend.models.entity.SecureToken;
import com.example.backend.repository.SecureTokenRepository;
import com.example.backend.service.SecureTokenService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class SecureTokenServiceImpl implements SecureTokenService {

    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(128);
    private static final Charset US_ASCII = StandardCharsets.US_ASCII;

    private static final int tokenValidityInHours = 48;

    private final SecureTokenRepository secureTokenRepository;

    public SecureTokenServiceImpl(SecureTokenRepository secureTokenRepository) {
        this.secureTokenRepository = secureTokenRepository;
    }

    @Override
    public SecureToken createSecureToken() {
        String tokenValue = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), US_ASCII);

        SecureToken secureToken = new SecureToken();
        secureToken.setToken(tokenValue);
        secureToken.setExpireAt(LocalDateTime.now().plusHours(getTokenValidityInHours() + 50));
        this.saveSecureToken(secureToken);
        return secureToken;
    }

    @Override
    public void saveSecureToken(SecureToken token) {
        this.secureTokenRepository.save(token);
    }

    @Override
    public SecureToken findByToken(String token) {
        return this.secureTokenRepository.findFirstByToken(token);
    }

    @Override
    public void removeToken(SecureToken token) {
        this.secureTokenRepository.delete(token);
    }

    @Override
    public void removeTokenByToken(String token) {
        this.secureTokenRepository.removeByToken(token);
    }

    public int getTokenValidityInHours() {
        return tokenValidityInHours;
    }
}
