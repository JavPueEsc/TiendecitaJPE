package es.studium.B2_EliminarArticulo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JOptionPane;
import es.studium.A_MenuPrincipal.MenuPrincipal_controlador;
import es.studium.A_MenuPrincipal.MenuPrincipal_vista;
import es.studium.Z_Modelos.Modelo;
import es.studium.Z_Modelos.ModeloMetodosBD;

public class EliminarArticulo_controlador implements ActionListener {

	EliminarArticulo_vista vista;
	Modelo modelo;

	public EliminarArticulo_controlador(EliminarArticulo_vista v, Modelo m) {
		vista = v;
		modelo = m;
		
		v.tabla.setModel(ModeloMetodosBD.mostrarArticulosEnTabla(v.nombreColumnas));

		v.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				new MenuPrincipal_controlador(new MenuPrincipal_vista(), new Modelo());
				vista.setVisible(false);
			}
		});

		v.tabla.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					
					int filaSeleccionada = v.tabla.rowAtPoint(e.getPoint());
					
					m.gestionBorradoArticulo(filaSeleccionada,"artículo", v.tabla);
					v.tabla.setModel(ModeloMetodosBD.mostrarArticulosEnTabla(v.nombreColumnas));
					
				}
			}
		});

		v.btnVolver.addActionListener(this);
		v.btnSeleccionar.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource().equals(vista.btnVolver)) {
			new MenuPrincipal_controlador(new MenuPrincipal_vista(), new Modelo());
			vista.setVisible(false);
		}

		if (evento.getSource().equals(vista.btnSeleccionar)) {
			
			int filaSeleccionada = vista.tabla.getSelectedRow();
			
			if(filaSeleccionada == -1) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un artículo.");
			}
			else {
				modelo.gestionBorradoArticulo(filaSeleccionada,"artículo", vista.tabla);
				vista.tabla.setModel(ModeloMetodosBD.mostrarArticulosEnTabla(vista.nombreColumnas));
			}
		}
	}
}
