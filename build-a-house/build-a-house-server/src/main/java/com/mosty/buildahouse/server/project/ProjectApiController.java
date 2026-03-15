package com.mosty.buildahouse.server.project;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.mosty.buildahouse.shared.project.ProjectCreateDto;
import com.mosty.buildahouse.shared.project.ProjectDto;
import com.mosty.buildahouse.shared.project.ProjectSummaryDto;
import com.mosty.buildahouse.shared.project.ProjectUpdateDto;

import jakarta.validation.Valid;

@RestController
public class ProjectApiController implements ProjectApi {
	private ProjectService projectService;
	
	public ProjectApiController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@Override
	public ResponseEntity<List<ProjectSummaryDto>> getProjects() {
		List<ProjectSummaryDto> projects = projectService.getProjects().stream()
				.map(project -> ProjectMapper.INSTANCE.projectEntityToSummaryDto(project)).toList();
		
		return new ResponseEntity<>(projects, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ProjectDto> getProject(long id) {
		Optional<ProjectEntity> entity = projectService.getProject(id);
		
		if (entity.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<ProjectDto>(ProjectMapper.INSTANCE.projectEntityToDto(entity.get()), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ProjectDto> createProject(@Valid ProjectCreateDto project) throws Exception {
		ProjectEntity entity = projectService.createProject(project);
		
		return new ResponseEntity<>(ProjectMapper.INSTANCE.projectEntityToDto(entity), HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<Void> updateProject(long id, @Valid ProjectUpdateDto project) throws Exception {
		if (projectService.updateProject(id, project))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@Override
	public ResponseEntity<Void> deleteProject(long id) {
		if (projectService.deleteProject(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
