package com.GymTrackerBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GymTrackerBackend.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

	User findByUsername(String username);

	
}
