package es.studium.B4_ConsultarArticulo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import es.studium.Z_Modelos.Modelo;

/**
 * La clase <b>ConsultarArticulo2_controlador</b> gestiona la interacción entre la clase
 * <b>ConsultarArticulo2_vista</b> y la clase <b>Modelo</b>. Permite manejar los eventos que suceden
 * en la ventana 'Consultar Artículo'.
 * @author Javier Pueyo
 * @version 2.0
 */
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
		
		v.txtDescripcion.setText(c[1]);
		v.txtPrecio.setText(c[2]);
		v.txtCantidad.setText(c[3]);
		
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
