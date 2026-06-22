package com.GymTrackerBackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.GymTrackerBackend.dto.ExerciseResponseDTO;
import com.GymTrackerBackend.model.Exercise;
import com.GymTrackerBackend.repository.ExerciseRepository;

@Service
public class ExerciseService {
	
	private final ExerciseRepository exerciseRepository;
	
	
	
	public ExerciseService(ExerciseRepository exerciseRepository) {
		super();
		this.exerciseRepository = exerciseRepository;
	}



	public List<ExerciseResponseDTO> getAllExercises(String search, String muscleGroup) {
	    List<Exercise> exercisesList;
	    
		if (search != null && muscleGroup != null) {
			exercisesList = exerciseRepository
	                .findByNameContainingIgnoreCaseAndMuscleGroupIgnoreCase(search, muscleGroup);
	    }

		else if (search != null) {
	    	exercisesList = exerciseRepository.findByNameContainingIgnoreCase(search);
	    }

		else if (muscleGroup != null) {
	    	exercisesList = exerciseRepository.findByMuscleGroupIgnoreCase(muscleGroup);
	    }
	    else {
	    	exercisesList = exerciseRepository.findAll();	    	
	    }
		
		List<ExerciseResponseDTO> exercises = new ArrayList<ExerciseResponseDTO>();
		
		for(Exercise exercise : exercisesList) {
			ExerciseResponseDTO exerciseResponseDTO = new ExerciseResponseDTO();
			exerciseResponseDTO.setId(exercise.getId());
			exerciseResponseDTO.setName(exercise.getName());
			exerciseResponseDTO.setMuscleGroup(exercise.getMuscleGroup());
			
			exercises.add(exerciseResponseDTO);
		}
		
		return exercises;
	}

}
