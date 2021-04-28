package es.mordor.mordorLloguer.test;

import es.mordor.mordorLloguer.model.AlmacenDatosDB;
import es.mordor.mordorLloguer.model.MySqlDataBase;

public class Test {

	public static void main(String[] args) {
		
		AlmacenDatosDB modelo = new MySqlDataBase();
		
		System.out.println(modelo.getEmpleados());

	}

}
