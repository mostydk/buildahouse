package com.mosty.buildahouse.server.project;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mosty.buildahouse.shared.project.ProjectCreateDto;
import com.mosty.buildahouse.shared.project.ProjectStateEnum;
import com.mosty.buildahouse.shared.project.ProjectUpdateDto;

@Service
public class ProjectService {
	private final ProjectRepository projectRepository;
	private final ProjectStateRepository projectStateRepository;
	
	public ProjectService(ProjectRepository projectRepository, ProjectStateRepository projectStateRepository) {
		this.projectRepository = projectRepository;
		this.projectStateRepository = projectStateRepository;
	}
	
	public List<ProjectEntity> getProjects() {
		return projectRepository.findAll(Sort.by("id"));
	}
	
	public Optional<ProjectEntity> getProject(long id) {
		return projectRepository.findById(id);
	}
	
	public ProjectEntity createProject(ProjectCreateDto projectDto) throws Exception {
		ProjectStateEntity state = getStateEntity(ProjectStateEnum.ACTIVE);
		
		ProjectEntity project = ProjectMapper.INSTANCE.projectCreateDtoToEntity(projectDto);
		project.setState(state);
		
		return projectRepository.save(project);
	}
	
	public boolean updateProject(long id, ProjectUpdateDto updateDto) throws Exception {
		ProjectEntity project = projectRepository.findById(id).orElse(null);
		
		if (project == null)
			return false;
		
		project.setName(updateDto.getName());
		project.setDescription(updateDto.getDescription());
		if (ProjectStateEnum.valueOf(project.getState().getEnumName()) != updateDto.getState())
			project.setState(getStateEntity(updateDto.getState()));
		
		projectRepository.save(project);
		return true;
	}
	
	public boolean deleteProject(long id) {
		Optional<ProjectEntity> project = projectRepository.findById(id);
		
		if (project.isEmpty())
			return false;
		else
			projectRepository.delete(project.get());
		
		return true;
	}
	
	private ProjectStateEntity getStateEntity(ProjectStateEnum state) throws Exception {
		Optional<ProjectStateEntity> entity = projectStateRepository.findByEnumName(state.name());
		if (entity.isEmpty())
			throw new Exception("ProjectStateEnum is out of sync with database. Did you forget to update the entries in the database?");
		
		return entity.get();
	}
}
