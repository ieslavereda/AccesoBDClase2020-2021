package es.mordor.mordorLloguer.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MyVehicleTableModel<T extends Vehicle> extends MyTableModel<T> {

	public MyVehicleTableModel(ArrayList<T> data) {
		super(Arrays.asList("dfsf","dsfas"),data);
		// TODO Auto-generated constructor stub
	}
	
	public Object getValueAt(int row, int col) {
		switch(col) {
		case 1: return data.get(row).getMatricula();
		default: return null;
		}
	}
}
