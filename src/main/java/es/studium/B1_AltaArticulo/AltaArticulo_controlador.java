package es.studium.B1_AltaArticulo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JOptionPane;

import es.studium.A_MenuPrincipal.MenuPrincipal_controlador;
import es.studium.A_MenuPrincipal.MenuPrincipal_vista;
import es.studium.Z_Modelos.Modelo;
import es.studium.Z_Modelos.ModeloMetodosBD;

/**
 * La clase <b>AltaArticulo_controlador</b> gestiona la interacción entre la clase
 * <b>AltaArticulo_vista_vista</b> y la clase <b>Modelo</b>. Permite manejar los eventos que suceden
 * en la ventana 'Crear Artículos'.
 * @author Javier Pueyo
 * @version 2.0
 */
public class AltaArticulo_controlador implements ActionListener {

	AltaArticulo_vista vista;
	Modelo modelo;
	String descripcion;
	float precio;
	int cantidad;
	
	public AltaArticulo_controlador(AltaArticulo_vista v, Modelo m) {
		vista = v;
		modelo = m;
		
		v.addWindowListener(new WindowAdapter() {
			@Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
				new MenuPrincipal_controlador(new MenuPrincipal_vista() , new Modelo());
				vista.setVisible(false);
				}
			}
		);
		
		v.btnCrearArticulo.addActionListener(this);
		v.btnLimpiar.addActionListener(this);
		v.btnVolver.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(vista.btnLimpiar)) {
			modelo.limpiarCampos(vista.txtDescripcion, vista.txtPrecio, vista.txtCantidad);
		}
		
		if(e.getSource().equals(vista.btnVolver)) {
			new MenuPrincipal_controlador(new MenuPrincipal_vista() , new Modelo());
			vista.setVisible(false);
		}
		
		if(e.getSource().equals(vista.btnCrearArticulo)) {
			if(vista.txtDescripcion.getText().isEmpty()) {
				JOptionPane.showMessageDialog(vista.contentPane, "El campo 'Descripción' "
						+ "es obligatorio.");
			}
			else if(vista.txtPrecio.getText().isEmpty()) {
				JOptionPane.showMessageDialog(vista.contentPane, "El campo 'Precio (€)' "
						+ "es obligatorio.");
			}
			else if(vista.txtCantidad.getText().isEmpty()) {
				JOptionPane.showMessageDialog(vista.contentPane, "El campo 'Cantidad' "
						+ "es obligatorio.");
			}
			else {
				descripcion = vista.txtDescripcion.getText();
				try {
					precio = Float.parseFloat(vista.txtPrecio.getText());
				}
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(vista.contentPane, "El valor introducido "
							+ "para el campo 'Precio' debe ser un número decimal.");
		            vista.txtPrecio.setText("");
		        }
				try {
					cantidad = Integer.parseInt(vista.txtCantidad.getText());
				}
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(vista.contentPane, "El valor introducido "
							+ "para el campo 'Cantidad' debe ser un número entero.");
		            vista.txtCantidad.setText("");
		        }
				
				if(!vista.txtPrecio.getText().isEmpty() && !vista.txtCantidad.getText().isEmpty()){
					ModeloMetodosBD.crearArticulo(descripcion, precio, cantidad);
					JOptionPane.showMessageDialog(vista.contentPane, "El artículo "+descripcion+" "
							+ "se ha creado correctamente.");
					modelo.limpiarCampos(vista.txtDescripcion, vista.txtPrecio, vista.txtCantidad);
				}
			}
		}
		
	}

}
