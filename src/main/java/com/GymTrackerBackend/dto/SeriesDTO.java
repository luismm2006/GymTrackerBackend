package com.GymTrackerBackend.dto;

public class SeriesDTO {
	private Double weight;
    private Integer reps;
	public SeriesDTO(Double weight, Integer reps) {
		this.weight = weight;
		this.reps = reps;
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
