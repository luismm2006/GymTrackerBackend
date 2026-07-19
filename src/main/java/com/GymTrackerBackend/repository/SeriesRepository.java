package com.GymTrackerBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GymTrackerBackend.model.Series;

public interface SeriesRepository extends JpaRepository<Series, Integer> {
	
}
