package es.studium.C3_ModificarTicket;

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
 * La clase <b>ModificarTicket1_controlador</b> gestiona la interacción entre la clase
 * <b>ModificarTicket1_vista</b> y la clase <b>Modelo</b>. Permite manejar los eventos que suceden
 * en la ventana 'Modificar Tickets - Selección'.
 * @author Javier Pueyo
 * @version 2.0
 */
public class ModificarTicket1_controlador implements ActionListener {

	ModificarTicket1_vista vista;
	Modelo modelo;
	String idTicketGestionado;
	int filaSeleccionada;
	
	public ModificarTicket1_controlador(ModificarTicket1_vista v, Modelo m) {
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
					
					new ModificarTicket2_controlador (new ModificarTicket2_vista()
							, new Modelo(),idTicketGestionado);
					v.setVisible(false);
				}
			}
		});

		v.btnVolver.addActionListener(this);
		v.btnModificar.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource().equals(vista.btnVolver)) {
			new MenuPrincipal_controlador(new MenuPrincipal_vista(), new Modelo());
			vista.setVisible(false);
		}

		if (evento.getSource().equals(vista.btnModificar)) {
			
			int filaSeleccionada = vista.tablaSeleccion.getSelectedRow();
			
			if(filaSeleccionada == -1) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un ticket.");
			}
			else {
				new ModificarTicket2_controlador (new ModificarTicket2_vista(), new Modelo()
						,idTicketGestionado);
				vista.setVisible(false);			}
		}
	}
	
}
