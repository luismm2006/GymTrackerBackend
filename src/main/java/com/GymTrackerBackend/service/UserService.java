package com.GymTrackerBackend.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.GymTrackerBackend.dto.UserRegisterRequestDTO;
import com.GymTrackerBackend.dto.UserRegisterResponseDTO;
import com.GymTrackerBackend.exception.BadRequest;
import com.GymTrackerBackend.exception.Conflict;
import com.GymTrackerBackend.model.User;
import com.GymTrackerBackend.model.VerificationToken;
import com.GymTrackerBackend.repository.UserRepository;

import jakarta.validation.Valid;


@Service
public class UserService implements UserDetailsService{

	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;

	private final EmailService emailService;
	
	private final VerificationTokenService tokenService;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, VerificationTokenService tokenService) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenService = tokenService;
		this.emailService = emailService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User result = userRepository.findByusername(username);
		if (result == null) {
			throw new UsernameNotFoundException("Credenciales incorrectas");
		}
		return result;
	}

	public UserRegisterResponseDTO add(@Valid UserRegisterRequestDTO userRegisterRequestDTO) {
		if(!userRegisterRequestDTO.getPassword().equals(userRegisterRequestDTO.getRepeatPassword())) {
			throw new BadRequest("Las contraseñas no coinciden");
		}
		
		User userExist = this.userRepository.findByusername(userRegisterRequestDTO.getUsername());
		
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
	
}
