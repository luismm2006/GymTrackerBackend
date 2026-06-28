package com.GymTrackerBackend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role = "USER";
    
    @Column(nullable = false)
    private boolean enabled = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user")
    private List<Routine> routines;

    @OneToMany(mappedBy = "user")
    private List<Template> templates;

    @OneToMany(mappedBy = "user")
    private List<LibraryTemplate> libraryTemplates;
    
    public User(String username, String email, String password, String role) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	    this.enabled = false; 
		this.createdAt = LocalDateTime.now();
		this.routines = new ArrayList<>();
		this.templates = new ArrayList<>();
		this.libraryTemplates = new ArrayList<>();;

	}

	public User() {
		super();
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

	@Override
	public boolean isEnabled() {
	    return enabled;
	}

	
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    
    public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

    
	public List<Routine> getRoutines() {
		return routines;
	}

	public void setRoutines(List<Routine> routines) {
		this.routines = routines;
	}

	public List<Template> getTemplates() {
		return templates;
	}

	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}

	public List<LibraryTemplate> getLibraryTemplates() {
		return libraryTemplates;
	}

	public void setLibraryTemplates(List<LibraryTemplate> libraryTemplates) {
		this.libraryTemplates = libraryTemplates;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return Objects.equals(id, other.id);
    }
}

