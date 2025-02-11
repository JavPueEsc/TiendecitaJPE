package es.studium.Z_Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * La clase <b>GestorConexiones</b> contiene el método para establecer la conexión de la aplicación con la base de datos
 * y también aquellos métodos que se utilizan para cerrar los recursos asociados a una consulta SQL y finalizar la conexión.
 * @author Javier Pueyo
 * @version 2.0
 */
public class GestorConexiones {
	private final static String MySQL_DB_USUARIO = "root";
	private final static String MySQL_DB_PASSWORD = "Studium2023;";
	private final static String MySQL_DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String MySQL_DB_URL = "jdbc:mysql://localhost/";

	/**
	 * La función del método <b>getMySQL_Connection()</b> es establecer y devolver una conexión con una base de datos MySQL específica.
	 * @param database Parámetro de tipo {@code String}. Se corersponde con el nombre de la base de datos a la que se conectará la aplicación.
	 * @return connMySQL que es un objeto de tipo {@code Connection} que establece la conexión con la base de datos especificada.
	 *    Tomará el valor {@code null} si la conexión no se pudo establecer.
	 */
	public static Connection getMySQL_Connection(String database) {

		Connection connMySQL = null;
		try {
			Class.forName(MySQL_DB_DRIVER);
			connMySQL = DriverManager.getConnection(MySQL_DB_URL + database
					, MySQL_DB_USUARIO, MySQL_DB_PASSWORD);

			System.out.println("La conexión a la base de datos " + database 
					+ " se ha realizado correctamente.");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException eq) {
			eq.printStackTrace();
		}
		return connMySQL;
	}
	
	/**
	 * La función del método <b>cerrarRecursosPst()</b> es cerrar los recursos asociados a una consulta SQL 
	 * para liberar memoria y finalizar la conexión con la base de datos. Existen dos versiones de este método. Esta versión recibe 3 parámetros.
	 * @param conexion Parámetro de tipo {@code Connection}. Representa la conexión activa de la aplicación con la base de datos.
	 * @param pst Parámtero de tipo {@code PreparedStatement}. Representa la consulta SQL que ha sido ejecutada.
	 * @param rs Parámetro de tipo {@code ResultSet}. Representa el resultado de la consulta SQL que ha sido ejecutada.
	 */
	public static void cerrarRecursosPst(Connection conexion, PreparedStatement pst, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (conexion != null) {
				conexion.close();
			}
		} catch (SQLException e) {
			System.err.println("Error cerrando recursos " + e.getMessage());
		}
	}

	/**
	 * La función del método <b>cerrarRecursosPst()</b> es cerrar los recursos asociados a una consulta SQL 
	 * para liberar memoria y finalizar la conexión con la base de datos. Existen dos versiones de este método. Esta versión recibe 2 parámetros.
	 * @param conexion Parámetro de tipo {@code Connection}. Representa la conexión activa de la aplicación con la base de datos.
	 * @param pst Parámtero de tipo {@code PreparedStatement}. Representa la consulta SQL que ha sido ejecutada.
	 */
	public static void cerrarRecursosPst(Connection conexion, PreparedStatement pst) {
		try {
			if (pst != null) {
				pst.close();
			}
			if (conexion != null) {
				conexion.close();
			}
		} catch (SQLException e) {
			System.err.println("Error cerrando recursos " + e.getMessage());
		}
	}
	
	/**
	 * La función del método <b>cerrarRecursosSt()</b> es cerrar los recursos asociados a una consulta SQL 
	 * para liberar memoria y finalizar la conexión con la base de datos. 
	 * @param conexion Parámetro de tipo {@code Connection}. Representa la conexión activa de la aplicación con la base de datos.
	 * @param st Parámetro de tipo{@code Statement}. Representa la consulta SQL que ha sido ejecutada.
	 * @param rs Parámetro de tipo {@code ResultSet}. Representa el resultado de la consulta SQL que ha sido ejecutada.
	 */
	public static void cerrarRecursosSt(Connection conexion, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conexion != null) {
				conexion.close();
			}
		} catch (SQLException e) {
			System.err.println("Error cerrando recursos " + e.getMessage());
		}
	}

}