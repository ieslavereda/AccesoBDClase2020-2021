package es.mordor.mordorLloguer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

public class MySqlDataBase implements AlmacenDatosDB {

	private ArrayList<Empleado> getCustomEmpleados(String where) {

		ArrayList<Empleado> empleados = new ArrayList<Empleado>();

		DataSource ds = MyDataSource.getMySQLDataSource();

		String query = "SELECT * FROM EMPLEADO";
		if (where != null)
			query += " WHERE " + where;

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			Empleado empleado;

			while (rs.next()) {
				empleado = new Empleado(rs.getInt("idEmpleado"), rs.getString("DNI"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("CP"), rs.getString("email"), rs.getDate("fechaNac"),
						rs.getString("cargo"), rs.getString("domicilio"), rs.getString("password"));

				empleados.add(empleado);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empleados;
	}

	@Override
	public ArrayList<Empleado> getEmpleados() {
		return getCustomEmpleados(null);
	}

	@Override
	public ArrayList<Empleado> getEmpleadosPorCP(String cp) {
		return getCustomEmpleados("CP=" + cp);
	}

	@Override
	public ArrayList<Empleado> getEmpleadosPorCargo(String cargo) {
		return getCustomEmpleados("cargo='" + cargo + "'");
	}

	@Override
	public Empleado getEmpleadoPorDNI(String dni) {
		ArrayList<Empleado> empleados = getCustomEmpleados("DNI='" + dni + "'");
		if (empleados.size() == 0)
			return null;
		else
			return empleados.get(0);
	}

	@Override
	public boolean updateEmpleado(Empleado empleado) {

		boolean actualizado = false;

		DataSource ds = MyDataSource.getMySQLDataSource();

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();) {

			String query = "UPDATE EMPLEADO SET nombre='" + empleado.getNombre() + "', " +
												"apellidos='" + empleado.getApellidos() + "'," + 
												((empleado.getDomicilio()!=null)?"domicilio='" + empleado.getDomicilio() + "',":"") + 
												((empleado.getCP()!=null)?"CP='"	+ empleado.getCP() + "',":"") + 
												"email='" + empleado.getEmail() + "'," + 
												"fechaNac='" + empleado.getFechaNac() + "'," + 
												"cargo='" + empleado.getCargo() + "' " + 
												"WHERE DNI='" + empleado.getDNI() + "'";

			
			if(stmt.executeUpdate(query)==1)
				actualizado=true;

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return actualizado;

	}

	@Override
	public boolean deleteEmpleado(String dni) {
		DataSource ds = MyDataSource.getMySQLDataSource();
		String query = "DELETE FROM EMPLEADO " +
						"WHERE DNI='" + dni +"'";
		
		boolean eliminado = false;
		
		try(
			Connection con = ds.getConnection();
			Statement stmt = con.createStatement();
		) {
			
			if(stmt.executeUpdate(query)==1)
				eliminado=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return eliminado;
	}

	@Override
	public boolean authenticate(String dni, String password) {
		
		DataSource ds = MyDataSource.getMySQLDataSource();
		boolean valido = false;
		String query = "SELECT COUNT(*) FROM EMPLEADO " + 
						"WHERE DNI=? AND password=?";
		
		try(
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
		){
			
			pstmt.setString(1, dni);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			if(rs.getInt(1)>0)
				valido=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return valido;
	}
	// NO UTILIZAR NUNCA 
//	@Override
//	public boolean authenticate(String dni, String password) {
//		
//		DataSource ds = MyDataSource.getMySQLDataSource();
//		boolean valido = false;
//		String query = "SELECT COUNT(*) FROM EMPLEADO " + 
//						"WHERE DNI='" + dni+ "' AND "+
//								"password='"+password+"'";
//		
//		System.out.println(query);
//		try(
//			Connection con = ds.getConnection();
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(query)
//		){
//			
//			rs.next();
//			if(rs.getInt(1)>0)
//				valido=true;
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//		
//		return valido;
//	}

}



