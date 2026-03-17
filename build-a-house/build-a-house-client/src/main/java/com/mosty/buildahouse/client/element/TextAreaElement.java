package com.mosty.buildahouse.client.element;

import elemental2.dom.HTMLTextAreaElement;

public class TextAreaElement extends BaseElement<TextAreaElement, HTMLTextAreaElement> {
	public static TextAreaElement of(HTMLTextAreaElement element) {
		return new TextAreaElement(element);
	}
	
	public TextAreaElement(HTMLTextAreaElement element) {
		super(element);
	}
	
	public TextAreaElement withValue(String value) {
		element().value = value;
		return this;
	}
	
	public String getValue() {
		return element().value;
	}
	
	public void setValue(String value) {
		element().value = value;
	}
}
