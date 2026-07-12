package com.GymTrackerBackend.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.GymTrackerBackend.dto.TemplateRequestDTO;
import com.GymTrackerBackend.dto.TemplateResponseDTO;
import com.GymTrackerBackend.exception.NotFound;
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

	    Template template = new Template();
	    template.setName(dto.getName());
	    template.setCreatedAt(LocalDateTime.now());

	    if (loggedUser.getRole().equals("ADMIN")) {
	        template.setUser(null);
	    } else {
	        template.setUser(loggedUser);
	    }

	    templateRepository.save(template);

	    return new TemplateResponseDTO(
	            template.getId(),
	            template.getName(),
	            template.getUser() == null, 
	            template.getCreatedAt(),
	            List.of()           
	    );
	}
	

	public TemplateExercise addExercise(Integer templateId, Integer exerciseId) {
		
		Template template = templateRepository.findById(templateId).orElse(null);
		if(template == null) {
			throw new NotFound("No existe esa plantilla");
		}
		
		Exercise exercise = exerciseRepository.findById(exerciseId).orElse(null);
		if(exercise == null) {
			throw new NotFound("No existe ese ejercicio");
		}
		
		int order = templateExerciseRepository.countByTemplateId(templateId);

	    TemplateExercise te = new TemplateExercise();
	    te.setTemplate(template);
	    te.setExercise(exercise);
	    te.setOrderIndex(order);

	    return templateExerciseRepository.save(te);
	}
}
