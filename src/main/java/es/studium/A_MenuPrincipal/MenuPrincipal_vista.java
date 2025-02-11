package es.studium.A_MenuPrincipal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Color;

/**
 * La clase <b>MenuPrincipal_vista</b> contiene los componentes gráficos del menú principal
 * de la aplicación.
 * @author Javier Pueyo
 * @version 2.0
 */
public class MenuPrincipal_vista extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	
	public JMenuBar barraMenu;
	public JMenu menuTickets;
	public JMenu menuArticulos;
	public JMenu menuOpciones;
	public JMenuItem mnuiCrearArticulo;
	public JMenuItem mnuiEliminarArticulo;
	public JMenuItem mnuiModificarArticulo;
	public JMenuItem mnuiConsultarArticulo;
	public JMenuItem mnuiCrearTicket;
	public JMenuItem mnuiEliminarTicket;
	public JMenuItem mnuiModificarTicket;
	public JMenuItem mnuiConsultarTicket;
	public JMenuItem mnuiSalir;
	public ImageIcon imagenFondo = new ImageIcon("C:\\Users\\dekad\\Desktop\\GrupoStudium\\3. Segundo DAM\\4. Desarrollo de interfaces\\workspace DI\\DIT2_PracticaT2\\imagenes\\FondoMenu.png");
	public JLabel lblImagen;
	
	//Nueva opción del menú: Informes
	public JMenu menuInformes;
	public JMenuItem mniInformeArticulos;
	public JMenuItem mniInformeTickets;
	
	//Elementos del cuadro de dialogo
	public JDialog dlgTickets = new JDialog(this,true);
	public JLabel lblFechaDesde = new JLabel("Fecha Desde: ");
	public JLabel lblFechaHasta= new JLabel("Fecha Hasta: ");
	public JTextField txtFechaDesde = new JTextField();
	public JTextField txtFechaHasta = new JTextField();
	public JButton btnDlgAceptar = new JButton("Aceptar");
	
	
	/**
	 * Create the frame.
	 */
	public MenuPrincipal_vista() {
		setForeground(new Color(255, 255, 255));
		setTitle("Menú Principal");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dekad\\Desktop\\GrupoStudium\\3. Segundo DAM\\4. Desarrollo de interfaces\\workspace DI\\DIT2_PracticaT2\\imagenes\\Icono_Frame.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 300);
		
		barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		
		menuArticulos = new JMenu("Artículos");
		barraMenu.add(menuArticulos);
		
		mnuiCrearArticulo = new JMenuItem("Crear Artículo");
		menuArticulos.add(mnuiCrearArticulo);
		
		mnuiEliminarArticulo = new JMenuItem("Eliminar Artículo");
		menuArticulos.add(mnuiEliminarArticulo);
		
		mnuiModificarArticulo = new JMenuItem("Modificar Artículo");
		menuArticulos.add(mnuiModificarArticulo);
		
		mnuiConsultarArticulo = new JMenuItem("Consultar Artículo");
		menuArticulos.add(mnuiConsultarArticulo);
		
		menuTickets = new JMenu("Tickets");
		barraMenu.add(menuTickets);
		
		mnuiCrearTicket = new JMenuItem("Crear Ticket");
		menuTickets.add(mnuiCrearTicket);
		
		mnuiEliminarTicket = new JMenuItem("Eliminar Ticket");
		menuTickets.add(mnuiEliminarTicket);
		
		mnuiModificarTicket = new JMenuItem("Modificar Ticket");
		menuTickets.add(mnuiModificarTicket);
		
		mnuiConsultarTicket = new JMenuItem("Consultar Ticket");
		menuTickets.add(mnuiConsultarTicket);
		
		//1. Adición de elementos de la nueva opción de menú Informes
		menuInformes = new JMenu("Informes");
		barraMenu.add(menuInformes);
		
		mniInformeArticulos = new JMenuItem("Informe artículos");
		menuInformes.add(mniInformeArticulos);
		
		mniInformeTickets = new JMenuItem("Informe tickets");
		menuInformes.add(mniInformeTickets);
		// ------------------------------------------------------------
		menuOpciones = new JMenu("Opciones");
		barraMenu.add(menuOpciones);
		
		mnuiSalir = new JMenuItem("Salir");
		menuOpciones.add(mnuiSalir);
		
		//2. Montar cuadro de dialogo
		dlgTickets.setLayout(null);
		
		dlgTickets.add(lblFechaDesde);
		lblFechaDesde.setBounds(20, 10, 100, 20);
		dlgTickets.add(txtFechaDesde);
		txtFechaDesde.setBounds(120, 13, 100, 20);
		dlgTickets.add(lblFechaHasta);
		lblFechaHasta.setBounds(20, 40, 100, 20);
		dlgTickets.add(txtFechaHasta);
		txtFechaHasta.setBounds(120, 43, 100, 20);
		dlgTickets.add(btnDlgAceptar);
		btnDlgAceptar.setBounds(75,75,100,25);
		
		dlgTickets.setTitle("Consultar Tickets");
		dlgTickets.setSize(250, 150);
		dlgTickets.setResizable(false);
		dlgTickets.setLocationRelativeTo(null);
		dlgTickets.setVisible(false);
		//----------------------------------------------------
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblImagen = new JLabel("New label");
		lblImagen.setForeground(new Color(255, 255, 255));
		lblImagen.setIcon(imagenFondo);
		lblImagen.setBounds(0, 0, 336, 241);
		contentPane.add(lblImagen);
		
		setVisible(true);
	}
}
