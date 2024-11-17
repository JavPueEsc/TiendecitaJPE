package es.studium.A_MenuPrincipal;

import java.awt.EventQueue;

import es.studium.Z_Modelos.Modelo;


public class MenuPrincipal_principal {
	
	MenuPrincipal_vista vista = new MenuPrincipal_vista();
	Modelo modelo = new Modelo();
	
	public MenuPrincipal_principal(MenuPrincipal_vista v, Modelo m) {
		vista = v;
		modelo = m;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MenuPrincipal_vista vista = new MenuPrincipal_vista();
					Modelo modelo = new Modelo();
					
					new MenuPrincipal_controlador(vista , modelo);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

