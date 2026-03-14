package com.mosty.buildahouse.server.project;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mosty.buildahouse.shared.project.ProjectCreateDto;
import com.mosty.buildahouse.shared.project.ProjectDto;
import com.mosty.buildahouse.shared.project.ProjectSummaryDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Validated
@RequestMapping("${bah.api.path:/api}")
@Tag(name = "Project", description = "Operations related to projects")
public interface ProjectApi {
	@Operation(operationId = "getProjects", tags = { "Project" }, summary = "Get a list of projects", description = "Get a list of projects.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "List of projects succesfully returned"),
		@ApiResponse(responseCode = "500", description = "There was a problem retrieving the list of projects", content = @Content())
	})
	@GetMapping(value = "/project", produces = "application/json")
	ResponseEntity<List<ProjectSummaryDto>> getProjects();
	
	@Operation(operationId = "getProject", tags = { "Project" }, summary = "Get details of a project", description = "Get details of a project.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Project succesfully returned"),
		@ApiResponse(responseCode = "404", description = "Project couldn't be found", content = @Content()),
		@ApiResponse(responseCode = "500", description = "There was a problem retrieving the project", content = @Content())
	})
	@GetMapping(value = "/project/{id}", produces = "application/json")
	ResponseEntity<ProjectDto> getProject(@Parameter(description = "ID of the project") @PathVariable("id") long id);
	
	@Operation(operationId = "createProject", tags = { "Project" }, summary = "Create a new project", description = "Create a new project.")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Project created succesfully"),
		@ApiResponse(responseCode = "500", description = "There was a problem creating the project", content = @Content())
	})
	@PostMapping(value = "/project", produces = "application/json", consumes = "application/json")
	ResponseEntity<Long> createProject(@Valid @RequestBody(required = true) ProjectCreateDto project);
	
	@Operation(operationId = "updateProject", tags = { "Project" }, summary = "Update details of a project", description = "Update details of a project.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Project updated succesfully"),
		@ApiResponse(responseCode = "404", description = "Project couldn't be found", content = @Content()),
		@ApiResponse(responseCode = "500", description = "There was a problem updating the project", content = @Content())
	})
	@PatchMapping(value = "/project/{id}", consumes = "application/json")
	ResponseEntity<Void> updateProject(@Parameter(description = "ID of the project") @PathVariable("id") long id, @Valid @RequestBody(required = true) ProjectDto project);
	
	@Operation(operationId = "deleteProject", tags = { "Project" }, summary = "Delete a project", description = "Delete a project.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Project deleted succesfully"),
		@ApiResponse(responseCode = "404", description = "Project couldn't be found", content = @Content()),
		@ApiResponse(responseCode = "500", description = "There was a problem deleting the project", content = @Content())
	})
	@DeleteMapping(value = "/project/{id}")	
	ResponseEntity<Void> deleteProject(@Parameter(description = "ID of the project") @PathVariable("id") long id);
}
