package com.mosty.buildahouse.client.component;

import com.mosty.buildahouse.client.element.BaseElement.ButtonElement;
import com.mosty.buildahouse.client.element.BaseMUIElement;
import com.mosty.buildahouse.client.element.ElementsFactory;

import elemental2.dom.HTMLButtonElement;

public class Button extends BaseMUIElement<Button, HTMLButtonElement> implements ElementsFactory {
	private ButtonElement button;
	
	public static Button create(String label) {
		return new Button(label);
	}
	
	public Button(String label) {
		button = button()
				.textContent(label);
		
		init(this);
	}
	
	@Override
	public HTMLButtonElement element() {
		return button.element();
	}
}
