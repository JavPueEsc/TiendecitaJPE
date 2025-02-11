package es.studium.Z_Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * La clase <b>ModeloMetodosBD</b> contiene los métodos para realizar operaciones CRUD sobre los artículos y tickets almacenados en la base de datos. 
 * @author Javier Pueyo
 * @version 2.0
 */
public class ModeloMetodosBD {

	 /**
	 * <p>La función del método <b>crearArticulo()</b> es insertar un nuevo registro en la tabla ARTICULOS 
	 * de la base de datos mediante una operación INSERT. </p>
	 * 
	 * <p>Adicionalmente, tras realizar la inserción, se 
	 * consulta la tabla ARTICULOS para obtener el valor de IDARTICULO asignado al nuevo registro.</p>
	 * @param descripcion Parámetro de tipo {@code String}. Se corresponde con el nombre del nuevo artículo que se desea incluir en la base de datos.
	 * @param precio Parámetro de tipo {@code float}. Se corresponde con el precio del nuevo artículo que se desea incluir en la base de datos.
	 * @param cantidad Parámetro de tipo {@code int}. Se corresponde con el número de unidades del nuevo artículo que se desea incluir en la base de datos.
	 */
	public static void crearArticulo(String descripcion, float precio, int cantidad) {
		int idArticulo;
		Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conexion = GestorConexiones.getMySQL_Connection("tiendecitajpe");

			String ins = "INSERT INTO ARTICULOS (DESCRIPCIONARTICULO,PRECIOARTICULO,CANTIDADARTICULO)"
					+ "VALUES (?,?,?)";

			pst = conexion.prepareStatement(ins);

			pst.setString(1, descripcion);
			pst.setFloat(2, precio);
			pst.setInt(3, cantidad);

			pst.executeUpdate();
			pst.clearParameters();

			String consulta = "SELECT IDARTICULO FROM ARTICULOS WHERE DESCRIPCIONARTICULO = ? AND PRECIOARTICULO = ? "
					+ "AND CANTIDADARTICULO = ?";
			pst = conexion.prepareStatement(consulta);
			pst.setString(1, descripcion);
			pst.setFloat(2, precio);
			pst.setInt(3, cantidad);

			rs = pst.executeQuery();
			if (rs.next()) {
				idArticulo = rs.getInt("IDARTICULO");
				System.out.println("El articulo ha sido dado de alta con el id: " + idArticulo);
			} else {
				System.err.println("No se ha dado de alta");
			}
			pst.clearParameters();
		} catch (SQLException sqle) {

			System.out.println("Error de SQL " + sqle.getMessage());
		} finally {
			GestorConexiones.cerrarRecursosPst(conexion, pst, rs);
		}
	}

	/**
	 * La función del método <b>mostrarArticulosEnTabla()</b> es recuperar todos los artículos almacenados en
	 * la tabla ARTICULOS de la base de datos y guardarlos en un modelo de tabla.
	 * @param nombreColumnas Parámetro de tipo {@code String[]}. Contiene los nombres de las columnas que se deben mostrar en la tabla.
	 * @return modeloTabla que es un objeto de tipo {@code DefaultTableModel}. Este objeto contiene los datos de los artículos recuperados de
	 * la base de datos. Si no se han encontrado artículos, devuelve un modelo de tabla vacío con las columnas definidas.
	 */
	public static DefaultTableModel mostrarArticulosEnTabla(String[] nombreColumnas) {
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		Object[] datosTabla = new Object[4];
		DefaultTableModel modeloTabla = new DefaultTableModel(nombreColumnas, 0) {
			// Se sobrescribe el método isCellEditable para que siempre devuelva false
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // No se puede editar ninguna celda
			}
		};

		try {
			conexion = GestorConexiones.getMySQL_Connection("tiendecitajpe");
			st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String consulta = "SELECT IDARTICULO, DESCRIPCIONARTICULO, PRECIOARTICULO, CANTIDADARTICULO "
					+ "FROM ARTICULOS";
			rs = st.executeQuery(consulta);

			while (rs.next()) {
				for (int i = 0; i < 4; i++) {
					datosTabla[i] = rs.getString(i + 1);
				}
				modeloTabla.addRow(datosTabla);
			}
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL");
		} finally {
			GestorConexiones.cerrarRecursosSt(conexion, st, rs);
		}

		return modeloTabla;
	}

	/**
	 * La función del método <b>eliminarArticulo()</b> es la de eliminar un artículo de la tabla ARTICULOS de
	 * la base de datos mediante una operacion DELETE.
	 * @param idArticulo Parámetro de tipo {@code String}. Se corresponde con el ID del artículo de la base de datos que se desea eliminar.
	 */
	public static void eliminarArticulo(String idArticulo) {
		Connection conexion = null;
		PreparedStatement pst = null;

		try {
			conexion = GestorConexiones.getMySQL_Connection("tiendecitajpe");
			String ins = "DELETE FROM ARTICULOS WHERE IDARTICULO = ?";
			pst = conexion.prepareStatement(ins);
			pst.setString(1, idArticulo);

			pst.executeUpdate();
			pst.clearParameters();
		} catch (SQLException sqle) {

			System.err.println("Error de SQL " + sqle.getMessage());

		} finally {
			GestorConexiones.cerrarRecursosPst(conexion, pst);
		}

	}

	/**
	 * La función del método <b>actualizarArticulo()</b> es la de actualizar el valor de los campos de un resgistro concreto
	 * de la tabla ARTICULOS de la base de datos utilizando como referencia el valor de su campo IDARTICULO.
	 * @param idArticulo Parámetro de tipo {@code String}. Se corresponde con el identificador del artículo cuyos campos se deseas mofificar.
	 * @param descripcion Parámetro de tipo {@code String}. Se corersponde con el nuevo nombre que se desea asignar al artículo.
	 * @param precio Parámetro de tipo {@code String}. Se corersponde con el nuevo precio que se le desea asignar al artículo.
	 * @param cantidad Parámetro de tipo {@code String}. Se corresponde con el nuevo número de unidades que se le desean asignar al artículo.
	 */
	public static void actualizarArticulo(String idArticulo, String descripcion, String precio, String cantidad) {
		Connection conexion = null;
		PreparedStatement pst = null;

		try {
			conexion = GestorConexiones.getMySQL_Connection("tiendecitajpe");
			String ins = "UPDATE ARTICULOS SET DESCRIPCIONARTICULO = ?, PRECIOARTICULO = ?,"
					+ "CANTIDADARTICULO = ? WHERE IDARTICULO = ?";
			pst = conexion.prepareStatement(ins);
			pst.setString(1, descripcion);
			pst.setFloat(2, Float.parseFloat(precio));
			pst.setInt(3, Integer.parseInt(cantidad));
			pst.setInt(4, Integer.parseInt(idArticulo));

			pst.executeUpdate();
			pst.clearParameters();
		} catch (SQLException sqle) {

			System.err.println("Error de SQL " + sqle.getMessage());

		} finally {
			GestorConexiones.cerrarRecursosPst(conexion, pst);
		}
	}

	/**
	 * <p>La función del método <b>crearTicket()</b> es insertar un nuevo registro en la tabla `TICKETS` 
	 * de la base de datos mediante una operación INSERT.</p>
	 * 
	 * <p>Luego, se consulta la tabla TICKETS para obtener el IDTICKET del ticket recién creado. </p>
	 * 
	 * <p>Con este ID, se insertan en la tabla PERTENENCIAS los artículos asociados al ticket, 
	 * estableciendo la relación entre cada IDARTICULOFK y el IDTICKETFK (los cuales contienen un valor similar a IDARTICULO e IDTICKET respectivamente), 
	 * junto con el precio total del ticket.</p>
	 * @param fecha Parámetro de tipo {@code String}. Representa la fecha en la que se emitió el ticket que se desea incluir en la base de datos.
	 * @param precioTotal Parámetro de tipo {@code String}. Representa la suma total de los precios de todos los artículos incluidos 
	 * en el ticket que se desea incluir en la base de datos.
	 * @param tabla Parámetro de tipo {@code Jtable}. Objeto que contiene los datos de los artículos incluidos en el ticket que se desea incluir en la base de datos.
	 */
	public static void crearTicket(String fecha, String precioTotal, JTable tabla) {
		int idTicket = 0;
		Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conexion = GestorConexiones.getMySQL_Connection("tiendecitajpe");
			String ins = "INSERT INTO TICKETS (FECHATICKET,TOTALTICKET) VALUES (?,?)";
			pst = conexion.prepareStatement(ins);

			pst.setString(1, Modelo.europeoMysql(fecha));
			pst.setFloat(2, Float.parseFloat(precioTotal));

			pst.executeUpdate();
			pst.clearParameters();

			String consulta = "SELECT IDTICKET FROM TICKETS WHERE FECHATICKET = ? AND TOTALTICKET = ? "
					+ "ORDER BY IDTICKET DESC LIMIT 1;";
			pst = conexion.prepareStatement(consulta);
			pst.setString(1, Modelo.europeoMysql(fecha));
			pst.setFloat(2, Float.parseFloat(precioTotal));

			rs = pst.executeQuery();

			if (rs.next()) {
				idTicket = rs.getInt("IDTICKET");
			}
			pst.clearParameters();

			String[] articulosdelTicket = Modelo.obtenerValoresColumna(tabla, 0);
			String[] cantidadesArticulosdelTicket = Modelo.obtenerValoresColumna(tabla, 3);

			for (int i = 0; i < articulosdelTicket.length; i++) {
				String ins2 = "INSERT INTO PERTENENCIAS (CANTIDADARTICULOTICKET,IDARTICULOFK,IDTICKETFK) "
						+ "VALUES (?,?,?)";
				pst = conexion.prepareStatement(ins2);

				pst.setInt(1, Integer.parseInt(cantidadesArticulosdelTicket[i]));
				pst.setInt(2, Integer.parseInt(articulosdelTicket[i]));
				pst.setInt(3, idTicket);

				pst.executeUpdate();
				pst.clearParameters();
			}

		} catch (SQLException sqle) {

			System.out.println("Error de SQL " + sqle.getMessage());
		} finally {
			GestorConexiones.cerrarRecursosPst(conexion, pst, rs);
		}
		System.err.println("id asignado al ticket" + idTicket);
	}

	/**
	 * La función del método <b>mostrarTicketsEnTabla()</b> es la de es recuperar todos los tickets almacenados en
	 * la tabla TICKETS de la base de datos y guardarlos en un modelo de tabla.
	 * @param nombreColumnas Parámetro de tipo {@code String[]}. Contiene los nombres de las columnas que se deben mostrar en la tabla.
	 * @return modeloTabla que es un objeto de tipo {@code DefaultTableModel}. Este objeto contiene los datos de los tickets recuperados de
	 * la base de datos. Si no se han encontrado artículos, devuelve un modelo de tabla vacío con las columnas definidas.
	 */
	public static DefaultTableModel mostrarTicketsEnTabla(String[] nombreColumnas) {
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;

		DefaultTableModel modeloTabla = new DefaultTableModel(nombreColumnas, 0) {
			// Se sobrescribe el método isCellEditable para que siempre devuelva false
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // No se puede editar ninguna celda
			}
		};
		try {
			conexion = GestorConexiones.getMySQL_Connection("tiendecitajpe");
			st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String consulta = "SELECT IDTICKET, FECHATICKET, TOTALTICKET FROM TICKETS";
			rs = st.executeQuery(consulta);

			while (rs.next()) {
				Object[] datosTabla = new Object[3];
				datosTabla[0] = rs.getInt("IDTICKET");
				datosTabla[1] = Modelo.mysqlEuropeo(rs.getDate("FECHATICKET") + "");
				datosTabla[2] = rs.getFloat("TOTALTICKET");
				modeloTabla.addRow(datosTabla);
			}

		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL");
		} finally {
			GestorConexiones.cerrarRecursosSt(conexion, st, rs);
		}

		return modeloTabla;
	}

	/**
	 * La función del método <b>mostrarArticulosTicketEnTabla()</b> es la de recuperar todos los artículos asociados
	 * a un ticket concreto para guardarlos en un modelo de tabla. Para ello realiza una consulta SQL que extrae información 
	 * tanto de la tabla PERTENENCIAS como de la tabla ARTICULOS.
	 * @param idTicket Parámetro de tipo {@code String}. Se corresponde con el identificador del ticket del que se desean obtener los artículos que incluye.
	 * @param nombreColumnas Parámetro de tipo {@code String[]}. Contiene los nombres de las columnas que se deben mostrar en la tabla.
	 * @return modeloTabla que es un objeto de tipo {@code DefaultTableModel}. Este objeto contiene los datos de los artículos pertenecientes a un ticket. 
	 * Si no se han encontrado artículos, devuelve un modelo de tabla vacío con las columnas definidas.
	 */
	public static DefaultTableModel mostrarArticulosTicketEnTabla(String idTicket, String[] nombreColumnas) {
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;

		DefaultTableModel modeloTabla = new DefaultTableModel(nombreColumnas, 0) {
			// Se sobrescribe el método isCellEditable para que siempre devuelva false
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // No se puede editar ninguna celda
			}
		};
		try {
			conexion = GestorConexiones.getMySQL_Connection("tiendecitajpe");
			st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String consulta = "SELECT P.IDPERTENENCIA, P.IDARTICULOFK, P.CANTIDADARTICULOTICKET, P.IDTICKETFK, "
					+ "A.DESCRIPCIONARTICULO, A.PRECIOARTICULO, A.CANTIDADARTICULO "
					+ "FROM PERTENENCIAS P JOIN ARTICULOS A ON P.IDARTICULOFK = A.IDARTICULO WHERE P.IDTICKETFK ="
					+ idTicket + "";
			rs = st.executeQuery(consulta);

			while (rs.next()) {
				Object[] datosTabla = new Object[4];
				datosTabla[0] = rs.getString("A.DESCRIPCIONARTICULO");
				datosTabla[1] = rs.getFloat("A.PRECIOARTICULO");
				datosTabla[2] = rs.getInt("P.CANTIDADARTICULOTICKET");
				datosTabla[3] = rs.getFloat("A.PRECIOARTICULO") * (float) rs.getInt("P.CANTIDADARTICULOTICKET");
				modeloTabla.addRow(datosTabla);
			}
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL");
		} finally {
			GestorConexiones.cerrarRecursosSt(conexion, st, rs);
		}

		return modeloTabla;
	}

	/**
	 * El método <b>eliminarTicket()</b> se encarga de eliminar un ticket de la tabla TICKETS 
	 * de la base de datos utilizando su identificador. Además, borra todos los registros 
	 * de la tabla PERTENENCIAS asociados a dicho ticket, eliminando así los artículos que contenía. 
	 * @param idTicket Parámetro de tipo {@code String}. Se corresponde con el identificador del ticket que se desea eliminar de la base de datos.
	 */
	public static void eliminarTicket(String idTicket) {
		Connection conexion = null;
		PreparedStatement pst = null;

		try {
			conexion = GestorConexiones.getMySQL_Connection("tiendecitajpe");
			String ins = "DELETE FROM TICKETS WHERE IDTICKET = ?";
			pst = conexion.prepareStatement(ins);
			pst.setString(1, idTicket);

			pst.executeUpdate();
			pst.clearParameters();

			String ins2 = "DELETE FROM PERTENENCIAS WHERE IDTICKETFK = ?";
			pst = conexion.prepareStatement(ins2);
			pst.setString(1, idTicket);

			pst.executeUpdate();
			pst.clearParameters();
		} catch (SQLException sqle) {

			System.err.println("Error de SQL " + sqle.getMessage());

		} finally {
			GestorConexiones.cerrarRecursosPst(conexion, pst);
		}

	}

	/**
	 *  El método <b>mostrarArticulosTicketEnTablaParaActualizar()</b> recupera todos los artículos asociados 
	 * a un ticket específico con el objetivo de mostrarlos en un modelo de tabla para su actualización. 
	 * Para ello, se realiza una consulta SQL que obtiene información de las tablas PERTENENCIAS y ARTICULOS.
	 * @param idTicket Parámetro de tipo {@code String}. Se corresponde con el identificador del ticket cuyos artículos se desean actualizar.
	 * @param nombreColumnas Parámetro de tipo {@code String[]}. Contiene los nombres de las columnas que se deben mostrar en la tabla.
	 * @return modeloTabla que es un objeto de tipo {@code DefaultTableModel}. Este objeto contiene los artículos que incluye el ticket que se desea actualizar. 
	 * Si no se han encontrado artículos, devuelve un modelo de tabla vacío con las columnas definidas.
	 */
	public static DefaultTableModel mostrarArticulosTicketEnTablaParaActualizar(String idTicket,
			String[] nombreColumnas) {
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;

		DefaultTableModel modeloTabla = new DefaultTableModel(nombreColumnas, 0) {
			// Se sobrescribe el método isCellEditable para que siempre devuelva false
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // No se puede editar ninguna celda
			}
		};
		try {
			conexion = GestorConexiones.getMySQL_Connection("tiendecitajpe");
			st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String consulta = "SELECT P.IDPERTENENCIA, P.IDARTICULOFK, P.CANTIDADARTICULOTICKET, P.IDTICKETFK, "
					+ "A.DESCRIPCIONARTICULO, A.PRECIOARTICULO, A.CANTIDADARTICULO "
					+ "FROM PERTENENCIAS P JOIN ARTICULOS A ON P.IDARTICULOFK = A.IDARTICULO WHERE P.IDTICKETFK ="
					+ idTicket + "";
			rs = st.executeQuery(consulta);

			while (rs.next()) {
				Object[] datosTabla = new Object[5];
				datosTabla[0] = rs.getInt("P.IDARTICULOFK");
				datosTabla[1] = rs.getString("A.DESCRIPCIONARTICULO");
				datosTabla[2] = rs.getFloat("A.PRECIOARTICULO");
				datosTabla[3] = rs.getInt("P.CANTIDADARTICULOTICKET");
				datosTabla[4] = rs.getFloat("A.PRECIOARTICULO") * (float) rs.getInt("P.CANTIDADARTICULOTICKET");
				modeloTabla.addRow(datosTabla);
			}
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL");
		} finally {
			GestorConexiones.cerrarRecursosSt(conexion, st, rs);
		}

		return modeloTabla;
	}

	/**
	 * El método <b>obtenerFechaTicket()</b> recupera la fecha de emisión de un ticket concreto realizando
	 * una consulta a la tabla TICKETS  de la base de datos.
	 * @param idTicket Parámetro de tipo {@code String}. Se corresponde con el identificador del ticket del que se desea conocer su fecha de emisión.
	 * @return fechaTicket que es un objeto de tipo {@code String}. Se corresponde con la fecha de emisión del ticket consultado.
	 */
	public static String obtenerFechaTicket(String idTicket) {
		String fechaTicket = "";
		Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conexion = GestorConexiones.getMySQL_Connection("tiendecitajpe");
			String consulta = "SELECT FECHATICKET FROM TICKETS WHERE IDTICKET = ?";
			pst = conexion.prepareStatement(consulta);
			pst.setString(1, idTicket);

			rs = pst.executeQuery();
			if (rs.next()) {
				fechaTicket = Modelo.mysqlEuropeo(rs.getDate("FECHATICKET") + "");
				System.out.println("La fecha del ticket es: " + fechaTicket);

			}
			pst.clearParameters();
		} catch (SQLException sqle) {

			System.out.println("Error de SQL " + sqle.getMessage());
		} finally {
			GestorConexiones.cerrarRecursosPst(conexion, pst, rs);
		}
		return fechaTicket;
	}

	/**
	 * El método <b>actualizarTicket()</b> se encarga de realizar tres acciones en una base de datos relacionadas con la actualización de un ticket concreto.
	 * En primer lugar, actualiza los datos FECHATICKET y PRECIOTOTAL del ticket en la tabla TICKETS. A continuación, elimina todos los artículos asociados a 
	 * dicho ticket en la tabla PERTENENCIAS. Por último, inserta los artículos actualizados del ticket en la tabla PERTENENCIAS.
	 * 
	 * @param idTicket Parámetro de tipo {@code String}. Se corresponde con el identificador del ticket cuyos datos y artículos se desean actualizar.
	 * @param fecha Parámetro de tipo {@code String}. Se corresponde con la fecha actualizada del ticket que se desea editar.
	 * @param precioTotal Parámetro de tipo {@code String}. Se corresponde con el valor actualizado de la suma de los precios de todos los artículos del ticket que se desea actualizar.
	 * @param tabla Parámetro de tipo {@code JTable}. Contiene la tabla con los artículos actualizados del ticket.
	 */
	public static void actualizarTicket(String idTicket, String fecha, String precioTotal, JTable tabla) {
		Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conexion = GestorConexiones.getMySQL_Connection("tiendecitajpe");
			String ins = "UPDATE TICKETS SET FECHATICKET = ?, TOTALTICKET = ? WHERE IDTICKET = ?";
			pst = conexion.prepareStatement(ins);
			String fechaSql = Modelo.europeoMysql(fecha);
			pst.setString(1, fechaSql);
			pst.setString(2, precioTotal);
			pst.setString(3, idTicket);

			pst.executeUpdate();
			pst.clearParameters();

			String ins2 = "DELETE FROM PERTENENCIAS WHERE IDTICKETFK = ?";
			pst = conexion.prepareStatement(ins2);
			pst.setString(1, idTicket);

			pst.executeUpdate();
			pst.clearParameters();

			String[] articulosdelTicket = Modelo.obtenerValoresColumna(tabla, 0);
			String[] cantidadesArticulosdelTicket = Modelo.obtenerValoresColumna(tabla, 3);

			for (int i = 0; i < articulosdelTicket.length; i++) {
				String ins3 = "INSERT INTO PERTENENCIAS (CANTIDADARTICULOTICKET,IDARTICULOFK,IDTICKETFK) "
						+ "VALUES (?,?,?)";
				pst = conexion.prepareStatement(ins3);

				pst.setInt(1, Integer.parseInt(cantidadesArticulosdelTicket[i]));
				pst.setInt(2, Integer.parseInt(articulosdelTicket[i]));
				pst.setInt(3, Integer.parseInt(idTicket));

				pst.executeUpdate();
				pst.clearParameters();
			}
		} catch (SQLException sqle) {

			System.err.println("Error de SQL " + sqle.getMessage());

		} finally {
			GestorConexiones.cerrarRecursosPst(conexion, pst);
		}

	}
}
