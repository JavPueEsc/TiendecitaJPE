package es.studium.B2_EliminarArticulo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.JViewport;

import java.awt.Toolkit;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class EliminarArticulo_vista extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JButton btnVolver;
	public JButton btnSeleccionar;
	
	public String[] nombreColumnas = {"Id", "Descripción", "Precio (€)","Cantidad"};
	public JTable tabla = new JTable();
	DefaultTableModel modeloTabla = new DefaultTableModel(nombreColumnas, 0);
	JScrollPane scrollPane = new JScrollPane(tabla);
	JViewport vistaScrollpanel = scrollPane.getViewport();
	LineBorder border = new LineBorder(Color.white, 0);
	
	/**
	 * Create the frame.
	 */
	public EliminarArticulo_vista() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dekad\\Desktop\\GrupoStudium\\3. Segundo DAM\\4. Desarrollo de interfaces\\workspace DI\\DIT2_PracticaT2\\imagenes\\Icono_Frame.png"));
		setTitle("Eliminar Artículos");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabla.setModel(modeloTabla);
		vistaScrollpanel.setBackground(Color.white); 
		scrollPane.setBorder(border);
		scrollPane.setBounds(23, 53, 285, 167);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Seleccione un artículo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(34, 8, 210, 40);
		contentPane.add(lblNewLabel);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(223, 19, 75, 23);
		contentPane.add(btnVolver);
		
		btnSeleccionar = new JButton("Eliminar artículo");
		btnSeleccionar.setBounds(81, 229, 171, 23);
		contentPane.add(btnSeleccionar);
		
		setVisible(true);
	}
}
