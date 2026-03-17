package com.mosty.buildahouse.client.element;

import elemental2.dom.HTMLInputElement;

public class InputElement extends BaseElement<InputElement, HTMLInputElement> {
	public static InputElement of(HTMLInputElement element) {
		return new InputElement(element);
	}
	
	public InputElement(HTMLInputElement element) {
		super(element);
	}
	
	public InputElement type(String type) {
		element().type = type;
		return this;
	}
	
	public String getValue() {
		return element().value;
	}
	
	public void setValue(String value) {
		element().value = value;
	}
}