package com.GymTrackerBackend.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.GymTrackerBackend.model.VerificationToken;
import com.GymTrackerBackend.repository.VerificationTokenRepository;

@Service
public class VerificationTokenService {

    private final VerificationTokenRepository tokenRepository;

    public VerificationTokenService(VerificationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public VerificationToken createToken(Integer userId) {

        VerificationToken token = new VerificationToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUserId(userId);
        token.setExpiresAt(LocalDateTime.now().plusHours(24));

        return tokenRepository.save(token);
    }

    public VerificationToken getByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}