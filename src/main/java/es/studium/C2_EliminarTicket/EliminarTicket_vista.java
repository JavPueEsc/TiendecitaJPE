package es.studium.C2_EliminarTicket;

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

public class EliminarTicket_vista extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTable tablaSeleccion;
	public JButton btnVolver;
	public JButton btnEliminar;
	public DefaultTableModel modeloTablaSeleccion;
	private JTable tablaTicket;
	public DefaultTableModel modeloTablaTicket;
	
	/**
	 * Create the frame.
	 */
	public EliminarTicket_vista() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dekad\\Desktop\\GrupoStudium\\3. Segundo DAM\\4. Desarrollo de interfaces\\workspace DI\\DIT2_PracticaT2\\imagenes\\Icono_Frame.png"));
		setTitle("Eliminar Tickets");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 53, 309, 167);
		contentPane.add(scrollPane);
		
		modeloTablaSeleccion = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Num. ticket", "Fecha", "Total (€)"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		
		tablaSeleccion = new JTable(modeloTablaSeleccion);
		scrollPane.setViewportView(tablaSeleccion);
		
		JLabel lblNewLabel = new JLabel("Seleccione un ticket");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(34, 8, 198, 40);
		contentPane.add(lblNewLabel);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(665, 19, 75, 23);
		contentPane.add(btnVolver);
		
		btnEliminar = new JButton("Eliminar ticket");
		btnEliminar.setBounds(81, 229, 171, 23);
		contentPane.add(btnEliminar);
		
		JScrollPane scrollPane_ticket = new JScrollPane();
		scrollPane_ticket.setBounds(370, 53, 370, 167);
		contentPane.add(scrollPane_ticket);
		
		
		modeloTablaTicket = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Artículo", "Precio (€)", "Unidades", "Total línea (€)"
			}
		);
		tablaTicket = new JTable(modeloTablaTicket);
		scrollPane_ticket.setViewportView(tablaTicket);
		
		JLabel lblNewLabel_1 = new JLabel("Contenido del ticket seleccionado");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(370, 8, 285, 40);
		contentPane.add(lblNewLabel_1);
		
		setVisible(true);
	}
}
