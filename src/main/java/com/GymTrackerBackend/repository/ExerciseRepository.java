package com.GymTrackerBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.GymTrackerBackend.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
	List<Exercise> findByNameContainingIgnoreCase(String name);

    List<Exercise> findByMuscleGroupIgnoreCase(String muscleGroup);

    List<Exercise> findByNameContainingIgnoreCaseAndMuscleGroupIgnoreCase(
        String name,
        String muscleGroup
    );

    @Query("SELECT DISTINCT e.muscleGroup FROM Exercise e")
	List<String> findDistinctMuscleGroups();
}
