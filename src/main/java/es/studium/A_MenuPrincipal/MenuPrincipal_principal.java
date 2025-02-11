package es.studium.A_MenuPrincipal;

import java.awt.EventQueue;

import es.studium.Z_Modelos.Modelo;

/**
 * La clase <b>MenuPrincipal_principal</b> es la única clase de la aplicación que
 * contiene método Main. Desde esta clase se inicia la ejecución de la aplicación.
 * @author Javier Pueyo
 * @version 2.0
 */
public class MenuPrincipal_principal {
	
	MenuPrincipal_vista vista = new MenuPrincipal_vista();
	Modelo modelo = new Modelo();
	/**
	 * Método
	 * @param v
	 * @param m
	 */
	public MenuPrincipal_principal(MenuPrincipal_vista v, Modelo m) {
		vista = v;
		modelo = m;
	}
	/**
	 * Método Main() de la clase MenuPrincipal_principal
	 * Por este método comienza la ejecución del programa
	 * @param args parámetro del método main()
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

