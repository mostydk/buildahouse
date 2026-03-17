package com.mosty.buildahouse.client.component;

import com.mosty.buildahouse.client.element.BaseElement.DivElement;
import com.mosty.buildahouse.client.element.BaseMUIElement;
import com.mosty.buildahouse.client.element.ElementsFactory;
import com.mosty.buildahouse.client.element.InputElement;

import elemental2.dom.HTMLDivElement;

public class TextBox extends BaseMUIElement<TextBox, HTMLDivElement> implements ElementsFactory {
	private DivElement root;
	private InputElement input;
	
	public static TextBox create(String label) {
		return new TextBox(label);
	}
	
	public TextBox(String label) {
		root = div()
				.appendChild(div()
						.addCss("mui-input-label")
						.textContent(label))
				.appendChild(input = input()
						.type("text")
						.addCss("mui-input"));
		
		init(this);
	}
	
	public TextBox withValue(String value) {
		input.element().value = value;
		return this;
	}
	
	public String getValue() {
		if (input.element().value.equals(""))
			return null;
		
		return input.element().value; 
	}
	
	public void setValue(String value) {
		input.element().value = value;
	}
	
	@Override
	public HTMLDivElement element() {
		return root.element();
	}
}
