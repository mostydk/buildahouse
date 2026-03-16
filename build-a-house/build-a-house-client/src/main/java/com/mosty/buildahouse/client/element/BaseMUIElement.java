package com.mosty.buildahouse.client.element;

import elemental2.dom.Element;
import elemental2.dom.Node;

public abstract class BaseMUIElement<T extends BaseMUIElement<T, E>, E extends Element> {
	private T mElement;
	public abstract E element();
	
	public void init(T mElement) {
		this.mElement = mElement;
	}
	
	public T appendChild(BaseMUIElement<?, ?> mElement) {
		element().appendChild(mElement.element());
		return this.mElement;
	}

	public T appendChild(Element element) {
		element().appendChild(element);
		return mElement;
	}
	
	public T setTextContent(String text) {
		element().textContent = text;
		return mElement;
	}
	
	public T clearElement() {
		Element element = element();
		
		for (Node child : element.childNodes.asList()) {
			element.removeChild(child);
		}
		
		return mElement;
	}
	
	public T addCss(String cls) {
		element().classList.add(cls);
		return mElement;
	}
}
