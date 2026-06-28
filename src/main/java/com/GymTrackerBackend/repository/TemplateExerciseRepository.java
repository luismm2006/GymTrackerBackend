package com.GymTrackerBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GymTrackerBackend.model.TemplateExercise;

public interface TemplateExerciseRepository extends JpaRepository<TemplateExercise, Integer> {

}
