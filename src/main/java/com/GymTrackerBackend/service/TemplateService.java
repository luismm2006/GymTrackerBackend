package com.GymTrackerBackend.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.GymTrackerBackend.dto.TemplateRequestDTO;
import com.GymTrackerBackend.dto.TemplateResponseDTO;
import com.GymTrackerBackend.model.Exercise;
import com.GymTrackerBackend.model.Template;
import com.GymTrackerBackend.model.TemplateExercise;
import com.GymTrackerBackend.model.User;
import com.GymTrackerBackend.repository.ExerciseRepository;
import com.GymTrackerBackend.repository.TemplateExerciseRepository;
import com.GymTrackerBackend.repository.TemplateRepository;
import com.GymTrackerBackend.repository.UserRepository;

@Service
public class TemplateService {
	
	private final TemplateRepository templateRepository;

	private final UserRepository userRepository;
	
	private final ExerciseRepository exerciseRepository;
	
	private final TemplateExerciseRepository templateExerciseRepository;
	
	public TemplateService(TemplateRepository templateRepository, UserRepository userRepository, ExerciseRepository exerciseRepository, TemplateExerciseRepository templateExerciseRepository) {
		super();
		this.templateRepository = templateRepository;
		this.userRepository = userRepository;
		this.exerciseRepository = exerciseRepository;
		this.templateExerciseRepository = templateExerciseRepository;
	}

	public TemplateResponseDTO createTemplate(TemplateRequestDTO dto, Authentication auth) {
		
		User loggedUser = userRepository.findByUsername(auth.getName());
		
		if(loggedUser.getRole().equals("ADMIN")) {
			dto.setUserId(null);
		}else {
			dto.setUserId(loggedUser.getId());
		}
		
		Template template = new Template();
		template.setName(dto.getName());
		template.setCreatedAt(LocalDateTime.now());
		if (dto.getUserId() == null) {
		    template.setUser(null); 
		} else {
		    template.setUser(loggedUser); 
		}
				
		templateRepository.save(template);
		
		List<TemplateExercise> templateExercises = createTemplateExercises(template.getId(), dto.getExerciseIds());
		
		template.setTemplateExercises(templateExercises);
		
		templateRepository.save(template);
		
		TemplateResponseDTO templateResponseDTO = new TemplateResponseDTO();
		templateResponseDTO.setId(template.getId());
		templateResponseDTO.setName(template.getName());
		templateResponseDTO.setOfficial(dto.getUserId() == null ? true : false);
		templateResponseDTO.setExerciseIds(dto.getExerciseIds());
		templateResponseDTO.setCreatedAt(template.getCreatedAt());
		
		return templateResponseDTO;
	}
	
	public List<TemplateExercise> createTemplateExercises(Integer templateId, List<Integer> exercisesId){
		Template template = templateRepository.findById(templateId).orElse(null);
		if(template == null) {
			//fallo
		}
		List<TemplateExercise> result = new ArrayList<>();

	    int order = 0;
		for(Integer exerciseId : exercisesId) {
			Exercise exercise = exerciseRepository.findById(exerciseId).orElse(null);
			if(exercise == null) {
				//fallo
			}
			TemplateExercise templateExercise = new TemplateExercise();
			templateExercise.setTemplate(template);
			templateExercise.setExercise(exercise);
			templateExercise.setOrderIndex(order++);
			
			templateExerciseRepository.save(templateExercise);
			result.add(templateExercise);
		}
		
		return result;
		
	}
}
