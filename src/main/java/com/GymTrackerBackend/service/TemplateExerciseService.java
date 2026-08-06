package com.GymTrackerBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.GymTrackerBackend.dto.SeriesDTO;
import com.GymTrackerBackend.dto.SeriesListRequestDTO;
import com.GymTrackerBackend.exception.NotFound;
import com.GymTrackerBackend.model.Series;
import com.GymTrackerBackend.model.TemplateExercise;
import com.GymTrackerBackend.repository.SeriesRepository;
import com.GymTrackerBackend.repository.TemplateExerciseRepository;

import jakarta.transaction.Transactional;

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
	@Transactional
	public List<SeriesDTO> getSeries(Integer templateId, Integer templateExerciseId) {
		TemplateExercise te = templateExerciseRepository.findByIdAndTemplateId(templateExerciseId, templateId);
		if(te == null) {
			throw new NotFound("TemplateExercise no pertenece al Template indicado");
		}
		return te.getSeries().stream().map(s -> new SeriesDTO(s.getWeight(), s.getReps())).toList();
	}

	public String editSeries(Integer templateId, Integer templateExerciseId, Integer seriesId, SeriesDTO seriesDTO) {
		TemplateExercise te = templateExerciseRepository.findByIdAndTemplateId(templateExerciseId, templateId);
		if(te == null) {
			throw new NotFound("TemplateExercise no pertenece al Template indicado");
		}
		
		Series series = seriesRepository.findById(seriesId).orElseThrow(() -> new NotFound("Serie no encontrada"));
		
		if(!series.getTemplateExercise().getId().equals(te.getId())) {
	        throw new NotFound("La serie no pertenece al TemplateExercise indicado");
		}
		
		series.setWeight(seriesDTO.getWeight());
		series.setReps(seriesDTO.getReps());
		
		seriesRepository.save(series);
		
		return "Serie editada correctamente";
	}

	public String deleteSeries(Integer templateId, Integer templateExerciseId, Integer seriesId) {
		TemplateExercise te = templateExerciseRepository.findByIdAndTemplateId(templateExerciseId, templateId);
		if(te == null) {
			throw new NotFound("TemplateExercise no pertenece al Template indicado");
		}
		
		Series series = seriesRepository.findById(seriesId).orElseThrow(() -> new NotFound("Serie no encontrada"));
		if(!series.getTemplateExercise().getId().equals(te.getId())) {
	        throw new NotFound("La serie no pertenece al TemplateExercise indicado");
		}
		
		seriesRepository.delete(series);
		return "Serie eliminada correctamente";
	}
	
	
	
}
