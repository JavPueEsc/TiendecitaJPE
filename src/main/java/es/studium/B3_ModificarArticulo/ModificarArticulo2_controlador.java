package es.studium.B3_ModificarArticulo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JOptionPane;

import es.studium.A_MenuPrincipal.MenuPrincipal_controlador;
import es.studium.A_MenuPrincipal.MenuPrincipal_vista;
import es.studium.Z_Modelos.Modelo;

public class ModificarArticulo2_controlador implements ActionListener {

	ModificarArticulo2_vista vista;
	Modelo modelo;
	String[] contenido;
	
	public ModificarArticulo2_controlador(ModificarArticulo2_vista v, Modelo m, String[] c) {
		vista = v;
		modelo = m;
		contenido = c;
		
		v.addWindowListener(new WindowAdapter() {
			@Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
				new ModificarArticulo1_controlador(new ModificarArticulo1_vista(), new Modelo());
				vista.setVisible(false);
				}
			}
		);
		v.txtDescripcion.setText(c[0]);
		v.txtPrecio.setText(c[1]);
		v.txtCantidad.setText(c[2]);
		
		v.btnModificarArticulo.addActionListener(this);
		v.btnLimpiar.addActionListener(this);
		v.btnVolver.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(vista.btnLimpiar)) {
			modelo.limpiarCampos(vista.txtDescripcion, vista.txtPrecio, vista.txtCantidad);
		}
		
		if(e.getSource().equals(vista.btnVolver)) {
			new ModificarArticulo1_controlador(new ModificarArticulo1_vista(), new Modelo());
			vista.setVisible(false);
		}
		
		if(e.getSource().equals(vista.btnModificarArticulo)) {
			if(vista.txtDescripcion.getText().isEmpty()) {
				JOptionPane.showMessageDialog(vista.contentPane, "El campo 'Descripción' es obligatorio.");
			}
			else if(vista.txtPrecio.getText().isEmpty()) {
				JOptionPane.showMessageDialog(vista.contentPane, "El campo 'Precio (€)' es obligatorio.");
			}
			else if(vista.txtCantidad.getText().isEmpty()) {
				JOptionPane.showMessageDialog(vista.contentPane, "El campo 'Cantidad' es obligatorio.");
			}
			else {
				JOptionPane.showMessageDialog(vista.contentPane, "El artículo se ha modificado correctamente.");
				new ModificarArticulo1_controlador(new ModificarArticulo1_vista(), new Modelo());
				vista.setVisible(false);
			}
		}
		
	}

}
