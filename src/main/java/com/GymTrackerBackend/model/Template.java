package com.GymTrackerBackend.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "templates")
public class Template {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();

	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "template")
    private List<TemplateExercise> templateExercises;

    @OneToMany(mappedBy = "template")
    private List<Routine> routines;

    @OneToMany(mappedBy = "template")
    private List<LibraryTemplate> libraryTemplates;

    
    
	public Template() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<TemplateExercise> getTemplateExercises() {
		return templateExercises;
	}

	public void setTemplateExercises(List<TemplateExercise> templateExercises) {
		this.templateExercises = templateExercises;
	}

	public List<Routine> getRoutines() {
		return routines;
	}

	public void setRoutines(List<Routine> routines) {
		this.routines = routines;
	}

	public List<LibraryTemplate> getLibraryTemplates() {
		return libraryTemplates;
	}

	public void setLibraryTemplates(List<LibraryTemplate> libraryTemplates) {
		this.libraryTemplates = libraryTemplates;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Template other = (Template) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
	
}
