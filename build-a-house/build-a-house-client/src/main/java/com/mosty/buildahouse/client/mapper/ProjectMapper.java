package com.mosty.buildahouse.client.mapper;

import java.util.List;

import com.mosty.buildahouse.shared.project.ProjectCreateDto;
import com.mosty.buildahouse.shared.project.ProjectDto;
import com.mosty.buildahouse.shared.project.ProjectStateEnum;
import com.mosty.buildahouse.shared.project.ProjectSummaryDto;
import com.mosty.buildahouse.shared.project.ProjectUpdateDto;

import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

public class ProjectMapper {
	public static List<ProjectSummaryDto> toSummaryDtoList(Object json) {
		return JsReader.getList(json, ProjectMapper::toSummaryDto);
	}
	
	public static ProjectSummaryDto toSummaryDto(Object json) {
		JsPropertyMap<?> map = Js.asPropertyMap(json);
		ProjectSummaryDto dto = new ProjectSummaryDto();
		
		dto.setId(JsReader.getLong(map, "id"));
		dto.setName(JsReader.getString(map, "name"));
		dto.setState(JsReader.getEnum(map, "state", ProjectStateEnum::valueOf));
		
		return dto;
	}
	
	public static ProjectDto toDetailDto(Object json) {
		JsPropertyMap<?> map = Js.asPropertyMap(json);
		ProjectDto dto = new ProjectDto();
		
		dto.setId(JsReader.getLong(map, "id"));
		dto.setName(JsReader.getString(map, "name"));
		dto.setDescription(JsReader.getString(map, "description"));
		dto.setState(JsReader.getEnum(map, "state", ProjectStateEnum::valueOf));
		
		return dto;
	}
	
	public static JsPropertyMap<Object> fromCreateDto(ProjectCreateDto dto) {
		JsPropertyMap<Object> map = JsPropertyMap.of();
		
		map.set("name", dto.getName());
		map.set("description", dto.getDescription());
		
		return map;
	}
	
	public static JsPropertyMap<Object> fromUpdateDto(ProjectUpdateDto dto) {
		JsPropertyMap<Object> map = JsPropertyMap.of();
		
		map.set("name", dto.getName());
		map.set("description", dto.getDescription());
		map.set("state", dto.getState().name());
		
		return map;
	}
}
