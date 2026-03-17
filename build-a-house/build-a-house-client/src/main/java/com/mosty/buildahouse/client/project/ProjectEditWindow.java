package com.mosty.buildahouse.client.project;


import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.mosty.buildahouse.client.component.Button;
import com.mosty.buildahouse.client.component.Select;
import com.mosty.buildahouse.client.component.TextArea;
import com.mosty.buildahouse.client.component.TextBox;
import com.mosty.buildahouse.client.component.Window;
import com.mosty.buildahouse.client.element.ElementsFactory;
import com.mosty.buildahouse.shared.project.ProjectCreateDto;
import com.mosty.buildahouse.shared.project.ProjectDto;
import com.mosty.buildahouse.shared.project.ProjectStateEnum;
import com.mosty.buildahouse.shared.project.ProjectUpdateDto;

public class ProjectEditWindow extends Window implements ElementsFactory {
	private Long id;
	private TextBox nameField;
	private TextArea descriptionField;
	private Select<ProjectStateEnum> stateField;
	private List<BiConsumer<Long, ProjectUpdateDto>> updateListeners = new ArrayList<>();
	private List<Consumer<ProjectCreateDto>> createListeners = new ArrayList<>();
	
	public ProjectEditWindow(ProjectDto project) {
		super(project == null ? "Add New Project" : "Edit '" + project.getName() + "'");
		
		addCss("project-edit-window");
		appendChild(div()
				.appendChild(nameField = TextBox.create("Name"))
				.appendChild(descriptionField = TextArea.create("Description"))
				.appendChild(stateField = Select.<ProjectStateEnum>create("State")
						.addOptions(ProjectStateEnum.values(), state -> state.getTitle())
						.hide()));
		
		withFooter(footer -> footer
				.appendChild(Button.create("Cancel")
						.addCss("mui-margin-left-auto")
						.addClickLister(evt -> close()))
				.appendChild(Button.create("Save")
						.addCss("mui-margin-left-1")
						.addClickLister(evt -> submit())));
		
		if (project != null) {
			id = project.getId();
			stateField.show();
			populateForm(project);
		}
	}
	
	public ProjectEditWindow addUpdateListener(BiConsumer<Long, ProjectUpdateDto> listener) {
		updateListeners.add(listener);
		return this;
	}
	
	public ProjectEditWindow addCreateListener(Consumer<ProjectCreateDto> listener) {
		createListeners.add(listener);
		return this;
	}
	
	private void populateForm(ProjectDto project) {
		nameField.setValue(project.getName());
		descriptionField.setValue(project.getDescription());
		stateField.setValue(project.getState());
	}
	
	private void submit() {
		//TODO: validate
				
		if (id != null && updateListeners.size() > 0) {
			ProjectUpdateDto update = new ProjectUpdateDto();
			update.setName(nameField.getValue());
			update.setDescription(descriptionField.getValue());
			update.setState(stateField.getValue());
			
			updateListeners.forEach(listener -> listener.accept(id, update));
		} else if (createListeners.size() > 0) {
			ProjectCreateDto creation = new ProjectCreateDto();
			creation.setName(nameField.getValue());
			creation.setDescription(descriptionField.getValue());
			
			createListeners.forEach(listener -> listener.accept(creation));
		}
		
		close();
	}
}
