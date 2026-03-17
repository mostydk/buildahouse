package com.mosty.buildahouse.client.element;

import elemental2.dom.Element;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLOptionElement;

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

	public static class SpanElement extends BaseElement<SpanElement, HTMLElement> {
		public static SpanElement of(HTMLElement element) {
			return new SpanElement(element);
		}
		
		public SpanElement(HTMLElement element) {
			super(element);
		}
	}
	
	public static class OptionElement extends BaseElement<OptionElement, HTMLOptionElement> {
		public static OptionElement of(HTMLOptionElement element) {
			return new OptionElement(element);
		}
		
		public OptionElement(HTMLOptionElement element) {
			super(element);
		}
	}
	
	public static class ButtonElement extends BaseElement<ButtonElement, HTMLButtonElement> {
		public static ButtonElement of(HTMLButtonElement element) {
			return new ButtonElement(element);
		}
		
		public ButtonElement(HTMLButtonElement element) {
			super(element);
		}
	}
}
