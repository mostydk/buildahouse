package com.mosty.buildahouse.client.project;

import com.mosty.buildahouse.client.api.ProjectApiClient;
import com.mosty.buildahouse.client.component.FlexTable;
import com.mosty.buildahouse.client.component.FlexTable.Column;
import com.mosty.buildahouse.client.element.BaseElement.DivElement;
import com.mosty.buildahouse.client.element.BaseMElement;
import com.mosty.buildahouse.client.element.ElementsFactory;
import com.mosty.buildahouse.shared.project.ProjectSummaryDto;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;

public class ProjectPage extends BaseMElement<ProjectPage, HTMLDivElement> implements ElementsFactory {
	private DivElement root;
	private FlexTable<ProjectSummaryDto> projectTable;
	
	public ProjectPage() {
		root = div();
		init(this);
		
		root.appendChild(projectTable = FlexTable.<ProjectSummaryDto>create()
				.addColumn(Column.<ProjectSummaryDto>create()
						.setTitle("#")
						.setColumnRenderer(project -> div()
								.setTextContent(Long.toString(project.getId()))
								.element()))
				.addColumn(Column.<ProjectSummaryDto>create()
						.setTitle("Name")
						.setGrow(true)
						.setColumnRenderer(project -> div()
								.setTextContent(project.getName())
								.element()))
				.addColumn(Column.<ProjectSummaryDto>create()
						.setTitle("State")
						.setColumnRenderer(project -> div()
								.setTextContent(project.getState().getTitle())
								.element())));
		
		ProjectApiClient.getProjects()
		.then(projects -> {
			projectTable.setData(projects);
			return null;
		})
		.catch_(error -> {
			DomGlobal.console.log(error);
			return null;
		});
	}
	
	@Override
	public HTMLDivElement element() {
		return root.element();
	}
}
