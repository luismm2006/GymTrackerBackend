package com.GymTrackerBackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GymTrackerBackend.dto.UserLoginRequestDTO;
import com.GymTrackerBackend.dto.UserLoginResponseDTO;
import com.GymTrackerBackend.dto.UserRegisterRequestDTO;
import com.GymTrackerBackend.dto.UserRegisterResponseDTO;
import com.GymTrackerBackend.exception.BadRequest;
import com.GymTrackerBackend.security.JwtUtil;
import com.GymTrackerBackend.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class UserController {
	
	private final UserService userService;
	
	private final AuthenticationManager authenticationManager;
	
    private final JwtUtil jwtUtil;

	public UserController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager; 
	}

	 @PostMapping("/auth/register")
	    public ResponseEntity<?> userRegister(
	            @Valid @RequestBody UserRegisterRequestDTO userRegisterRequestDTO,
	            BindingResult bindingResult) throws Exception {
	        if (bindingResult.hasErrors()) {
	            throw new Exception();
	        }
	        UserRegisterResponseDTO userRegisterResponseDTO = userService.add(userRegisterRequestDTO);
	        if (userRegisterResponseDTO == null) {
	            throw new BadRequest("No se pudo crear usuario");
	        }
	        return ResponseEntity.ok(userRegisterResponseDTO);
	 	}
	 @PostMapping("/auth/login")
	    public ResponseEntity<?> userLogin(
	            @Valid @RequestBody UserLoginRequestDTO userLoginRequestDTO,
	            BindingResult bindingResult) throws Exception {

	        if (bindingResult.hasErrors()) {
	            throw new Exception();
	        }

	        UserLoginResponseDTO userLoginResponseDTO =
	                userService.getToken(userLoginRequestDTO, authenticationManager, jwtUtil);

	        return ResponseEntity.ok(userLoginResponseDTO);
	    }
	
}
