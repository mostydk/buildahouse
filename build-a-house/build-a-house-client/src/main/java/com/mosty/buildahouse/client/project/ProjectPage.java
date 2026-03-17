package com.mosty.buildahouse.client.project;

import com.mosty.buildahouse.client.api.ProjectApiClient;
import com.mosty.buildahouse.client.component.GridTable;
import com.mosty.buildahouse.client.component.GridTable.Column;
import com.mosty.buildahouse.client.element.BaseElement.DivElement;
import com.mosty.buildahouse.client.element.BaseMUIElement;
import com.mosty.buildahouse.client.element.ElementsFactory;
import com.mosty.buildahouse.shared.project.ProjectCreateDto;
import com.mosty.buildahouse.shared.project.ProjectSummaryDto;
import com.mosty.buildahouse.shared.project.ProjectUpdateDto;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;

public class ProjectPage extends BaseMUIElement<ProjectPage, HTMLDivElement> implements ElementsFactory {
	private DivElement root;
	private GridTable<ProjectSummaryDto> projectTable;
	
	public ProjectPage() {
		root = div();
		
		init(this);
		
		createTable();
		loadData();
	}
	
	private void createTable() {
		root
				.addCss("mui-flex-column")
				.appendChild(span()
						.addCss("material-symbols-rounded", "mui-icon", "project-table-icon", "mui-margin-left-auto")
						.textContent("add")
						.addClickLister(evt -> handleAddProject()))
				.appendChild(projectTable = GridTable.<ProjectSummaryDto>create()
						.addColumn(Column.<ProjectSummaryDto>create()
								.setTitle("#")
								.setCellRenderer(project -> div()
										.textContent(Long.toString(project.getId()))
										.element()))
						.addColumn(Column.<ProjectSummaryDto>create()
								.setTitle("Name")
								.setGrow(true)
								.setCellRenderer(project -> div()
										.textContent(project.getName())
										.element()))
						.addColumn(Column.<ProjectSummaryDto>create()
								.setTitle("State")
								.setCellRenderer(project -> div()
										.textContent(project.getState().getTitle())
										.element()))
						.addColumn(Column.<ProjectSummaryDto>create()
								.setTitle("Action")
								.setCellRenderer(project -> div()
										.appendChild(span()
												.addCss("material-symbols-rounded", "mui-icon", "project-table-icon")
												.textContent("edit")
												.addClickLister(evt -> handleEditProject(project)))
										.appendChild(span()
												.addCss("material-symbols-rounded", "mui-icon", "project-table-icon")
												.textContent("delete")
												.addClickLister(evt -> handleDeleteProject(project)))
										.element())));
	}
	
	private void loadData() {
		ProjectApiClient.getProjects()
		.then(projects -> {
			projectTable.setData(projects);
			return null;
		})
		.catch_(error -> {
			DomGlobal.console.error(error); //temp
			return null;
		});
	}
	
	private void handleAddProject() {
		new ProjectEditWindow(null)
				.addCreateListener(this::createProject)
				.open();
	}
	
	private void handleEditProject(ProjectSummaryDto project) {
		ProjectApiClient.getProject(project.getId())
		.then(projectDetails -> {
			new ProjectEditWindow(projectDetails)
					.addUpdateListener(this::saveUpdate)
					.open();
			
			return null;
		})
		.catch_(error -> {
			DomGlobal.console.error(error); //temp
			return null;
		});
	}
	
	private void handleDeleteProject(ProjectSummaryDto project) {
		//TODO: Add "Are you sure?" dialog
		
		ProjectApiClient.deleteProject(project.getId())
		.then(nothing -> {
			loadData();
			return null;
		})
		.catch_(error -> {
			DomGlobal.console.error(error); //temp
			return null;
		});
	}
	
	public void saveUpdate(long id, ProjectUpdateDto update) {
		ProjectApiClient.updateProject(id, update)
		.then(nothing -> {
			loadData();
			return null;
		})
		.catch_(error -> {
			DomGlobal.console.error(error); //temp
			return null;
		});
	}
	
	public void createProject(ProjectCreateDto creation) {
		ProjectApiClient.createProject(creation)
		.then(project -> {
			loadData();
			return null;
		})
		.catch_(error -> {
			DomGlobal.console.error(error); //temp
			return null;
		});
	}
	
	@Override
	public HTMLDivElement element() {
		return root.element();
	}
}
