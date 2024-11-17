package es.studium.B2_EliminarArticulo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class EliminarArticulo_vista extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTable tabla;
	public JButton btnVolver;
	public JButton btnSeleccionar;

	
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 53, 264, 167);
		contentPane.add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		tabla.setModel(new DefaultTableModel(
			new Object[][] {
				{"Pastel", "1.00", "20"},
				{"Manzana", "0.20", "50"},
				{"Detergente", "2.00", "15"},
				{"Pizza", "1.50", "15"},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Descripci\u00F3n", "Precio (\u20AC)", "Cantidad"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JLabel lblNewLabel = new JLabel("Seleccione un artículo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(34, 8, 198, 40);
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
