package com.mosty.buildahouse.client;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsType;

@JsType
public class BuildAHouseApp {
	public void onModuleLoad() {
		HTMLDivElement div = (HTMLDivElement) DomGlobal.document.createElement("div");
		div.textContent = "This is a test!";
		
		DomGlobal.document.body.append(div);
	}
}
