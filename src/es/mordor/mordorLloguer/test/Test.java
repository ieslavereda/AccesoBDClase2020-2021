package es.mordor.mordorLloguer.test;

import es.mordor.mordorLloguer.model.AlmacenDatosDB;
import es.mordor.mordorLloguer.model.Empleado;
import es.mordor.mordorLloguer.model.MyOracleDataBase;
import es.mordor.mordorLloguer.model.MySqlDataBase;

public class Test {

	public static void main(String[] args) {
		
		AlmacenDatosDB modelo = new MySqlDataBase();
		
		Empleado e = modelo.getEmpleadoPorDNI("43210987V");
		e.setDomicilio("La Vereda");
		modelo.updateEmpleado(e);
		
	}

}
