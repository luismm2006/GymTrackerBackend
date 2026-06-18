package com.GymTrackerBackend.dto;

import java.util.Objects;

public class UserRegisterResponseDTO {

    
    private Integer id;

   
    private String username;

    
    private String email;

   
    private String rol;


    public UserRegisterResponseDTO(Integer id, String username, String email, String rol) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, id, username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserRegisterResponseDTO other = (UserRegisterResponseDTO) obj;
        return Objects.equals(email, other.email)
                && Objects.equals(id, other.id)
                && Objects.equals(username, other.username);
    }
}
