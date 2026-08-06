package com.GymTrackerBackend.dto;

public class TemplateListDTO {
	private Integer id;
    private String name;
    private boolean official;
    
    
    
	public TemplateListDTO(Integer id, String name, boolean official) {
		super();
		this.id = id;
		this.name = name;
		this.official = official;
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
	public boolean isOfficial() {
		return official;
	}
	public void setOfficial(boolean official) {
		this.official = official;
	}
    
    
}
