package com.GymTrackerBackend.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.GymTrackerBackend.model.User;
import com.GymTrackerBackend.model.VerificationToken;
import com.GymTrackerBackend.repository.UserRepository;
import com.GymTrackerBackend.service.VerificationTokenService;

@RestController
@RequestMapping("/api")
public class VerifyController {

    private final VerificationTokenService verificationTokenService;
    private final UserRepository userRepository;

    public VerifyController(VerificationTokenService verificationTokenService,
                            UserRepository userRepository) {
        this.verificationTokenService = verificationTokenService;
        this.userRepository = userRepository;
    }

    @GetMapping("/auth/verify")
    public ResponseEntity<String> verifyAccount(@RequestParam("token") String token) {

        VerificationToken vt = verificationTokenService.getByToken(token);

        if (vt == null) {
            return ResponseEntity.badRequest().body("Token inválido");
        }

        if (vt.getExpiresAt().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token expirado");
        }

        User user = userRepository.findById(vt.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setEnabled(true);
        userRepository.save(user);

        return ResponseEntity.ok("Cuenta verificada correctamente. Ya puedes iniciar sesión.");
    }
}