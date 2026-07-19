package com.GymTrackerBackend.service;

import org.springframework.stereotype.Service;

import com.GymTrackerBackend.dto.SeriesDTO;
import com.GymTrackerBackend.dto.SeriesListRequestDTO;
import com.GymTrackerBackend.exception.NotFound;
import com.GymTrackerBackend.model.Series;
import com.GymTrackerBackend.model.TemplateExercise;
import com.GymTrackerBackend.repository.SeriesRepository;
import com.GymTrackerBackend.repository.TemplateExerciseRepository;

@Service
public class TemplateExerciseService {

	private final TemplateExerciseRepository templateExerciseRepository;
	private final SeriesRepository seriesRepository;
	
	public TemplateExerciseService(TemplateExerciseRepository templateExerciseRepository, SeriesRepository seriesRepository) {
		super();
		this.templateExerciseRepository = templateExerciseRepository;
		this.seriesRepository = seriesRepository;
	}

	public String addSeries(Integer templateId, Integer templateExerciseId, SeriesListRequestDTO dto) {
		
		TemplateExercise te = templateExerciseRepository.findByIdAndTemplateId(templateExerciseId, templateId);
		if(te == null) {
			throw new NotFound("TemplateExercise no pertenece al Template indicado");
		}
		

	    for (SeriesDTO sDto : dto.getSeries()) {
	        Series s = new Series();
	        s.setWeight(sDto.getWeight());
	        s.setReps(sDto.getReps());
	        s.setTemplateExercise(te);
	        seriesRepository.save(s);
	    }
		
		return "Series añadida correctamente";
	}
	
	
	
}
