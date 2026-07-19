package com.GymTrackerBackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GymTrackerBackend.dto.SeriesListRequestDTO;
import com.GymTrackerBackend.service.TemplateExerciseService;

@RestController
@RequestMapping("/api")
public class TemplateExerciseController {

	private final TemplateExerciseService templateExerciseService;

	public TemplateExerciseController(TemplateExerciseService templateExerciseService) {
		super();
		this.templateExerciseService = templateExerciseService;
	}
	
	
	@PostMapping("/templates/{templateId}/exercises/{templateExerciseId}/series")
	public ResponseEntity<?> addSeriesList(@PathVariable Integer templateId, @PathVariable Integer templateExerciseId, @RequestBody SeriesListRequestDTO dto) {
		
		String messageOk = templateExerciseService.addSeries(templateId, templateExerciseId, dto);
		
		return ResponseEntity.ok(messageOk);
		
	}
}
