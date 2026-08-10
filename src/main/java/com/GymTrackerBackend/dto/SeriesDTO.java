package com.GymTrackerBackend.dto;

public class SeriesDTO {
	private Integer id;
	private Double weight;
    private Integer reps;
	public SeriesDTO(Integer id, Double weight, Integer reps) {
		this.id = id;
		this.weight = weight;
		this.reps = reps;
	}
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Integer getReps() {
		return reps;
	}
	public void setReps(Integer reps) {
		this.reps = reps;
	}
    
    
    
}
