package com.mosty.buildahouse.server.project;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mosty.buildahouse.shared.project.ProjectDto;
import com.mosty.buildahouse.shared.project.ProjectStateEnum;
import com.mosty.buildahouse.shared.project.ProjectSummaryDto;
import com.mosty.buildahouse.shared.project.ProjectCreateDto;

@Mapper
public interface ProjectMapper {
	ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);
	
	ProjectDto projectEntityToDto(ProjectEntity entity);
	ProjectSummaryDto projectEntityToSummaryDto(ProjectEntity entity);
	ProjectEntity projectUpdateDtoToEntity(ProjectCreateDto dto);
	
	default ProjectStateEnum projectStateEntityToEnum(ProjectStateEntity entity) {
		return ProjectStateEnum.valueOf(entity.getName());
	}
}
