package es.studium.B4_ConsultarArticulo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JOptionPane;

import es.studium.A_MenuPrincipal.MenuPrincipal_controlador;
import es.studium.A_MenuPrincipal.MenuPrincipal_vista;
import es.studium.Z_Modelos.Modelo;

public class ConsultarArticulo2_controlador implements ActionListener {

	ConsultarArticulo2_vista vista;
	Modelo modelo;
	String[] contenido;
	
	public ConsultarArticulo2_controlador(ConsultarArticulo2_vista v, Modelo m, String[] c) {
		vista = v;
		modelo = m;
		contenido = c;
		
		v.addWindowListener(new WindowAdapter() {
			@Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
				new ConsultarArticulo1_controlador(new ConsultarArticulo1_vista(), new Modelo());
				vista.setVisible(false);
				}
			}
		);
		v.btnVolver.requestFocusInWindow();
		
		v.txtDescripcion.setEditable(false);
		v.txtPrecio.setEditable(false);
		v.txtCantidad.setEditable(false);
		
		v.txtDescripcion.setText(c[0]);
		v.txtPrecio.setText(c[1]);
		v.txtCantidad.setText(c[2]);
		
		v.btnVolver.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(vista.btnVolver)) {
			new ConsultarArticulo1_controlador(new ConsultarArticulo1_vista(), new Modelo());
			vista.setVisible(false);
		}
		
	}

}
