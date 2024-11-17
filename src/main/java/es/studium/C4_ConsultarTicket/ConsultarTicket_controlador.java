package es.studium.C4_ConsultarTicket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import es.studium.A_MenuPrincipal.MenuPrincipal_controlador;
import es.studium.A_MenuPrincipal.MenuPrincipal_vista;
import es.studium.Z_Modelos.Modelo;
import es.studium.Z_Modelos.ModeloMetodosBD;

public class ConsultarTicket_controlador implements ActionListener {

	ConsultarTicket_vista vista;
	Modelo modelo;
	String idTicketGestionado;
	int filaSeleccionada;
	
	public ConsultarTicket_controlador(ConsultarTicket_vista v, Modelo m) {
		vista = v;
		modelo = m;

		v.tablaSeleccion.setModel(ModeloMetodosBD.mostrarTicketEnTabla(v.nombreColumnasSeleccion));
		
		v.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				new MenuPrincipal_controlador(new MenuPrincipal_vista(), new Modelo());
				vista.setVisible(false);
			}
		});

		v.tablaSeleccion.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1) {
					filaSeleccionada = v.tablaSeleccion.rowAtPoint(e.getPoint());
					idTicketGestionado = Modelo.obtenerValorIdTicketSeleccionado(v.tablaSeleccion);
					v.tablaTicket.setModel(ModeloMetodosBD.mostrarArticulosTicketEnTabla(idTicketGestionado, v.nombreColumnasTicket));
					filaSeleccionada = v.tablaSeleccion.rowAtPoint(e.getPoint());
				}
			}
		});

		v.btnVolver.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource().equals(vista.btnVolver)) {
			new MenuPrincipal_controlador(new MenuPrincipal_vista(), new Modelo());
			vista.setVisible(false);
		}

	}
	
}
