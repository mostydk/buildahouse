package com.mosty.buildahouse.client.element;

import com.mosty.buildahouse.client.element.BaseElement.ButtonElement;
import com.mosty.buildahouse.client.element.BaseElement.DivElement;
import com.mosty.buildahouse.client.element.BaseElement.OptionElement;
import com.mosty.buildahouse.client.element.BaseElement.SpanElement;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLInputElement;
import elemental2.dom.HTMLOptionElement;
import elemental2.dom.HTMLSelectElement;
import elemental2.dom.HTMLTextAreaElement;

public interface ElementsFactory {
	default DivElement div() {
		return DivElement.of((HTMLDivElement) DomGlobal.document.createElement("div"));
	}
	
	default SpanElement span() {
		return SpanElement.of((HTMLElement) DomGlobal.document.createElement("span"));
	}
	
	default InputElement input() {
		return InputElement.of((HTMLInputElement) DomGlobal.document.createElement("input"));
	}
	
	default TextAreaElement textarea() {
		return TextAreaElement.of((HTMLTextAreaElement) DomGlobal.document.createElement("textarea"));
	}
	
	default SelectElement select() {
		return SelectElement.of((HTMLSelectElement) DomGlobal.document.createElement("select"));
	}
	
	default OptionElement option() {
		return OptionElement.of((HTMLOptionElement) DomGlobal.document.createElement("option"));
	}
	
	default ButtonElement button() {
		return ButtonElement.of((HTMLButtonElement) DomGlobal.document.createElement("button"));
	}
}
