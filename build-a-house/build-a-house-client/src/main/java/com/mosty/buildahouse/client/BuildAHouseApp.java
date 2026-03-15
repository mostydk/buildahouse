package com.mosty.buildahouse.client;
import com.mosty.buildahouse.client.element.BaseElement.DivElement;
import com.mosty.buildahouse.client.project.ProjectPage;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsType;

@JsType
public class BuildAHouseApp {
	public void onModuleLoad() {
		HTMLDivElement rootElement = (HTMLDivElement) DomGlobal.document.getElementById("app");
		if (rootElement == null) {
			DomGlobal.console.log("index.html is missing div element with 'app' id!");
			return;
		}
		
		DivElement.of(rootElement)
				.appendChild(new ProjectPage());
	}
}
