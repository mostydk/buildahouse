package com.mosty.buildahouse.server.project;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.mosty.buildahouse.shared.project.ProjectCreateDto;
import com.mosty.buildahouse.shared.project.ProjectDto;
import com.mosty.buildahouse.shared.project.ProjectStateEnum;
import com.mosty.buildahouse.shared.project.ProjectSummaryDto;

import jakarta.validation.Valid;

@Controller
public class ProjectApiController implements ProjectApi {
	private final ProjectRepository projectRepository;
	private final ProjectStateRepository projectStateRepository;
	
	public ProjectApiController(ProjectRepository projectRepository, ProjectStateRepository projectStateRepository) {
		this.projectRepository = projectRepository; 
		this.projectStateRepository = projectStateRepository;
	}
	
	@Override
	public ResponseEntity<List<ProjectSummaryDto>> getProjects() {
		List<ProjectSummaryDto> projects = projectRepository.findAll().stream()
				.map(project -> ProjectMapper.INSTANCE.projectEntityToSummaryDto(project)).toList();
		
		return new ResponseEntity<>(projects, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ProjectDto> getProject(long id) {
		Optional<ProjectEntity> entity = projectRepository.findById(id);
		
		if (entity.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<ProjectDto>(ProjectMapper.INSTANCE.projectEntityToDto(entity.get()), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Long> createProject(@Valid ProjectCreateDto project) {
		ProjectStateEntity state = projectStateRepository.findByName(ProjectStateEnum.ACTIVE.name()).orElse(null);
		
		ProjectEntity entity = ProjectMapper.INSTANCE.projectUpdateDtoToEntity(project);
		entity.setState(state);
		
		entity = projectRepository.save(entity);
		
		return new ResponseEntity<>(entity.getId(), HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<Void> updateProject(long id, @Valid ProjectDto project) {
		ProjectEntity entity = projectRepository.findById(id).orElse(null);
		
		if (entity == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		entity.setName(project.getName());
		entity.setDescription(project.getDescription());
		if (ProjectStateEnum.valueOf(entity.getState().getName()) != project.getState()) {
			ProjectStateEntity state = projectStateRepository.findByName(project.getState().name()).orElse(null);
			entity.setState(state);
		}
		
		projectRepository.save(entity);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Void> deleteProject(long id) {
		Optional<ProjectEntity> entity = projectRepository.findById(id);
		
		if (entity.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			projectRepository.delete(entity.get());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
