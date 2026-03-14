package com.mosty.buildahouse.shared.project;

public enum ProjectStateEnum {
	ACTIVE("Active"),
	CLOSED("Closed"),
	;
	
	private String title;
	
	private ProjectStateEnum(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
}
