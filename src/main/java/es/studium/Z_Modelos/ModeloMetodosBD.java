package es.studium.Z_Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
}
