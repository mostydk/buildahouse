package com.mosty.buildahouse.client.component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.mosty.buildahouse.client.element.BaseElement.DivElement;
import com.mosty.buildahouse.client.element.BaseMUIElement;
import com.mosty.buildahouse.client.element.ElementsFactory;

import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;

public class FlexTable<T> extends BaseMUIElement<FlexTable<T>, HTMLDivElement> implements ElementsFactory {
	public static class Column<T> {
		private String title;
		private Function<T, Element> columnRenderer;
		private boolean grow = false;
		private DivElement columnDiv;
		
		public static <T> Column<T> create() {
			return new Column<T>();
		}
		
		public Column<T> setTitle(String title) {
			this.title = title;	
			return this;
		}
		
		public Column<T> setColumnRenderer(Function<T, Element> columnRenderer) {
			this.columnRenderer = columnRenderer;
			return this;
		}

		public Column<T> setGrow(boolean grow) {
			this.grow = grow;
			return this;
		}
	}
	
	private DivElement root;
	private List<Column<T>> columns = new ArrayList<>();
	
	public static <T> FlexTable<T> create() {
		return new FlexTable<T>();
	}
	
	public FlexTable() {
		root = div()
				.addCss("flex-table");
	}
	
	public FlexTable<T> addColumn(Column<T> column) {
		columns.add(column);
		return this;
	}
	
	public void setData(List<T> data) {
		renderTable(data);
	}
	
	@Override
	public HTMLDivElement element() {
		return root.element();
	}
	
	private void renderTable(List<T> data) {
		root.clearElement();
		
		for (Column<T> column : columns) {
			root.appendChild(column.columnDiv = div()
					.addCss("flex-column")
					.appendChild(div()
							.addCss("flex-header")
							.setTextContent(column.title)));
			
			if (column.grow)
				column.columnDiv.addCss("flex-column-grow");
		}
		
		for (T rowData : data) {
			for (Column<T> column : columns) {
				column.columnDiv.appendChild(div()
						.addCss("flex-cell")
						.appendChild(column.columnRenderer.apply(rowData)));
			}
		}
	}
}
