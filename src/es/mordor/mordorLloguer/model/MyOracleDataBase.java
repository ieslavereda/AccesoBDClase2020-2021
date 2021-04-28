package es.mordor.mordorLloguer.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

public class MyOracleDataBase implements AlmacenDatosDB{

	@Override
	public ArrayList<Empleado> getEmpleados() {
		
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		
		DataSource ds = MyDataSource.getMySQLDataSource();
		
		try(
			Connection con = ds.getConnection();
			Statement stmt = con.createStatement();	
			ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLEADO")
			){
			
			Empleado empleado;
			
			while(rs.next()) {
				empleado = new Empleado(rs.getInt("idEmpleado"),
										rs.getString("DNI"),
										rs.getString("nombre"),
										rs.getString("apellidos"),
										rs.getString("CP"),
										rs.getString("email"),
										rs.getDate("fechaNac"),
										rs.getString("cargo"),
										rs.getString("domicilio"),
										rs.getString("password"));
				
				empleados.add(empleado);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return empleados;
	}
	

}
