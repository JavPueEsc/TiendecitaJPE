package es.studium.C1_AltaTicket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JViewport;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AltaTicket_vista extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField txtFecha;
	public JTextField txtTotal;
	
	ImageIcon iconoVolver = new ImageIcon("C:\\Users\\dekad\\Desktop\\GrupoStudium\\3. Segundo DAM\\4. Desarrollo de interfaces\\workspace DI\\DIT2_PracticaT2\\imagenes\\Icono casa.png");
	Image iconoVolverRedimensionado = iconoVolver.getImage().getScaledInstance(66, 37, java.awt.Image.SCALE_SMOOTH);
	public JButton btnCrearTicket;
	public JButton btnLimpiar;
	public JButton btnVolver;
	public JTable tablaTickets;
	//public JScrollPane scrollPaneArticulos;
	//public JTable tablaArticulos;
	public JButton btnAdd;
	public JButton btnQuitar;
	public DefaultTableModel modeloTablaTicket;

	public String[] nombreColumnas = {"Id", "Descripción", "Precio (€)","Cantidad"};
	public JTable tablaArticulos = new JTable();
	DefaultTableModel modeloTabla = new DefaultTableModel(nombreColumnas, 0);
	JScrollPane scrollPane = new JScrollPane(tablaArticulos);
	JViewport vistaScrollpanel = scrollPane.getViewport();
	LineBorder border = new LineBorder(Color.white, 0);
	
	/**
	 * Create the frame.
	 */
	public AltaTicket_vista() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dekad\\Desktop\\GrupoStudium\\3. Segundo DAM\\4. Desarrollo de interfaces\\workspace DI\\DIT2_PracticaT2\\imagenes\\Icono_Frame.png"));
		setTitle("Crear Ticket");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFecha = new JLabel("Fecha del ticket*");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFecha.setBounds(436, 20, 154, 20);
		contentPane.add(lblFecha);
		
		JLabel lblTotal = new JLabel("Total ticket (€)");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotal.setBounds(585, 199, 95, 20);
		contentPane.add(lblTotal);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(585, 22, 85, 20);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);
		
		txtTotal = new JTextField();
		txtTotal.setForeground(new Color(255, 0, 0));
		txtTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtTotal.setEditable(false);
		txtTotal.setBounds(679, 199, 85, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Crear ticket");
		lblNewLabel_3.setForeground(new Color(255, 0, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(34, 20, 137, 20);
		contentPane.add(lblNewLabel_3);
		
		btnCrearTicket = new JButton("Crear Ticket");
		btnCrearTicket.setBounds(34, 198, 120, 23);
		contentPane.add(btnCrearTicket);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(171, 198, 95, 23);
		contentPane.add(btnLimpiar);
		
		JLabel lblNewLabel_4 = new JLabel("Los campos marcados con * son obligatorios.");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(255, 232, 280, 20);
		contentPane.add(lblNewLabel_4);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(689, 21, 75, 23);
		contentPane.add(btnVolver);
		
		JScrollPane scrollPaneTickets = new JScrollPane();
		scrollPaneTickets.setBounds(436, 93, 328, 86);
		contentPane.add(scrollPaneTickets);
		
		
		modeloTablaTicket= new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Descripción", "Precio (\u20AC)", "Unidades", "Total línea (€)"
			}
		);
		tablaTickets = new JTable(modeloTablaTicket);
		scrollPaneTickets.setViewportView(tablaTickets);
		
		//tabla artículos
		vistaScrollpanel.setBackground(Color.white); 
		scrollPane.setBorder(border);
		scrollPane.setBounds(34, 93, 232, 86);
		contentPane.add(scrollPane);
		
		btnAdd = new JButton("Añadir artículo \u2192");
		btnAdd.setBounds(280, 105, 140, 23);
		contentPane.add(btnAdd);
		
		btnQuitar = new JButton("\u2190 Quitar artículo");
		btnQuitar.setBounds(280, 141, 140, 23);
		contentPane.add(btnQuitar);
		
		JLabel lblNewLabel_1 = new JLabel("Contenido del ticket ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(436, 55, 199, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Artículos disponibles");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(34, 55, 199, 23);
		contentPane.add(lblNewLabel_1_1);
		
		setVisible(true);
	}
}
