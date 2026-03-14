package com.mosty.buildahouse.server.project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "project_state")
public class ProjectStateEntity {
	@Id
	@Column(unique = true, nullable = false)
	private int id;
	
	@Column(unique = true, nullable = false, length = 50)
	private String name;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
