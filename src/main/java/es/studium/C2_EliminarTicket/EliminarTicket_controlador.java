package es.studium.C2_EliminarTicket;

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

public class EliminarTicket_controlador implements ActionListener {

	EliminarTicket_vista vista;
	Modelo modelo;
	
	String[] ticket1 = {"1","20/10/2024","8.40","Manzana","0.20","2","0.40","Detergente","2.00","2","8.00"};
	String[] ticket2 = {"2","21/10/2024","9.50","Pastel","1.00","5","5.00","Pizza","1.50","3","4.50"};
	String[] ticket3 = {"3","22/10/2024","7.90","Pizza","1.50","3","4.50","Manzana","0.20","7","1.40","Manzana","0.20","10","1.40"};
	String[] ticket4 = {"4","23/10/2024","45.00","Detergente","2.00","20","40.00","Pastel","1.00","5","5.00"};
	
	String [][] tickets = {ticket1,ticket2,ticket3,ticket4};

	public EliminarTicket_controlador(EliminarTicket_vista v, Modelo m, String [][] tickets ) {
		vista = v;
		modelo = m;

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
					int filaSeleccionada = v.tablaSeleccion.rowAtPoint(e.getPoint());
					m.mostrarContenidoTicket(filaSeleccionada, v.modeloTablaTicket,tickets[0], tickets[1], tickets[2], tickets[3]);
				}
				
				if (e.getClickCount() == 2) {
					
					int filaSeleccionada = v.tablaSeleccion.rowAtPoint(e.getPoint());
					
					m.gestionBorrado(filaSeleccionada,"ticket", v.tablaSeleccion);
					v.modeloTablaTicket.setRowCount(0);
				}
			}
		});

		v.btnVolver.addActionListener(this);
		v.btnEliminar.addActionListener(this);
		
		m.mostrarTicket(vista.modeloTablaSeleccion,tickets[0], tickets[1], tickets[2], tickets[3]);

	}

	@Override
	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource().equals(vista.btnVolver)) {
			new MenuPrincipal_controlador(new MenuPrincipal_vista(), new Modelo());
			vista.setVisible(false);
		}

		if (evento.getSource().equals(vista.btnEliminar)) {
			
			int filaSeleccionada = vista.tablaSeleccion.getSelectedRow();
			
			if(filaSeleccionada == -1) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un ticket.");
			}
			else {
				modelo.gestionBorrado(filaSeleccionada,"ticket", vista.tablaSeleccion);
				vista.modeloTablaTicket.setRowCount(0);
			}
		}
	}
	
}
