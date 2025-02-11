package es.studium.A_MenuPrincipal;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;
import javax.swing.JOptionPane;
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
import es.studium.Z_Modelos.GestorConexiones;
import es.studium.Z_Modelos.Modelo;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * La clase <b>MenuPrincipal_controlador</b> gestiona la interacción entre la clase
 * <b>MenuPrincipal_vista</b> y la clase <b>Modelo</b>. Permite manejar los eventos que suceden
 * en el menú principal de la aplicación, permitiendo la navegación entre las diferentes funcionalidades
 * como la gestión de artículos, la gestión de tickets o la gestión de informes.
 * @author Javier Pueyo
 * @version 2.0
 */
public class MenuPrincipal_controlador implements ActionListener{
	
	MenuPrincipal_vista vista;
	Modelo modelo;
	LocalDate fechaActual = LocalDate.now();
	String fechaGeneracion = fechaActual.toString();
	
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
		
		//Añadir Listener a los nuevos menú items
		v.mniInformeArticulos.addActionListener(this);
		v.mniInformeTickets.addActionListener(this);
		v.btnDlgAceptar.addActionListener(this);
		
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
		
		//Gestión de eventos en los nuevos Menú Items "Informe artículo" e "Informe Tickets"
		
		//1. Generar Informe sobre Artículos
		
		if(e.getSource().equals(vista.mniInformeArticulos)) {
			try {
				// Compilar el informe generando fichero .jasper
					JasperCompileManager.compileReportToFile("./src/main/resources/es/studium/jasper/ArticulosTiendecitajpe.jrxml");
					
				// Objeto para guardar parámetros necesarios para el informe
							HashMap<String, Object> parametros = new HashMap<String, Object>();
							parametros.put("fecha_generacion", Modelo.mysqlEuropeo(fechaGeneracion));
				// Cargar el informe compilado
							JasperReport report = (JasperReport) JRLoader
									.loadObjectFromFile("./src/main/resources/es/studium/jasper/ArticulosTiendecitajpe.jasper");

				// Conectar a la base de datos para sacar la información
							Connection conexion = GestorConexiones.getMySQL_Connection("tiendecitajpe");
							
				// Completar el informe con los datos de la base de datos
							JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion);

				// Mostrar el informe en JasperViewer
							JasperViewer.viewReport(print, false);

				// Para exportarlo a pdf
							JasperExportManager.exportReportToPdfFile(print,
									"./src/main/resources/es/studium/jasper/ArticulosTiendecitajpe.pdf");

				//Crea el informe en PDF
							File path = new File("./src/main/resources/es/studium/jasper/ArticulosTiendecitajpe.pdf");

				// Abrir el fichero PDF generado
							Desktop.getDesktop().open(path);
							System.out.println("Fichero ArticulosTiendecitajpe.pdf generado CORRECTAMENTE!");

						} catch (Exception ex1) {

							System.out.println("Error: " + e.toString());

						}
		}
		
		//2. Generar Informe sobre tickets
		
		if(e.getSource().equals(vista.mniInformeTickets)) {
			
			vista.dlgTickets.setVisible(true);
					
		}
		
		if(e.getSource().equals(vista.btnDlgAceptar)){
			boolean fechaDesdeValida = Modelo.esFechaValida(vista.txtFechaDesde.getText());
			boolean fechaHastaValida = Modelo.esFechaValida(vista.txtFechaHasta.getText());
			
			if(vista.txtFechaDesde.getText().isEmpty()) {
				JOptionPane.showMessageDialog(
					    vista,
					    "El campo 'Fecha desde:' no puede estar vacío.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE
					);
			}
			
			else if (vista.txtFechaHasta.getText().isEmpty()) {
				JOptionPane.showMessageDialog(
					    vista,
					    "El campo 'Fecha Hasta:' no puede estar vacío.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE
					);
			}
			
			else if(!fechaDesdeValida) {
				JOptionPane.showMessageDialog(
					    vista,
					    "La fecha del campo 'Fecha Desde:' debe tener el formato dd/mm/aaaa.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE
					);
			}
			
			else if(!fechaHastaValida) {
				JOptionPane.showMessageDialog(
					    vista,
					    "La fecha del campo 'Fecha Hasta:' debe tener el formato dd/mm/aaaa.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE
					);
			}
			
			else {
				try {
					// Compilar el informe generando fichero .jasper
						JasperCompileManager.compileReportToFile("./src/main/resources/es/studium/jasper/"
								+ "TicketsTiendecitajpe.jrxml");
						
					// Objeto para guardar parámetros necesarios para el informe
								HashMap<String, Object> parametros = new HashMap<String, Object>();
								parametros.put("fecha_generacion", Modelo.mysqlEuropeo(fechaGeneracion));
								parametros.put("fechaInicio", vista.txtFechaDesde.getText());
								parametros.put("fechaFin", vista.txtFechaHasta.getText());
								
								System.out.println(Modelo.europeoMysql(vista.txtFechaDesde.getText()));
								System.out.println(Modelo.europeoMysql(vista.txtFechaHasta.getText()));
					// Cargar el informe compilado
								JasperReport report = (JasperReport) JRLoader
										.loadObjectFromFile("./src/main/resources/es/studium/jasper/"
												+ "TicketsTiendecitajpe.jasper");

					// Conectar a la base de datos para sacar la información
								Connection conexion2 = GestorConexiones.getMySQL_Connection("tiendecitajpe");
								
					// Completar el informe con los datos de la base de datos
								JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion2);

					// Mostrar el informe en JasperViewer
								JasperViewer.viewReport(print, false);

					// Para exportarlo a pdf
								JasperExportManager.exportReportToPdfFile(print,
										"./src/main/resources/es/studium/jasper/TicketsTiendecitajpe.pdf");

					//Crea el informe en PDF
								File path = new File("./src/main/resources/es/studium/jasper/TicketsTiendecitajpe.pdf");

					// Abrir el fichero PDF generado
								Desktop.getDesktop().open(path);
								System.out.println("Fichero TicketsTiendecitajpe.pdf generado CORRECTAMENTE!");
					// cerrar cuadro de diálogo
								
								vista.dlgTickets.setVisible(false);

							} catch (Exception ex2) {

								System.out.println("Error: " + e.toString());

							}
			}
		
		if(e.getSource().equals(vista.mnuiSalir)) {
			System.exit(0);
		}
	}
	}
}
