package es.studium.C3_ModificarTicket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import es.studium.Z_Modelos.Modelo;
import es.studium.Z_Modelos.ModeloMetodosBD;

/**
 * La clase <b>ModificarTicket2_controlador</b> gestiona la interacción entre la clase
 * <b>ModificarTicket2_vista</b> y la clase <b>Modelo</b>. Permite manejar los eventos que suceden
 * en la ventana 'Modificar Ticket'.
 * @author Javier Pueyo
 * @version 2.0
 */
public class ModificarTicket2_controlador implements ActionListener {

	public ModificarTicket2_vista vista;
	public Modelo modelo;
	public String idTicketGestionado;
	public DefaultTableModel modeloActualizarTickets;
	public String fechaTicket;
	
	public ModificarTicket2_controlador(ModificarTicket2_vista v, Modelo m
			, String idTicketGestionado) {
		vista = v;
		modelo = m;
		this.idTicketGestionado = idTicketGestionado;
		vista.tablaTickets = v.tablaTickets;
		
		v.tablaArticulos.setModel(ModeloMetodosBD.mostrarArticulosEnTabla(v.nombreColumnas));
		modeloActualizarTickets = 
				ModeloMetodosBD.mostrarArticulosTicketEnTablaParaActualizar(idTicketGestionado
						, v.nombreColumnasTicket);
		v.tablaTickets.setModel(modeloActualizarTickets);
		m.ajustarAnchoColumnas(v.tablaArticulos);
		m.ajustarAnchoColumnas(v.tablaTickets);
		
		v.addWindowListener(new WindowAdapter() {
			@Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
				new ModificarTicket1_controlador(new ModificarTicket1_vista(), new Modelo());
				vista.setVisible(false);
				}
			}
		);
		
		v.btnModificarTicket.addActionListener(this);
		v.btnLimpiar.addActionListener(this);
		v.btnVolver.addActionListener(this);
		
		v.btnAddArticulo.addActionListener(this);
		v.btnQuitar.addActionListener(this);
		
		v.lblnumTicket.setText(idTicketGestionado);
		
		fechaTicket = ModeloMetodosBD.obtenerFechaTicket(idTicketGestionado);
		v.txtFecha.setText(fechaTicket);
		
		m.establecerTotal( vista,  modeloActualizarTickets,  v.txtTotal);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(vista.btnAddArticulo)) {
			
			int filaSeleccionada = vista.tablaArticulos.getSelectedRow();
			
			 modelo.addArticuloActualizarTicket(vista, filaSeleccionada, vista.tablaArticulos
					 , modeloActualizarTickets, vista.txtTotal);
			
		}
		
		if(e.getSource().equals(vista.btnQuitar)) {
			int filaSeleccionada = vista.tablaTickets.getSelectedRow();
			
			modelo.borrarArticulo2(vista, filaSeleccionada, modeloActualizarTickets
					, vista.txtTotal);
		}
		
		if(e.getSource().equals(vista.btnLimpiar)) {
			modelo.limpiarCamposYTablas(modeloActualizarTickets, vista.txtTotal
					, vista.txtFecha);
		}
		
		if(e.getSource().equals(vista.btnVolver)) {
			new ModificarTicket1_controlador(new ModificarTicket1_vista(), new Modelo());
			vista.setVisible(false);
		}
		
		if(e.getSource().equals(vista.btnModificarTicket)) {
			boolean fechaCorrecta = modelo.esFechaValida(vista.txtFecha.getText());
			if(vista.txtFecha.getText().isEmpty()) {
				JOptionPane.showMessageDialog(vista.contentPane, "El campo 'Fecha del ticket' "
						+ "es obligatorio.");
			}
			else if(modeloActualizarTickets.getRowCount()==0) {
				JOptionPane.showMessageDialog(vista.contentPane, "El ticket debe incluir "
						+ "al menos un artículo.");
			}
			else if(!fechaCorrecta) {
				JOptionPane.showMessageDialog(vista, "La fecha debe tener el formato "
						+ "dd/mm/aaaa");
			}
			else {
				ModeloMetodosBD.actualizarTicket(idTicketGestionado, vista.txtFecha.getText()
						, vista.txtTotal.getText(), vista.tablaTickets);
				JOptionPane.showMessageDialog(vista.contentPane, "El ticket se ha modificado "
						+ "correctamente.");
			}
		}
		
	}

}
