package com.GymTrackerBackend.dto;

import java.util.List;

public class ExercisesInTemplateDTO {

	private Integer id;
    private String exerciseName;
    private String muscleGroup;
    private String urlImage;
    private Integer order;
    private List<SeriesDTO> series;

    public ExercisesInTemplateDTO(Integer id, String exerciseName, String muscleGroup, String urlImage, Integer order, List<SeriesDTO> series) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.muscleGroup = muscleGroup;
        this.order = order;
        this.series = series;
        this.urlImage = urlImage;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public String getMuscleGroup() {
		return muscleGroup;
	}

	public void setMuscleGroup(String muscleGroup) {
		this.muscleGroup = muscleGroup;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public List<SeriesDTO> getSeries() {
		return series;
	}

	public void setSeries(List<SeriesDTO> series) {
		this.series = series;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
    

	
    
    
    
}