package com.GymTrackerBackend.dto;

public class ExercisesTypesResponseDTO {

	private String muscleGroup;

	public ExercisesTypesResponseDTO(String muscleGroup) {
		super();
		this.muscleGroup = muscleGroup;
	}

	public String getMuscleGroup() {
		return muscleGroup;
	}

	public void setMuscleGroup(String muscleGroup) {
		this.muscleGroup = muscleGroup;
	}
	
	
	
}
