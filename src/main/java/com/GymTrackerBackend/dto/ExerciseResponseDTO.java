package com.GymTrackerBackend.dto;


public class ExerciseResponseDTO {
	private Integer id;
	
	private String name;
	
	private String muscleGroup;

	
	
	public ExerciseResponseDTO() {
		super();
	}

	public ExerciseResponseDTO(Integer id, String name, String muscleGroup) {
		super();
		this.id = id;
		this.name = name;
		this.muscleGroup = muscleGroup;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMuscleGroup() {
		return muscleGroup;
	}

	public void setMuscleGroup(String muscleGroup) {
		this.muscleGroup = muscleGroup;
	}
	
	
	
}
