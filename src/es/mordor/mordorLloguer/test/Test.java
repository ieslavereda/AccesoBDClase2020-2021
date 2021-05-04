package es.mordor.mordorLloguer.test;

import es.mordor.mordorLloguer.model.AlmacenDatosDB;
import es.mordor.mordorLloguer.model.Empleado;
import es.mordor.mordorLloguer.model.MyOracleDataBase;
import es.mordor.mordorLloguer.model.MySqlDataBase;

public class Test {

	public static void main(String[] args) {
		
		AlmacenDatosDB modelo = new MySqlDataBase();
		String login = "87654321Z";
		String password = "1' OR DNI='87654321Z' and password!='1"; 
		System.out.println(modelo.authenticate(login, password));
		
		password="1111";
		System.out.println(modelo.authenticate(login, password));
		
	}

}
