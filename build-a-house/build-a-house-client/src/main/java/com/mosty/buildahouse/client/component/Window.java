package com.mosty.buildahouse.client.component;

import java.util.function.Consumer;

import com.mosty.buildahouse.client.element.BaseElement.DivElement;
import com.mosty.buildahouse.client.element.BaseMUIElement;
import com.mosty.buildahouse.client.element.ElementsFactory;

import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLDivElement;

public class Window extends BaseMUIElement<Window, HTMLDivElement> implements ElementsFactory {
	private Element appendTarget;
	private DivElement overlayElement;
	private DivElement windowElement;
	private DivElement headerElement;
	private DivElement titleElement;
	private DivElement bodyElement;
	private DivElement footerElement;
	private EventListener overlayClickListener;
	
	public Window crete(String title) {
		return new Window(title);
	}
	
	public Window(String title) {
		overlayElement = div();
		appendTarget = overlayElement.element();
		init(this);
		
		overlayClickListener = evt -> {
			if (evt.target == overlayElement.element())
				close();
		};
		
		overlayElement
				.addCss("mui-window-overlay")
				.appendChild(windowElement = div()
						.addCss("mui-window")
						.appendChild(headerElement = div()
								.addCss("mui-window-header")
								.appendChild(titleElement = div()
										.addCss("mui-window-title")
										.textContent(title)))
						.appendChild(bodyElement = div()
								.addCss("mui-window-body"))
						.appendChild(footerElement = div()
								.addCss("mui-window-footer")));
		
		appendTarget = bodyElement.element();
	}
	
	public Window closeOnOverlayClick(boolean closeOnClick) {
		overlayElement.removeClickLister(overlayClickListener);
		if (closeOnClick)
			overlayElement.addClickLister(overlayClickListener);
		
		return this;
	}
	
	public Window withFooter(Consumer<DivElement> footerConsumer) {
		footerConsumer.accept(footerElement);
		return this;
	}
	
	public Window withHeader(Consumer<DivElement> headerConsumer) {
		headerConsumer.accept(headerElement);
		return this;
	}
	
	public Window withTitle(Consumer<DivElement> titleConsumer) {
		titleConsumer.accept(titleElement);
		return this;
	}
	
	public Window open() {
		DomGlobal.document.body.appendChild(overlayElement.element());
		return this;
	}
	
	public Window close() {
		DomGlobal.document.body.removeChild(overlayElement.element());
		return this;
	}
	
	@Override
	public HTMLDivElement element() {
		return windowElement.element();
	}
	
	@Override
	protected Element getAppendTarget() {
		return appendTarget;
	}
}
