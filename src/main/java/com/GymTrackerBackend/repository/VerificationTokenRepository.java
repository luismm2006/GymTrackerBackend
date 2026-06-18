package com.GymTrackerBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GymTrackerBackend.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
	VerificationToken findByToken(String token);
}
