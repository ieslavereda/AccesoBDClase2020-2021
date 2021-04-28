package es.mordor.mordorLloguer.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

import oracle.jdbc.datasource.impl.OracleDataSource;

public class MyDataSource {

	private static String defaultProperties = "db.properties";

	public static DataSource getMySQLDataSource() {

		// Propiedades donde tenemos los datos de acceso a la BD

		Properties props = new Properties();

		// Objeto DataSource que devolveremos

		MysqlDataSource mysqlDS = null;

		try (FileInputStream fis = new FileInputStream(defaultProperties);) {

			// Cargamos las propiedades

			props.load(fis);

			// Generamos el DataSource con los datos URL, user y passwd necesarios

			mysqlDS = new MysqlDataSource();

			mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
			mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
			mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));

		} catch (IOException e) {

			e.printStackTrace();

		}

		return mysqlDS;

	}
	
}
