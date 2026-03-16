package com.mosty.buildahouse.client.element;

import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;

public abstract class BaseElement<T extends BaseElement<T, E>, E extends Element> extends BaseMUIElement<T, E> {
	private E element;
	
	@SuppressWarnings("unchecked")
	protected BaseElement(E element) {
		this.element = element;
		init((T)this);
	}
	
	@Override
	public E element() {
		return element;
	}
	
	public static class DivElement extends BaseElement<DivElement, HTMLDivElement> {
		public static DivElement of(HTMLDivElement element) {
			return new DivElement(element);
		}
		
		public DivElement(HTMLDivElement element) {
			super(element);
		}
	}

}
