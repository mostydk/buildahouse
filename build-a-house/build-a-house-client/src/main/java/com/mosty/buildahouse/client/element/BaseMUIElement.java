package com.mosty.buildahouse.client.element;

import elemental2.dom.Element;
import elemental2.dom.EventListener;

public abstract class BaseMUIElement<T extends BaseMUIElement<T, E>, E extends Element> {
	private T muiElement;
	public abstract E element();
	
	public void init(T mElement) {
		this.muiElement = mElement;
	}
	
	public T appendChild(BaseMUIElement<?, ?> mElement) {
		getAppendTarget().appendChild(mElement.element());
		return this.muiElement;
	}

	public T appendChild(Element element) {
		getAppendTarget().appendChild(element);
		return muiElement;
	}
	
	public T hide() {
		element().classList.add("mui-hidden");
		return muiElement;
	}
	
	public T show() {
		element().classList.remove("mui-hidden");
		return muiElement;
	}
	
	public T textContent(String text) {
		element().textContent = text;
		return muiElement;
	}
	
	public T clearElement() {
		Element element = element();
		
		while (element.firstChild != null) {
			element.removeChild(element.firstChild);
		}
		
		return muiElement;
	}
	
	public T setStyle(String style) {
		element().setAttribute("style", style);
		return muiElement;
	}
	
	public T addCss(String... cls) {
		element().classList.add(cls);
		return muiElement;
	}
	
	public T addClickLister(EventListener listener) {
		element().addEventListener("click", listener);
		return muiElement;
	}
	
	public T removeClickLister(EventListener listener) {
		element().removeEventListener("click", listener);
		return muiElement;
	}
	
	protected Element getAppendTarget() {
		return element();
	}
}
