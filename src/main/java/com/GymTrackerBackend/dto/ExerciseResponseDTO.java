package com.GymTrackerBackend.dto;


public class ExerciseResponseDTO {
	private Integer id;
	
	private String name;
	
	private String muscleGroup;

	private String urlImage;

	
	public ExerciseResponseDTO() {
		super();
	}

	public ExerciseResponseDTO(Integer id, String name, String muscleGroup, String urlImage) {
		super();
		this.id = id;
		this.name = name;
		this.muscleGroup = muscleGroup;
		this.urlImage = urlImage;
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

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	
	
	
}
