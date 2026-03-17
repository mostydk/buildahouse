package com.mosty.buildahouse.client.component;

import com.mosty.buildahouse.client.element.BaseElement.DivElement;
import com.mosty.buildahouse.client.element.BaseMUIElement;
import com.mosty.buildahouse.client.element.ElementsFactory;
import com.mosty.buildahouse.client.element.TextAreaElement;

import elemental2.dom.HTMLDivElement;

public class TextArea extends BaseMUIElement<TextArea, HTMLDivElement> implements ElementsFactory {
	private DivElement root;
	private TextAreaElement textArea;
	
	public static TextArea create(String label) {
		return new TextArea(label);
	}
	
	public TextArea(String label) {
		root = div()
				.appendChild(div()
						.addCss("mui-input-label")
						.textContent(label))
				.appendChild(textArea = textarea()
						.addCss("mui-input"));
		
		init(this);
	}
	
	public TextArea withValue(String value) {
		textArea.element().value = value;
		return this;
	}
	
	public String getValue() {
		if (textArea.element().value.equals(""))
			return null;
		
		return textArea.element().value; 
	}
	
	public void setValue(String value) {
		textArea.element().value = value;
	}
	
	@Override
	public HTMLDivElement element() {
		return root.element();
	}
}
