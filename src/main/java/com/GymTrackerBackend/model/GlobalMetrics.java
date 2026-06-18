package com.GymTrackerBackend.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "global_metrics")
public class GlobalMetrics {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "total_users")
	private Integer totalUsers;
	
	@Column(name = "total_routines")
	private Integer totalRoutines;
	
	@Column(name = "total_exercises")
	private Integer totalExercises;
	
	@Column(name = "last_update")
	private LocalDateTime lastUpdate = LocalDateTime.now();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(Integer totalUsers) {
		this.totalUsers = totalUsers;
	}

	public Integer getTotalRoutines() {
		return totalRoutines;
	}

	public void setTotalRoutines(Integer totalRoutines) {
		this.totalRoutines = totalRoutines;
	}

	public Integer getTotalExercises() {
		return totalExercises;
	}

	public void setTotalExercises(Integer totalExercises) {
		this.totalExercises = totalExercises;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
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
		GlobalMetrics other = (GlobalMetrics) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
