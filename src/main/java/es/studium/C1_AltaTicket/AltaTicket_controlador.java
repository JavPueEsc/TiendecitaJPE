package es.studium.C1_AltaTicket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JOptionPane;

import es.studium.A_MenuPrincipal.MenuPrincipal_controlador;
import es.studium.A_MenuPrincipal.MenuPrincipal_vista;
import es.studium.Z_Modelos.Modelo;

public class AltaTicket_controlador implements ActionListener {

	AltaTicket_vista vista;
	Modelo modelo;
	
	public AltaTicket_controlador(AltaTicket_vista v, Modelo m) {
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
		
		v.btnCrearTicket.addActionListener(this);
		v.btnLimpiar.addActionListener(this);
		v.btnVolver.addActionListener(this);
		
		v.btnAdd.addActionListener(this);
		v.btnQuitar.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(vista.btnAdd)) {
			
			int filaSeleccionada = vista.tablaArticulos.getSelectedRow();
			
			modelo.addArticulo(vista, filaSeleccionada,vista.tablaArticulos, vista.modeloTablaTicket, vista.txtTotal);
		}
		
		if(e.getSource().equals(vista.btnQuitar)) {
			int filaSeleccionada = vista.tablaTickets.getSelectedRow();
			
			modelo.borrarArticulo(vista, filaSeleccionada, vista.modeloTablaTicket, vista.txtTotal);
		}
		
		if(e.getSource().equals(vista.btnLimpiar)) {
			modelo.limpiarCamposYTablas(vista.modeloTablaTicket, vista.txtTotal, vista.txtFecha);
		}
		
		if(e.getSource().equals(vista.btnVolver)) {
			new MenuPrincipal_controlador(new MenuPrincipal_vista() , new Modelo());
			vista.setVisible(false);
		}
		
		if(e.getSource().equals(vista.btnCrearTicket)) {
			boolean fechaCorrecta = modelo.esFechaValida(vista.txtFecha.getText());
			if(vista.txtFecha.getText().isEmpty()) {
				JOptionPane.showMessageDialog(vista.contentPane, "El campo 'Fecha del ticket' es obligatorio.");
			}
			else if(vista.modeloTablaTicket.getRowCount()==0) {
				JOptionPane.showMessageDialog(vista.contentPane, "El ticket debe incluir al menos un artículo.");
			}
			else if(!fechaCorrecta) {
				JOptionPane.showMessageDialog(vista, "La fecha debe tener el formato dd/mm/aaaa");
			}
			else {
				JOptionPane.showMessageDialog(vista.contentPane, "El ticket se ha creado correctamente.");
				modelo.limpiarCamposYTablas(vista.modeloTablaTicket, vista.txtTotal, vista.txtFecha);
			}
		}
		
	}

}
