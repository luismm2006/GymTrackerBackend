package com.GymTrackerBackend.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.GymTrackerBackend.dto.ExercisesInTemplateDTO;
import com.GymTrackerBackend.dto.GetTemplatesResponseDto;
import com.GymTrackerBackend.dto.SeriesDTO;
import com.GymTrackerBackend.dto.TemplateResponseDTO;
import com.GymTrackerBackend.dto.UserLoginRequestDTO;
import com.GymTrackerBackend.dto.UserLoginResponseDTO;
import com.GymTrackerBackend.dto.UserRegisterRequestDTO;
import com.GymTrackerBackend.dto.UserRegisterResponseDTO;
import com.GymTrackerBackend.exception.BadRequest;
import com.GymTrackerBackend.exception.Conflict;
import com.GymTrackerBackend.model.Template;
import com.GymTrackerBackend.model.TemplateExercise;
import com.GymTrackerBackend.model.User;
import com.GymTrackerBackend.model.VerificationToken;
import com.GymTrackerBackend.repository.TemplateExerciseRepository;
import com.GymTrackerBackend.repository.TemplateRepository;
import com.GymTrackerBackend.repository.UserRepository;
import com.GymTrackerBackend.security.JwtUtil;

import jakarta.validation.Valid;


@Service
public class UserService implements UserDetailsService{

	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;

	private final TemplateRepository templateRepository;
	
	private final TemplateExerciseRepository templateExerciseRepository;
	
	private final EmailService emailService;
	
	private final VerificationTokenService tokenService;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, VerificationTokenService tokenService, TemplateRepository templateRepository, TemplateExerciseRepository templateExerciseRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenService = tokenService;
		this.emailService = emailService;
		this.templateRepository = templateRepository;
		this.templateExerciseRepository = templateExerciseRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User result = userRepository.findByUsername(username);
		if (result == null) {
			throw new UsernameNotFoundException("Credenciales incorrectas");
		}
		return result;
	}

	public UserRegisterResponseDTO add(@Valid UserRegisterRequestDTO userRegisterRequestDTO) {
		if(!userRegisterRequestDTO.getPassword().equals(userRegisterRequestDTO.getRepeatPassword())) {
			throw new BadRequest("Las contraseñas no coinciden");
		}
		
		User userExist = this.userRepository.findByUsername(userRegisterRequestDTO.getUsername());
		
		if(userExist != null) {
			throw new Conflict("Usuario existente");
		}
		
		User userEmailExist = this.userRepository.findByEmail(userRegisterRequestDTO.getEmail());
		
		if(userEmailExist != null) {
			throw new Conflict("Email existente");
		}
		
		String encoderPassword = passwordEncoder.encode(userRegisterRequestDTO.getPassword());


		User userValid = new User(userRegisterRequestDTO.getUsername(), userRegisterRequestDTO.getEmail(), encoderPassword, "USER");
		
		User userResponse = this.userRepository.save(userValid);
		if (userResponse == null) {
			throw new BadRequest("Error al crear el usuario");
		}
		
		VerificationToken token = tokenService.createToken(userResponse.getId());

	    String link = "http://localhost:8080/api/auth/verify?token=" + token.getToken();
	    emailService.sendVerificationEmail(userResponse.getEmail(), link);
		
		UserRegisterResponseDTO userRegisterResponseDTO = new UserRegisterResponseDTO(userResponse.getId(),
				userResponse.getUsername(), userResponse.getEmail(), userResponse.getRole());
		return userRegisterResponseDTO;
	}

	public UserLoginResponseDTO getToken(@Valid UserLoginRequestDTO userLoginRequestDTO,
			AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		Authentication authentication;
		authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getUsername(), userLoginRequestDTO.getPassword()));
		
		User user = (User)authentication.getPrincipal();
		String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
		UserLoginResponseDTO userLoginResponseDTO = new UserLoginResponseDTO(token);
		
		return userLoginResponseDTO;
	}

	public GetTemplatesResponseDto getAllTemplatesByUserId(Authentication auth) {
		User loggedUser = userRepository.findByUsername(auth.getName());
	    List<Template> templates = templateRepository.findByUserId(loggedUser.getId());
	    
	    List<TemplateResponseDTO> templateResponseDTOs = templates.stream()
	    		.map(t -> {
	    	List<TemplateExercise> templateExercises = templateExerciseRepository.findByTemplateIdWithSeries(t.getId());
	    	
	    	List<ExercisesInTemplateDTO> exercisesInTemplateDTOs = templateExercises.stream()
	    			.map(te -> {
	    				List<SeriesDTO> seriesDTOs = te.getSeries().stream()
	    						.map(s -> new SeriesDTO(s.getWeight(), s.getReps())).toList();
	    				
	    				return new ExercisesInTemplateDTO(te.getId(), te.getExercise().getName(), te.getExercise().getMuscleGroup(), seriesDTOs);
	    			}).toList();
	    	
	    	return new TemplateResponseDTO(t.getId(), t.getName(), t.getUser() == null ? true : false, t.getCreatedAt(), exercisesInTemplateDTOs);
	    	
	    }).toList();
	    return new GetTemplatesResponseDto(templateResponseDTOs);
	}

	
	
}
