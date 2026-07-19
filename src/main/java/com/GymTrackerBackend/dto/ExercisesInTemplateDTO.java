package com.GymTrackerBackend.dto;

import java.util.List;

public class ExercisesInTemplateDTO {

    private Integer id;
    private String name;
    private String muscleGroup;
    private List<SeriesDTO> series;
    
    public ExercisesInTemplateDTO(Integer id, String name, String muscleGroup, List<SeriesDTO> series) {
        this.id = id;
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.series = series;
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

	public List<SeriesDTO> getSeries() {
		return series;
	}

	public void setSeries(List<SeriesDTO> series) {
		this.series = series;
	}
    
    
    
}