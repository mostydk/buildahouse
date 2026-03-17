package com.mosty.buildahouse.client.element;

import elemental2.dom.HTMLSelectElement;

public class SelectElement extends BaseElement<SelectElement, HTMLSelectElement> {
	public static SelectElement of(HTMLSelectElement element) {
		return new SelectElement(element);
	}
	
	public SelectElement(HTMLSelectElement element) {
		super(element);
	}
	
	public String getValue() {
		return element().value;
	}
	
	public void setValue(String value) {
		element().value = value;
	}
}
