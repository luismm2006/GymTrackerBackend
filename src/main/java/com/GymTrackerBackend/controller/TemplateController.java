package com.GymTrackerBackend.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GymTrackerBackend.dto.TemplateExercisesRequestDTO;
import com.GymTrackerBackend.dto.TemplateRequestDTO;
import com.GymTrackerBackend.dto.TemplateResponseDTO;
import com.GymTrackerBackend.service.TemplateService;

@RestController
@RequestMapping("/api")
public class TemplateController {
	
	private final TemplateService templateService;

	public TemplateController(TemplateService templateService) {
		super();
		this.templateService = templateService;
	}
	
	
	
	@PostMapping("/createTemplate")
    public ResponseEntity<?> createTemplate(@RequestBody TemplateRequestDTO dto, Authentication auth) {
        TemplateResponseDTO response = templateService.createTemplate(dto, auth);
        return ResponseEntity.ok(response);
    }

	@GetMapping("/templates/{id}")
	public TemplateResponseDTO getTemplateId(@PathVariable Integer id) {
		return templateService.getTemplateId(id);
	}
	
	
    @PostMapping("/{templateId}/exercises")
    public ResponseEntity<?> addExercise(
            @PathVariable Integer templateId,
            @RequestBody TemplateExercisesRequestDTO dto
    ) {
        templateService.addExercise(templateId, dto.getExerciseId());
        return ResponseEntity.ok(
                Map.of("message", "Ejercicio añadido correctamente")
            );
    }
    
    
}
