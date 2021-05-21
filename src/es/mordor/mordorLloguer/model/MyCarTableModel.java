package es.mordor.mordorLloguer.model;
import java.util.ArrayList;

public class MyCarTableModel extends MyVehicleTableModel<Coche>{

	public MyCarTableModel(ArrayList<Coche> data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
	
	public Object getValueAt(int row, int col) {
		switch(col) {
		case 7: return data.get(row).getNumPlazas();
		case 8: return data.get(row).getNumPuertas();
		default: return super.getValueAt(row, col);
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}

}
