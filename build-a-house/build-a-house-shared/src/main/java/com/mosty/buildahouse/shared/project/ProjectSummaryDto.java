package com.mosty.buildahouse.shared.project;

public class ProjectSummaryDto {
	private long id;
	private String name;
	private ProjectStateEnum state;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ProjectStateEnum getState() {
		return state;
	}
	
	public void setState(ProjectStateEnum state) {
		this.state = state;
	}
}
