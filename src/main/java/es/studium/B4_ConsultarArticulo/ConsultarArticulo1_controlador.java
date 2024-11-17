package es.studium.B4_ConsultarArticulo;

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

public class ConsultarArticulo1_controlador implements ActionListener {

	ConsultarArticulo1_vista vista;
	Modelo modelo;

	public ConsultarArticulo1_controlador(ConsultarArticulo1_vista v, Modelo m) {
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
					
					gestionModificacion(filaSeleccionada);
					
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
				gestionModificacion(filaSeleccionada);
			}
		}
	}
	
	public void gestionModificacion(int filaSeleccionada) {
		

		if (filaSeleccionada != -1) {
			StringBuilder contenido = new StringBuilder();

			for (int col = 0; col < vista.tabla.getColumnCount(); col++) {
				Object valor = vista.tabla.getValueAt(filaSeleccionada, col);
				contenido.append(valor).append(" "); //
			}

			String cadenaContenido = contenido.toString();
			String[] ArrayContenido = cadenaContenido.split(" ");
			//String articuloAEliminar = ArrayContenido[0];
			
			new ConsultarArticulo2_controlador(new ConsultarArticulo2_vista(), new Modelo(), ArrayContenido);
			vista.setVisible(false);
		}
	}
}
