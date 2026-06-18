package com.GymTrackerBackend.dto;

import java.util.Objects;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserRegisterRequestDTO {
	@NotNull(message = "El username no puede ser nulo")
    @NotEmpty(message = "El username no puede estar vacío")
	private String username;
	
	@NotNull(message = "El email no puede ser nulo")
	@NotEmpty(message = "El email no puede estar vacío")
	@Pattern(
	    regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$",
	    message = "El formato de email no es válido"
	)
	private String email;
	@NotNull(message = "La contraseña no puede ser nula")
    @NotEmpty(message = "La contraseña no puede estar vacía")
	private String password;
	@NotNull(message = "La contraseña repetida no puede ser nula")
    @NotEmpty(message = "La contraseña repetida no puede estar vacía")
	private String repeatPassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, username, password, repeatPassword);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRegisterRequestDTO other = (UserRegisterRequestDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(username, other.username)
				&& Objects.equals(password, other.password) && Objects.equals(repeatPassword, other.repeatPassword);
	}
	
	
}
