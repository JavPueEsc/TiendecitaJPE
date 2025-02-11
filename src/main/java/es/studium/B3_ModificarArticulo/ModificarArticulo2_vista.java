package es.studium.B3_ModificarArticulo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

/**
 * La clase <b>ModificarArticulo2_vista</b> contiene los componentes gráficos de la ventana 'Modificar Artículos'.
 * @author Javier Pueyo
 * @version 2.0
 */
public class ModificarArticulo2_vista extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField txtDescripcion;
	public JTextField txtPrecio;
	public JTextField txtCantidad;
	
	ImageIcon iconoVolver = new ImageIcon("C:\\Users\\dekad\\Desktop\\GrupoStudium\\3. Segundo DAM\\4. Desarrollo de interfaces\\workspace DI\\DIT2_PracticaT2\\imagenes\\Icono casa.png");
	Image iconoVolverRedimensionado = iconoVolver.getImage().getScaledInstance(66, 37, java.awt.Image.SCALE_SMOOTH);
	public JButton btnModificarArticulo;
	public JButton btnLimpiar;
	public JButton btnVolver;

	/**
	 * Create the frame.
	 */
	public ModificarArticulo2_vista() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dekad\\Desktop\\GrupoStudium\\3. Segundo DAM\\4. Desarrollo de interfaces\\workspace DI\\DIT2_PracticaT2\\imagenes\\Icono_Frame.png"));
		setTitle("Modificar Artículo");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Descripción*");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(34, 62, 74, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Precio (€)*");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(34, 105, 74, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cantidad*");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(34, 145, 74, 20);
		contentPane.add(lblNewLabel_2);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(129, 62, 167, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(129, 105, 167, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(129, 145, 167, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Modificar artículo");
		lblNewLabel_3.setForeground(new Color(0, 128, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(34, 8, 151, 40);
		contentPane.add(lblNewLabel_3);
		
		btnModificarArticulo = new JButton("Modificar Artículo");
		btnModificarArticulo.setBounds(34, 198, 150, 23);
		contentPane.add(btnModificarArticulo);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(201, 198, 95, 23);
		contentPane.add(btnLimpiar);
		
		JLabel lblNewLabel_4 = new JLabel("Los campos marcados con * son obligatorios.");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(34, 232, 280, 20);
		contentPane.add(lblNewLabel_4);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(223, 19, 75, 23);
		contentPane.add(btnVolver);
		
		setVisible(true);
	}
}
