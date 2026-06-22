package com.GymTrackerBackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GymTrackerBackend.service.ExerciseService;

@RestController
@RequestMapping("/api")
public class ExerciseController {

	private final ExerciseService exerciseService;
	
	
	
	public ExerciseController(ExerciseService exerciseService) {
		super();
		this.exerciseService = exerciseService;
	}



	@GetMapping("/exercises")
	public ResponseEntity<?> getAllExercises(String search, String muscleGroup){
		return ResponseEntity.ok(exerciseService.getAllExercises(search, muscleGroup));
	}
}
