package com.mosty.buildahouse.client.element;

import com.mosty.buildahouse.client.element.BaseElement.DivElement;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;

public interface ElementsFactory {
	default DivElement div() {
		return DivElement.of((HTMLDivElement) DomGlobal.document.createElement("div"));
	}
}
