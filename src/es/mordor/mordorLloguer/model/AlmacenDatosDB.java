package es.mordor.mordorLloguer.model;

import java.util.ArrayList;

public interface AlmacenDatosDB {

	public ArrayList<Empleado> getEmpleados();
	public ArrayList<Empleado> getEmpleadosPorCP(String cp);
	public ArrayList<Empleado> getEmpleadosPorCargo(String cargo);
	public Empleado getEmpleadoPorDNI(String dni);
	public boolean updateEmpleado(Empleado empleado);
	public boolean deleteEmpleado(String dni);
	public boolean authenticate(String dni,String password);
	
}
