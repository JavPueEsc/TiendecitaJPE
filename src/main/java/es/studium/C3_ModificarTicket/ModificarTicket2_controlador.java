package es.studium.C3_ModificarTicket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import es.studium.A_MenuPrincipal.MenuPrincipal_controlador;
import es.studium.A_MenuPrincipal.MenuPrincipal_vista;
import es.studium.Z_Modelos.Modelo;

public class ModificarTicket2_controlador implements ActionListener {

	public ModificarTicket2_vista vista;
	public Modelo modelo;
	
	public String[] ticket1 = {"1","20/10/2024","8.40","Manzana","0.20","2","0.40","Detergente","2.00","2","8.00"};
	public String[] ticket2 = {"2","21/10/2024","9.50","Pastel","1.00","5","5.00","Pizza","1.50","3","4.50"};
	public String[] ticket3 = {"3","22/10/2024","7.90","Pizza","1.50","3","4.50","Manzana","0.20","7","1.40","Manzana","0.20","10","1.40"};
	public String[] ticket4 = {"4","23/10/2024","45.00","Detergente","2.00","20","40.00","Pastel","1.00","5","5.00"};
	
	String [][] tickets = {ticket1,ticket2,ticket3,ticket4};
	
	public DefaultTableModel modeloTablaTicket;
	String numTicketSeleccionado;
	String valorFechaSeleccionada;
	
	public ModificarTicket2_controlador(ModificarTicket2_vista v, Modelo m, DefaultTableModel mt, String numTicket, String valorFecha) {
		vista = v;
		modelo = m;
		modeloTablaTicket = mt;
		numTicketSeleccionado = numTicket;
		valorFechaSeleccionada = valorFecha;
		
		v.addWindowListener(new WindowAdapter() {
			@Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
				new ModificarTicket1_controlador(new ModificarTicket1_vista(), new Modelo(), tickets);
				vista.setVisible(false);
				}
			}
		);
		
		v.btnModificarTicket.addActionListener(this);
		v.btnLimpiar.addActionListener(this);
		v.btnVolver.addActionListener(this);
		
		v.btnAddArticulo.addActionListener(this);
		v.btnQuitar.addActionListener(this);
		
		v.lblnumTicket.setText(numTicket);
		v.txtFecha.setText(valorFecha);
		
		int totalFilas = mt.getRowCount();
		
		for (int i = 0; i < totalFilas; i++) {
		    // Crear un array para almacenar los datos de la fila
		    Object[] filaDatos = new Object[mt.getColumnCount()];

		    // Obtener los datos de la fila en el modelo origen
		    for (int j = 0; j < mt.getColumnCount(); j++) {
		        filaDatos[j] = mt.getValueAt(i, j);
		    }
		    v.modeloTablaTicket.addRow(filaDatos);
		}
		
		m.establecerTotal( vista,  v.modeloTablaTicket,  v.txtTotal);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(vista.btnAddArticulo)) {
			
			int filaSeleccionada = vista.tablaArticulos.getSelectedRow();
			
			modelo.addArticulo2(vista, filaSeleccionada,vista.tablaArticulos, vista.modeloTablaTicket, vista.txtTotal);
		}
		
		if(e.getSource().equals(vista.btnQuitar)) {
			int filaSeleccionada = vista.tablaTickets.getSelectedRow();
			
			modelo.borrarArticulo2(vista, filaSeleccionada, vista.modeloTablaTicket, vista.txtTotal);
		}
		
		if(e.getSource().equals(vista.btnLimpiar)) {
			modelo.limpiarCamposYTablas(vista.modeloTablaTicket, vista.txtTotal, vista.txtFecha);
		}
		
		if(e.getSource().equals(vista.btnVolver)) {
			new ModificarTicket1_controlador(new ModificarTicket1_vista(), new Modelo(), tickets);
			vista.setVisible(false);
		}
		
		if(e.getSource().equals(vista.btnModificarTicket)) {
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
				JOptionPane.showMessageDialog(vista.contentPane, "El ticket se ha modificado correctamente.");
				//modelo.limpiarCamposYTablas(vista.modeloTablaTicket, vista.txtTotal, vista.txtFecha);
			}
		}
		
	}

}
