package com.mosty.buildahouse.server.project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "project_state")
public class ProjectStateEntity {
	@Id
	private Integer id;
	
	@Column(name = "enum_name", unique = true, nullable = false, length = 50)
	private String enumName;

	public int getId() {
		return id;
	}
	
	public String getEnumName() {
		return enumName;
	}
	
	public void setEnumName(String enumName) {
		this.enumName = enumName;
	}
}
