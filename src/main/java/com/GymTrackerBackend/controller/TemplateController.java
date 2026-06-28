package com.GymTrackerBackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<?> createTemplate(@RequestBody TemplateRequestDTO dto){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		TemplateResponseDTO templateResponseDTO = templateService.createTemplate(dto, auth);
        return ResponseEntity.ok(templateResponseDTO);
	}
	
}
