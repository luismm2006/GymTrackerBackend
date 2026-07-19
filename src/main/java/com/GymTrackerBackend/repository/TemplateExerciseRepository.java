package com.GymTrackerBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.GymTrackerBackend.model.TemplateExercise;

public interface TemplateExerciseRepository extends JpaRepository<TemplateExercise, Integer> {

	int countByTemplateId(Integer templateId);

	@Query("""
		    SELECT te FROM TemplateExercise te
		    LEFT JOIN FETCH te.series
		    WHERE te.template.id = :templateId
		""")
		List<TemplateExercise> findByTemplateIdWithSeries(Integer templateId);

	TemplateExercise findByIdAndTemplateId(Integer templateExerciseId, Integer templateId);

}
