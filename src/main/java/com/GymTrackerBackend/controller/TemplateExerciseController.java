package com.GymTrackerBackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GymTrackerBackend.dto.SeriesDTO;
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
	
	@GetMapping("/templates/{templateId}/exercises/{templateExerciseId}/series")
	public ResponseEntity<?> getSeriesByExercise(@PathVariable Integer templateId, @PathVariable Integer templateExerciseId) {
		
		 List<SeriesDTO> seriesDTO = templateExerciseService.getSeries(templateId, templateExerciseId);
		
		return ResponseEntity.ok(seriesDTO);
		
	}
	
	
	@PostMapping("/templates/{templateId}/exercises/{templateExerciseId}/series")
	public ResponseEntity<?> addSeriesList(@PathVariable Integer templateId, @PathVariable Integer templateExerciseId, @RequestBody SeriesListRequestDTO dto) {
		
		String messageOk = templateExerciseService.addSeries(templateId, templateExerciseId, dto);
		
		return ResponseEntity.ok(messageOk);
		
	}
	
	@PutMapping("/templates/{templateId}/exercises/{templateExerciseId}/series/{seriesId}")
	public ResponseEntity<?> putSeries(@PathVariable Integer templateId, @PathVariable Integer templateExerciseId, @PathVariable Integer seriesId, @RequestBody SeriesDTO seriesDTO) {
		
		String messageOk = templateExerciseService.editSeries(templateId, templateExerciseId, seriesId, seriesDTO);
		
		return ResponseEntity.ok(messageOk);
		
	}
	
	@DeleteMapping("/templates/{templateId}/exercises/{templateExerciseId}/series/{seriesId}")
	public ResponseEntity<?> deleteSeries(@PathVariable Integer templateId, @PathVariable Integer templateExerciseId, @PathVariable Integer seriesId) {
		
		String messageOk = templateExerciseService.deleteSeries(templateId, templateExerciseId, seriesId);
		
		return ResponseEntity.ok(messageOk);
		
	}
}
