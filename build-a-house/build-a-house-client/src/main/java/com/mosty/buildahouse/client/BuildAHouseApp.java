package com.mosty.buildahouse.client;
import com.mosty.buildahouse.client.element.BaseElement.DivElement;
import com.mosty.buildahouse.client.project.ProjectPage;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsType;

@JsType
public class BuildAHouseApp {
	public void onModuleLoad() {
		HTMLDivElement rootDiv = (HTMLDivElement) DomGlobal.document.getElementById("app");
		
		DivElement root = DivElement.of(rootDiv);
		
		root.appendChild(new ProjectPage());
	}
}
