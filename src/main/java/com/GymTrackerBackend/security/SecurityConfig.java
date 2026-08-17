package com.GymTrackerBackend.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.GymTrackerBackend.exception.ApiError;

import tools.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
	        throws Exception {
	    return configuration.getAuthenticationManager();
	}
	
	 @Bean
	    AccessDeniedHandler accessDeniedHandler() {
	        return (request, response, ex) -> {
	            response.setStatus(HttpStatus.FORBIDDEN.value());
	            response.setContentType("application/json");

	            ApiError error = new ApiError(
	                    HttpStatus.FORBIDDEN.value(),
	                    HttpStatus.FORBIDDEN.getReasonPhrase(),
	                    List.of("No tienes permisos para acceder a este recurso"),
	                    request.getRequestURI()
	            );

	            new ObjectMapper().writeValue(response.getOutputStream(), error);
	        };
	    }
	 @Bean
	  CorsConfigurationSource corsConfigurationSource() {
	     CorsConfiguration config = new CorsConfiguration();
	     config.setAllowCredentials(true);
	     config.addAllowedOrigin("http://localhost:5173");
	     config.addAllowedHeader("*");
	     config.addAllowedMethod("*");

	     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	     source.registerCorsConfiguration("/**", config);
	     return source;
	 }

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

		http
	    .cors(cors -> cors.configurationSource(corsConfigurationSource()))
	    .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
         
        		// Swagger
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                // Auth
                .requestMatchers("/api/auth/login", "/api/auth/register", "/api/auth/verify").permitAll()
                // Ejercicios públicos
                .requestMatchers("/api/exercises", "/api/routines/**", "/api/templates/{templateId}/exercises/{templateExerciseId}/series", "/api/exercises/**").permitAll()
                // Crear plantilla (solo si quieres que ADMIN pueda sin token)
                .requestMatchers("/api/createTemplate").permitAll()
                // Endpoints que requieren autenticación
                .requestMatchers("/api/templates/**").authenticated()
                .requestMatchers("/api/**").authenticated()
                // Todo lo demás denegado
                .anyRequest().denyAll()

        )
        .exceptionHandling(ex -> ex
        		.authenticationEntryPoint(jwtAuthenticationEntryPoint)
        		.accessDeniedHandler(accessDeniedHandler())

        		);

    	http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}