package es.studium.A_MenuPrincipal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import java.awt.Color;

public class MenuPrincipal_vista extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
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
	public JMenu menuOpciones;
	
	
	
	/**
	 * Create the frame.
	 */
	public MenuPrincipal_vista() {
		setForeground(new Color(255, 255, 255));
		setTitle("Menú Principal");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dekad\\Desktop\\GrupoStudium\\3. Segundo DAM\\4. Desarrollo de interfaces\\workspace DI\\DIT2_PracticaT2\\imagenes\\Icono_Frame.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuArticulos = new JMenu("Artículos");
		menuBar.add(menuArticulos);
		
		mnuiCrearArticulo = new JMenuItem("Crear Artículo");
		menuArticulos.add(mnuiCrearArticulo);
		
		mnuiEliminarArticulo = new JMenuItem("Eliminar Artículo");
		menuArticulos.add(mnuiEliminarArticulo);
		
		mnuiModificarArticulo = new JMenuItem("Modificar Artículo");
		menuArticulos.add(mnuiModificarArticulo);
		
		mnuiConsultarArticulo = new JMenuItem("Consultar Artículo");
		menuArticulos.add(mnuiConsultarArticulo);
		
		JMenu menuTickets = new JMenu("Tickets");
		menuBar.add(menuTickets);
		
		mnuiCrearTicket = new JMenuItem("Crear Ticket");
		menuTickets.add(mnuiCrearTicket);
		
		mnuiEliminarTicket = new JMenuItem("Eliminar Ticket");
		menuTickets.add(mnuiEliminarTicket);
		
		mnuiModificarTicket = new JMenuItem("Modificar Ticket");
		menuTickets.add(mnuiModificarTicket);
		
		mnuiConsultarTicket = new JMenuItem("Consultar Ticket");
		menuTickets.add(mnuiConsultarTicket);
		
		menuOpciones = new JMenu("Opciones");
		menuBar.add(menuOpciones);
		
		mnuiSalir = new JMenuItem("Salir");
		menuOpciones.add(mnuiSalir);
		
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
