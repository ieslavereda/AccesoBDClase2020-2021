package es.mordor.mordorLloguer.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class MyTableModel<T> extends AbstractTableModel {

	
	protected List<String> header;
	protected List<T> data;

	public MyTableModel(List<String> header,List<T> data) {
		this.data = data;
		this.header = header;
	}

	@Override
	public int getColumnCount() {

		return header.size();
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public String getColumnName(int column) {
		return header.get(column);
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
		case 2: return Date.class;
		default: return String.class;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0)
			return false;
		else
			return true;
	
	}

	public abstract void setValueAt(Object aValue, int rowIndex, int columnIndex);
	public abstract Object getValueAt(int row, int col) ;
	
	public void addEmployee(T empleado) {
		data.add(empleado);
		fireTableRowsInserted(data.size() - 1, data.size() - 1);

	}
	public ArrayList<T> getEmployee(int[] rows) {
		ArrayList<T> empleados = new ArrayList<T>();
		
		for(int row : rows)
			empleados.add(getEmployee(row));
		
		return empleados;
	}

	public T getEmployee(int row) {
		if (row < 0 || row >= data.size())
			return null;
		else
			return data.get(row);
	}
	
	public void removeElement(T employee) {
		int pos = data.indexOf(employee);
		data.remove(employee);
		fireTableRowsDeleted(pos, pos);
	}

}
