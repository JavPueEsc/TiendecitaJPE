package es.studium.A_MenuPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import es.studium.B1_AltaArticulo.AltaArticulo_controlador;
import es.studium.B1_AltaArticulo.AltaArticulo_vista;
import es.studium.B2_EliminarArticulo.EliminarArticulo_controlador;
import es.studium.B2_EliminarArticulo.EliminarArticulo_vista;
import es.studium.B3_ModificarArticulo.ModificarArticulo1_controlador;
import es.studium.B3_ModificarArticulo.ModificarArticulo1_vista;
import es.studium.B4_ConsultarArticulo.ConsultarArticulo1_controlador;
import es.studium.B4_ConsultarArticulo.ConsultarArticulo1_vista;
import es.studium.C1_AltaTicket.AltaTicket_controlador;
import es.studium.C1_AltaTicket.AltaTicket_vista;
import es.studium.C2_EliminarTicket.EliminarTicket_controlador;
import es.studium.C2_EliminarTicket.EliminarTicket_vista;
import es.studium.C3_ModificarTicket.ModificarTicket1_controlador;
import es.studium.C3_ModificarTicket.ModificarTicket1_vista;
import es.studium.C4_ConsultarTicket.ConsultarTicket_controlador;
import es.studium.C4_ConsultarTicket.ConsultarTicket_vista;
import es.studium.Z_Modelos.Modelo;

public class MenuPrincipal_controlador implements ActionListener{
	
	MenuPrincipal_vista vista;
	Modelo modelo;
	
	public MenuPrincipal_controlador(MenuPrincipal_vista v, Modelo m) {
		vista = v;
		modelo = m;
		
		
		//añadir Listeners
		v.mnuiCrearArticulo.addActionListener(this);
		v.mnuiEliminarArticulo.addActionListener(this);
		v.mnuiModificarArticulo.addActionListener(this);
		v.mnuiConsultarArticulo.addActionListener(this);
		
		v.mnuiCrearTicket.addActionListener(this);
		v.mnuiEliminarTicket.addActionListener(this);
		v.mnuiModificarTicket.addActionListener(this);
		v.mnuiConsultarTicket.addActionListener(this);
		
		v.mnuiSalir.addActionListener(this);
		
		// Colocar imagen de fondo
        int anchoOriginal = v.imagenFondo.getIconWidth();
        int altoOriginal = v.imagenFondo.getIconHeight();
        
        // Calcular el 10% del tamaño original
        int nuevoAncho = (int) (anchoOriginal * 0.1);
        int nuevoAlto = (int) (altoOriginal * 0.1);
        
        // Ajustar la imagen al 90% de su tamaño original
        v.lblImagen.setIcon(modelo.escalarImagen(v.imagenFondo, nuevoAncho, nuevoAlto));

        // Colocar el JLabel en el centro del panel
        v.lblImagen.setBounds(0, -5, nuevoAncho, nuevoAlto);
        
        // Añadir el JLabel al panel
        v.contentPane.add(v.lblImagen);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(vista.mnuiCrearArticulo)) {
			new AltaArticulo_controlador(new AltaArticulo_vista(), new Modelo());
			vista.setVisible(false);
		}
		
		if(e.getSource().equals(vista.mnuiEliminarArticulo)) {
			new EliminarArticulo_controlador(new EliminarArticulo_vista(), new Modelo());
			vista.setVisible(false);
		}
		
		if(e.getSource().equals(vista.mnuiModificarArticulo)) {
			new ModificarArticulo1_controlador(new ModificarArticulo1_vista(), new Modelo());
			vista.setVisible(false);
		}
		
		if(e.getSource().equals(vista.mnuiConsultarArticulo)) {
			new ConsultarArticulo1_controlador(new ConsultarArticulo1_vista(), new Modelo());
			vista.setVisible(false);
		}
		
		if(e.getSource().equals(vista.mnuiCrearTicket)) {
			new AltaTicket_controlador(new AltaTicket_vista(), new Modelo());
			vista.setVisible(false);
		}
		
		if(e.getSource().equals(vista.mnuiEliminarTicket)) {
			new EliminarTicket_controlador(new EliminarTicket_vista(), new Modelo());
			vista.setVisible(false);
		}
		
		if(e.getSource().equals(vista.mnuiModificarTicket)) {
			new ModificarTicket1_controlador(new ModificarTicket1_vista(), new Modelo());
			vista.setVisible(false);
		}
		if(e.getSource().equals(vista.mnuiConsultarTicket)) {
			new ConsultarTicket_controlador(new ConsultarTicket_vista(), new Modelo());
			vista.setVisible(false);
		}
		
		if(e.getSource().equals(vista.mnuiSalir)) {
			System.exit(0);
		}
	}

}
