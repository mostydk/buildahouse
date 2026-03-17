package com.mosty.buildahouse.client.component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.mosty.buildahouse.client.element.BaseElement.DivElement;
import com.mosty.buildahouse.client.element.BaseMUIElement;
import com.mosty.buildahouse.client.element.ElementsFactory;

import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;

public class GridTable<T> extends BaseMUIElement<GridTable<T>, HTMLDivElement> implements ElementsFactory {
	public static class Column<T> {
		private String title;
		private Function<T, Element> columnRenderer;
		private boolean grow = false;
		
		public static <T> Column<T> create() {
			return new Column<T>();
		}
		
		public Column<T> setTitle(String title) {
			this.title = title;	
			return this;
		}
		
		public Column<T> setCellRenderer(Function<T, Element> columnRenderer) {
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
	
	public static <T> GridTable<T> create() {
		return new GridTable<T>();
	}
	
	public GridTable() {
		root = div()
				.addCss("mui-flex-table");
	}
	
	public GridTable<T> addColumn(Column<T> column) {
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
		root.setStyle("grid-template-columns: " + generateGridTemplateColumns() + ";");
		
		for (Column<T> column : columns) {
			root.appendChild(div()
					.addCss("mui-flex-table-header")
					.textContent(column.title));
			
		}
		
		for (T rowData : data) {
			for (Column<T> column : columns) {
				root.appendChild(div()
						.addCss("mui-flex-table-cell")
						.appendChild(column.columnRenderer.apply(rowData)));
			}
		}
	}
	
	private String generateGridTemplateColumns() {
		StringBuilder sb = new StringBuilder();
		
		for (Column<T> column : columns) {
			if (column.grow)
				sb.append("minmax(0, 1fr) ");
			else
				sb.append("minmax(0, auto) ");
		}
		
		return sb.toString();
	}
}
