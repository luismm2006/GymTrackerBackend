package com.GymTrackerBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GymTrackerBackend.model.Template;

public interface TemplateRepository extends JpaRepository<Template, Integer> {

}
