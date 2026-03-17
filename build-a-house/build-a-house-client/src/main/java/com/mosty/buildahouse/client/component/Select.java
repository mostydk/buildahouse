package com.mosty.buildahouse.client.component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.mosty.buildahouse.client.element.BaseElement.DivElement;
import com.mosty.buildahouse.client.element.BaseMUIElement;
import com.mosty.buildahouse.client.element.ElementsFactory;
import com.mosty.buildahouse.client.element.SelectElement;

import elemental2.dom.HTMLDivElement;

public class Select<T> extends BaseMUIElement<Select<T>, HTMLDivElement> implements ElementsFactory {	
	private DivElement root;
	private SelectElement select;
	private Map<String, T> titleOptions = new HashMap<>();
	private Map<T, String> valueOptions = new HashMap<>();
	
	public static <T> Select<T> create(String label) {
		return new Select<>(label);
	}
	
	public Select(String label) {
		root = div()
				.appendChild(div()
						.addCss("mui-input-label")
						.textContent(label))
				.appendChild(select = select()
						.addCss("mui-input"));
		
		init(this);
	}
	
	public Select<T> cleatOptions() {
		select.clearElement();
		titleOptions.clear();
		valueOptions.clear();
		return this;
	}
	
	public Select<T> addOption(T value, String title) {
		select.appendChild(option().textContent(title));
		titleOptions.put(title, value);
		valueOptions.put(value, title);
		return this;
	}
	
	public Select<T> addOptions(T[] values, Function<T, String> titleFunction) {
		for (T value : values) {
			addOption(value, titleFunction.apply(value));
		}
		
		return this;
	}
	
	public Select<T> withValue(T value) {
		select.setValue(valueOptions.get(value));
		return this;
	}
	
	public T getValue() {
		if (select.getValue().equals(""))
			return null;
		
		return titleOptions.get(select.getValue());
	}
	
	public void setValue(T value) {
		select.setValue(valueOptions.get(value));
	}
	
	@Override
	public HTMLDivElement element() {
		return root.element();
	}
}
