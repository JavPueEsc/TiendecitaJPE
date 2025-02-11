package es.studium.C2_EliminarTicket;

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

/**
 * La clase <b>EliminarTicket_controlador</b> gestiona la interacción entre la clase
 * <b>EliminarTicket_vista</b> y la clase <b>Modelo</b>. Permite manejar los eventos que suceden
 * en la ventana 'Eliminar Tickets'.
 * @author Javier Pueyo
 * @version 2.0
 */
public class EliminarTicket_controlador implements ActionListener {

	EliminarTicket_vista vista;
	Modelo modelo;
	String idTicketGestionado;
	int filaSeleccionada;
	
	public EliminarTicket_controlador(EliminarTicket_vista v, Modelo m) {
		vista = v;
		modelo = m;
		
		
		v.tablaSeleccion.setModel(ModeloMetodosBD.mostrarTicketsEnTabla(v.nombreColumnasSeleccion));

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
					v.tablaTicket.setModel(ModeloMetodosBD.mostrarArticulosTicketEnTabla(idTicketGestionado
							, v.nombreColumnasTicket));
					filaSeleccionada = v.tablaSeleccion.rowAtPoint(e.getPoint());
				}
				
				if (e.getClickCount() == 2) {
					
					filaSeleccionada = v.tablaSeleccion.rowAtPoint(e.getPoint());
					m.gestionBorradoTicket(filaSeleccionada,"ticket", v.tablaSeleccion);
					v.tablaSeleccion.setModel(ModeloMetodosBD.mostrarTicketsEnTabla(v.nombreColumnasSeleccion));
					v.tablaTicket.setModel(ModeloMetodosBD.mostrarArticulosTicketEnTabla("0", v.nombreColumnasTicket));
				}
			}
		});

		v.btnVolver.addActionListener(this);
		v.btnEliminar.addActionListener(this);

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
				modelo.gestionBorradoTicket(filaSeleccionada,"ticket", vista.tablaSeleccion);//<--
				vista.tablaSeleccion.setModel(ModeloMetodosBD.mostrarTicketsEnTabla(vista.nombreColumnasSeleccion));
				vista.tablaTicket.setModel(ModeloMetodosBD.mostrarArticulosTicketEnTabla("0", vista.nombreColumnasTicket));
			}
		}
	}
	
}
