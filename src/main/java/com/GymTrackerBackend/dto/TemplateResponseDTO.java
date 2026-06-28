package com.GymTrackerBackend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TemplateResponseDTO {

    private Integer id;
    private String name;
    private boolean official; 
    private LocalDateTime createdAt;

    private List<Integer> exerciseIds;

    public TemplateResponseDTO() {}

    public TemplateResponseDTO(Integer id, String name, boolean official, LocalDateTime createdAt, List<Integer> exerciseIds) {
        this.id = id;
        this.name = name;
        this.official = official;
        this.createdAt = createdAt;
        this.exerciseIds = exerciseIds;
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

    public boolean isOfficial() {
        return official;
    }

    public void setOfficial(boolean official) {
        this.official = official;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Integer> getExerciseIds() {
        return exerciseIds;
    }

    public void setExerciseIds(List<Integer> exerciseIds) {
        this.exerciseIds = exerciseIds;
    }
}
