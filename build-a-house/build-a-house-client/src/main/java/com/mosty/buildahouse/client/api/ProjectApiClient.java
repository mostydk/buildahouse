package com.mosty.buildahouse.client.api;

import java.util.List;

import com.mosty.buildahouse.client.mapper.ProjectMapper;
import com.mosty.buildahouse.shared.project.ProjectCreateDto;
import com.mosty.buildahouse.shared.project.ProjectDto;
import com.mosty.buildahouse.shared.project.ProjectSummaryDto;
import com.mosty.buildahouse.shared.project.ProjectUpdateDto;

import elemental2.promise.Promise;

public class ProjectApiClient {
	public static Promise<List<ProjectSummaryDto>> getProjects() {
		return ApiClient.getResource("/api/project", ProjectMapper::toSummaryDtoList);
	}
	
	public static Promise<ProjectDto> getProject(long id) {
		return ApiClient.getResource("/api/project/" + id, ProjectMapper::toDetailDto);
	}
	
	public static Promise<ProjectDto> createProject(ProjectCreateDto createDto) {
		return ApiClient.postResource("/api/project", ProjectMapper.fromCreateDto(createDto), ProjectMapper::toDetailDto);
	}
	
	public static Promise<Void> updateProject(long id, ProjectUpdateDto updateDto) {
		return ApiClient.putResource("/api/project/" + id, ProjectMapper.fromUpdateDto(updateDto));
	}
	
	public static Promise<Void> deleteProject(long id) {
		return ApiClient.deleteResource("/api/project/" + id);
	}
}
