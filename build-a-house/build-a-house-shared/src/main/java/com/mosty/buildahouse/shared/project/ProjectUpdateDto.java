package com.mosty.buildahouse.shared.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProjectUpdateDto {
	@NotBlank @Size(max = 200)
	private String name;
	
	private String description;
	
	@NotNull
	private ProjectStateEnum state;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ProjectStateEnum getState() {
		return state;
	}
	
	public void setState(ProjectStateEnum state) {
		this.state = state;
	}
}
