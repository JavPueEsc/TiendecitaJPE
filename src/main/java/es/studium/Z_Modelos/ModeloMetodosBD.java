package es.studium.Z_Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ModeloMetodosBD {

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
	            System.out.println("El articulo ha sido dado de alta con el id: "+idArticulo);
	        }
		    else {
		    	System.err.println("No se ha dado de alta");
		    }
		    pst.clearParameters();
		}
		catch (SQLException sqle) {

			System.out.println("Error de SQL " + sqle.getMessage());
		}
		finally {
			GestorConexiones.cerrarRecursosPst(conexion, pst, rs);
		}
	}
	
	public static DefaultTableModel mostrarArticulosEnTabla(String[] nombreColumnas) {
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		Object[] datosTabla = new Object[4];
		DefaultTableModel modeloTabla = new DefaultTableModel(nombreColumnas, 0){
	        // Se sobrescribe el método isCellEditable para que siempre devuelva false
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // No se puede editar ninguna celda
	        }
	    };
		
		try {
			conexion = GestorConexiones.getMySQL_Connection("tiendecitajpe");
			st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String consulta ="SELECT IDARTICULO, DESCRIPCIONARTICULO, PRECIOARTICULO, CANTIDADARTICULO FROM ARTICULOS";
			rs = st.executeQuery(consulta);
			
			while(rs.next()) {
				for(int i=0;i<4;i++) {
					datosTabla[i] = rs.getString(i+1);
					//System.out.println(datosTabla[i]);//
				}
				//System.out.println(".");
				modeloTabla.addRow(datosTabla);
			}
		}
		catch (SQLException e) {
			System.out.println("Error en la sentencia SQL");
		}
		finally {
			GestorConexiones.cerrarRecursosSt(conexion, st, rs);
		}
		
		return modeloTabla;
	}
	
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
		}
		catch (SQLException sqle) {

			System.err.println("Error de SQL " + sqle.getMessage());
			
		}
		finally {
			GestorConexiones.cerrarRecursosPst(conexion, pst);
		}

	}
	
	public static void actualizarArticulo(String idArticulo,String descripcion, String precio, String cantidad) {
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
		}
		catch (SQLException sqle) {

			System.err.println("Error de SQL " + sqle.getMessage());
			
		}
		finally {
			GestorConexiones.cerrarRecursosPst(conexion, pst);
		}
	}
	
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
		    
		    for(int i=0; i<articulosdelTicket.length;i++) {
		    	String ins2 = "INSERT INTO PERTENENCIAS (CANTIDADARTICULOTICKET,IDARTICULOFK,IDTICKETFK) VALUES (?,?,?)";
		    	pst = conexion.prepareStatement(ins2);
		    	
		    	pst.setInt(1, Integer.parseInt(cantidadesArticulosdelTicket[i]));
		    	pst.setInt(2, Integer.parseInt(articulosdelTicket[i]));
		    	pst.setInt(3, idTicket);
		    	
		    	pst.executeUpdate();
				pst.clearParameters();
		    }
		    
		}
		catch (SQLException sqle) {

			System.out.println("Error de SQL " + sqle.getMessage());
		}
		finally {
			GestorConexiones.cerrarRecursosPst(conexion, pst, rs);
		}
		System.err.println("id asignado al ticket"+idTicket);
	}
	
	public static DefaultTableModel mostrarTicketEnTabla(String[] nombreColumnas) {
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		
		DefaultTableModel modeloTabla = new DefaultTableModel(nombreColumnas, 0){
	        // Se sobrescribe el método isCellEditable para que siempre devuelva false
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // No se puede editar ninguna celda
	        }
	    };
	    try {
	    	conexion = GestorConexiones.getMySQL_Connection("tiendecitajpe");
	    	st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    	String consulta ="SELECT IDTICKET, FECHATICKET, TOTALTICKET FROM TICKETS";
	    	rs = st.executeQuery(consulta);
	    	
	    	while(rs.next()) {
	    		Object[] datosTabla = new Object[3];
				datosTabla[0] = rs.getInt("IDTICKET");
				datosTabla[1] = Modelo.mysqlEuropeo(rs.getDate("FECHATICKET")+"");
				datosTabla[2] = rs.getFloat("TOTALTICKET");
				modeloTabla.addRow(datosTabla);
			}

	    }
	    catch (SQLException e) {
			System.out.println("Error en la sentencia SQL");
		}
		finally {
			GestorConexiones.cerrarRecursosSt(conexion, st, rs);
		}
		
		return modeloTabla;
	}
	
	public static DefaultTableModel mostrarArticulosTicketEnTabla (String idTicket, String[] nombreColumnas) {
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		
		DefaultTableModel modeloTabla = new DefaultTableModel(nombreColumnas, 0){
	        // Se sobrescribe el método isCellEditable para que siempre devuelva false
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // No se puede editar ninguna celda
	        }
	    };
	    try {
	    	conexion = GestorConexiones.getMySQL_Connection("tiendecitajpe");
	    	st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    	String consulta ="SELECT P.IDPERTENENCIA, P.IDARTICULOFK, P.CANTIDADARTICULOTICKET, P.IDTICKETFK, "
	    			+ "A.DESCRIPCIONARTICULO, A.PRECIOARTICULO, A.CANTIDADARTICULO "
	    			+ "FROM PERTENENCIAS P JOIN ARTICULOS A ON P.IDARTICULOFK = A.IDARTICULO WHERE P.IDTICKETFK ="+idTicket+"";
	    	rs = st.executeQuery(consulta);
	    	
	    	while(rs.next()) {
	    		Object[] datosTabla = new Object[4];
				datosTabla[0] = rs.getString("A.DESCRIPCIONARTICULO");
				datosTabla[1] = rs.getFloat("A.PRECIOARTICULO");
				datosTabla[2] = rs.getInt("P.CANTIDADARTICULOTICKET");
				datosTabla[3] = rs.getFloat("A.PRECIOARTICULO") * (float) rs.getInt("P.CANTIDADARTICULOTICKET");
				modeloTabla.addRow(datosTabla);
			}	
	}
	    catch (SQLException e) {
			System.out.println("Error en la sentencia SQL");
		}
		finally {
			GestorConexiones.cerrarRecursosSt(conexion, st, rs);
		}
		
		return modeloTabla;
	}
	
	public static void eliminarTicket (String idTicket) {
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
		}
		catch (SQLException sqle) {

			System.err.println("Error de SQL " + sqle.getMessage());
			
		}
		finally {
			GestorConexiones.cerrarRecursosPst(conexion, pst);
		}

	}
}
