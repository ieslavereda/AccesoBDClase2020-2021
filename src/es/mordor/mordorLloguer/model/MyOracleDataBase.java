package es.mordor.mordorLloguer.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

public class MyOracleDataBase implements AlmacenDatosDB {

	public ArrayList<Empleado> getCustomEmpleados(String where) {

		ArrayList<Empleado> empleados = new ArrayList<Empleado>();

		DataSource ds = MyDataSource.getOracleDataSource();

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
	public ArrayList<Empleado> getEmpleadosPorCP(String cp) {
		// TODO Auto-generated method stub
		return getCustomEmpleados("CP='" + cp + "'");
	}

	@Override
	public ArrayList<Empleado> getEmpleadosPorCargo(String cargo) {
		return getCustomEmpleados("cargo='" + cargo + "'");
	}

	@Override
	public ArrayList<Empleado> getEmpleados() {
		return getCustomEmpleados(null);

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
		// TODO Auto-generated method stub
		return false;
	}

}
