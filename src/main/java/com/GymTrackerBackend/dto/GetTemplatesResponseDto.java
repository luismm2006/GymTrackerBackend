package com.GymTrackerBackend.dto;

import java.util.List;

public class GetTemplatesResponseDto {
	
	private List<TemplateResponseDTO> templates;

    public GetTemplatesResponseDto(List<TemplateResponseDTO> templates) {
        this.templates = templates;
    }

    public List<TemplateResponseDTO> getTemplates() {
        return templates;
    }
	
}
 