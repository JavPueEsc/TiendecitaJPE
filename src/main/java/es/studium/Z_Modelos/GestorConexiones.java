package es.studium.Z_Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorConexiones {
	private final static String MySQL_DB_USUARIO = "root";
	private final static String MySQL_DB_PASSWORD = "Studium2023;";

	private final static String MySQL_DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String MySQL_DB_URL = "jdbc:mysql://localhost/";

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