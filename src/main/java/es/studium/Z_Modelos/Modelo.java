package es.studium.Z_Modelos;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import es.studium.C1_AltaTicket.AltaTicket_vista;
import es.studium.C3_ModificarTicket.ModificarTicket2_vista;

/**
 * La clase <b>Modelo</b> contiene los métodos de la aplicación que permiten la gestión de tickets y artículos.
 * @author Javier Pueyo
 * @version 2.0
 */
public class Modelo {

	JTextField campoTexto;

	/**
	 * El método <b>limpiarCampos()</b> tiene la función de borrar el contenido de una colección de campos de texto
	 * @param camposTexto Parámetro de tipo {@code JTextField...}. Se corresponde con una serie de campos de texto
	 * cuyos contenidos se pretenden borrar.
	 */
	public void limpiarCampos(JTextField... camposTexto) {
		for (JTextField campo : camposTexto) {
			campo.setText("");
		}
	}

	/**
	 * El método <b>limpiarCamposYTablas()</b> tiene la función de borrar el contenido de varios campos de textos y
	 * de una tabla.
	 * @param modeloTablaTicket Parámetro de tipo {@code DefaultTableModel}. Se corresponde con el modelo de la tabla que se desea limpiar.
	 * @param cuadroTextoTotal Parámetro de tipo {@code JTextField}. Se corresponde con un cuadro de texto que se desea limpiar.
	 * @param camposTexto Parámetro de tipo {@code JTextField}. Se corresponde con un cuadro de texto que se desea limpiar.
	 */
	public void limpiarCamposYTablas(DefaultTableModel modeloTablaTicket, JTextField cuadroTextoTotal,
			JTextField... camposTexto) {
		for (JTextField campo : camposTexto) {
			campo.setText("");
		}
		modeloTablaTicket.setRowCount(0);

		cuadroTextoTotal.setText(sumarColumnaTotalesLinea(3, modeloTablaTicket) + "");
	}

	/**
	 * El método <b>addArticulo()</b> tiene la función de agregar un artículo al ticket de la compra
	 * tras haberlo seleccionado de una tabla de artículos. Solicita al usuario la cantidad de unidades del artículo a agregar
	 * valiendose de un cuadro de diálogo, calcula el precio total del ticket y actualiza el modelo de la tabla
	 * donde se muestran los artículos pertenecientes a un ticket.
	 * @param vista Parámetro del tipo {@code AltaTicket_vista}. Se corresponde con la vista donde se muestra el 
	 * cuadro de diálogo para ingresar la cantidad del artículo.
	 * @param filaSeleccionada Parámetro de tipo {@code int}. Se corersponde con el índice de la fila seleccionada en la tabla de artículos.
	 * @param tablaArticulos Parámetro de tipo {@code JTable}. Se corresponde con la tabla que contiene los artículos 
	 * disponibles para agregar al ticket.
	 * @param modeloTablaTicket Parámetro del tipo {@code DefaultTableModel}. Se corresponde con el modelo de la tabla
	 * donde se alojan los artículos añadidos al ticket.
	 * @param cuadroTextoTotal Parámetro de tipo {@code JTextField}. Se corresponde con el cuadrod e texto donde se 
	 * muestra la suma de todos los precios de los artículos incluidos en el ticket.
	 */
	public void addArticulo(AltaTicket_vista vista, int filaSeleccionada, JTable tablaArticulos,
			DefaultTableModel modeloTablaTicket, JTextField cuadroTextoTotal) {
		String articuloAAnadir = "";

		if (filaSeleccionada != -1) {
			StringBuilder contenido = new StringBuilder();

			for (int col = 0; col < tablaArticulos.getColumnCount(); col++) {
				Object valor = tablaArticulos.getValueAt(filaSeleccionada, col);
				contenido.append(valor).append(" "); //
			}

			String cadenaContenido = contenido.toString();
			String[] ArrayContenido = cadenaContenido.split(" ");
			articuloAAnadir = ArrayContenido[1];

			int numUnidades = mostrarDialogo(vista, articuloAAnadir);

			if (!(numUnidades <= 0)) {

				BigDecimal precio = new BigDecimal(ArrayContenido[2]);
				BigDecimal unidades = new BigDecimal(numUnidades);
				BigDecimal totalLinea = precio.multiply(unidades);

				String[] nuevaLineaTabla = { ArrayContenido[0], ArrayContenido[1], ArrayContenido[2]
						, numUnidades + "", totalLinea.setScale(2, RoundingMode.HALF_UP) + "" };
				modeloTablaTicket.addRow(nuevaLineaTabla);

				cuadroTextoTotal.setText(sumarColumnaTotalesLinea(4, modeloTablaTicket) + "");

			} else {
				JOptionPane.showMessageDialog(null, "La cantidad mínima a añadir es una unidad");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Debe escoger un artículo de la lista.");
		}
	}

	/**
	 * El método <b>addArticulo2()</b> tiene la función de agregar un artículo al ticket de la compra
	 * tras haberlo seleccionado de una tabla de artículos. Solicita al usuario la cantidad de unidades del artículo a agregar
	 * valiendose de un cuadro de diálogo, calcula el precio total del ticket y actualiza el modelo de la tabla
	 * donde se muestran los artículos pertenecientes a un ticket.
	 * @param vista Parámetro del tipo {@code ModificarTicket2_vista}. Se corresponde con la vista donde se muestra el 
	 * cuadro de diálogo para ingresar la cantidad del artículo.
	 * @param filaSeleccionada Parámetro de tipo {@code int}. Se corersponde con el índice de la fila seleccionada en la tabla de artículos.
	 * @param tablaArticulos Parámetro de tipo {@code JTable}. Se corresponde con la tabla que contiene los artículos 
	 * disponibles para agregar al ticket.
	 * @param modeloTablaTicket Parámetro del tipo {@code DefaultTableModel}. Se corresponde con el modelo de la tabla
	 * donde se alojan los artículos añadidos al ticket.
	 * @param cuadroTextoTotal Parámetro de tipo {@code JTextField}. Se corresponde con el cuadrod e texto donde se 
	 * muestra la suma de todos los precios de los artículos incluidos en el ticket.
	 */
	public void addArticulo2(ModificarTicket2_vista vista, int filaSeleccionada, JTable tablaArticulos,
			DefaultTableModel modeloTablaTicket, JTextField cuadroTextoTotal) {
		String articuloAAnadir = "";

		if (filaSeleccionada != -1) {
			StringBuilder contenido = new StringBuilder();

			for (int col = 0; col < tablaArticulos.getColumnCount(); col++) {
				Object valor = tablaArticulos.getValueAt(filaSeleccionada, col);
				contenido.append(valor).append(" "); //
			}

			String cadenaContenido = contenido.toString();
			String[] ArrayContenido = cadenaContenido.split(" ");
			articuloAAnadir = ArrayContenido[0];

			int numUnidades = mostrarDialogo2(vista, articuloAAnadir);

			if (!(numUnidades <= 0)) {

				BigDecimal precio = new BigDecimal(ArrayContenido[1]);
				BigDecimal unidades = new BigDecimal(numUnidades);
				BigDecimal totalLinea = precio.multiply(unidades);

				String[] nuevaLineaTabla = { ArrayContenido[0], ArrayContenido[1], numUnidades + "",
						totalLinea.setScale(2, RoundingMode.HALF_UP) + "" };
				modeloTablaTicket.addRow(nuevaLineaTabla);

				cuadroTextoTotal.setText(sumarColumnaTotalesLinea(4, modeloTablaTicket) + "");

			} else {
				JOptionPane.showMessageDialog(null, "La cantidad mínima a añadir es una unidad");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Debe escoger un articulo de la lista.");
		}
	}

	/**
	 * El método <b>establecerTotal()</b> tiene la función de calcular la suma de los precios de todos
	 * los artículos incluidos en una tabla referente a un ticket.
	 * @param vista Parámetro de tipo {@code ModificarTicket2_vista}. Se corresponde con la vista donde 
	 * se encuentra la tabla de la cual se obtienen los datos para hacer el cálculo del precio total.
	 * @param modeloTablaTicket Parámetro del tipo {@code DefaultTableModel}. Se corresponde con el modelo de la tabla
	 * donde se alojan los artículos añadidos al ticket.
	 * @param cuadroTextoTotal Parámetro de tipo {@code JTextField}. Se corresponde con el campo de texto donde
	 * se establecerá el valor calculado por el método.
	 */
	public void establecerTotal(ModificarTicket2_vista vista, DefaultTableModel modeloTablaTicket,
			JTextField cuadroTextoTotal) {

		BigDecimal sumaTotal = BigDecimal.ZERO;
		int numFilas = modeloTablaTicket.getRowCount();

		if (numFilas == 0) {
			cuadroTextoTotal.setText("0.00");
			return;
		}

		for (int i = 0; i < numFilas; i++) {
			Object valor = modeloTablaTicket.getValueAt(i, 4);

			if (valor != null) {
				try {
					BigDecimal valorDecimal = new BigDecimal(valor.toString());
					sumaTotal = sumaTotal.add(valorDecimal);
				} catch (NumberFormatException e) {
					System.out.println("Error al convertir valor: " + valor + " a decimal.");
				}
			}
		}

		cuadroTextoTotal.setText(sumaTotal.setScale(2, RoundingMode.HALF_UP).toString());
	}

	/**
	 * El método <b>gestionBorradoArticulo()</b> tiene la función de eliminar un artículo de la aplicación.
	 * Antes de realizar el borrado, solicitará una confirmación en un cuadro de diálogo.
	 * @param filaSeleccionada Parámetro de tipo {@code int}. Se corresponde con la fila seleccionada en la 
	 * tabla de selección de artículos.
	 * @param entidad Parámetro de tipo {@code String}. Se corresponde con la entidad del elemento que se pretende borrar.
	 * @param tablaSeleccion Parámetro de tipo {@code JTable}. Se corresponde con la tabla que contiene los elementos de 
	 * la entidad del elemento que se pretende eliminar.
	 */
	public void gestionBorradoArticulo(int filaSeleccionada, String entidad, JTable tablaSeleccion) {

		if (filaSeleccionada != -1) {
			StringBuilder contenido = new StringBuilder();

			for (int col = 0; col < tablaSeleccion.getColumnCount(); col++) {
				Object valor = tablaSeleccion.getValueAt(filaSeleccionada, col);
				contenido.append(valor).append(" "); //
			}

			String cadenaContenido = contenido.toString();
			String[] ArrayContenido = cadenaContenido.split(" ");
			String idEntidadAEliminar = ArrayContenido[0];
			String entidadAEliminar = ArrayContenido[1];

			int respuesta = JOptionPane.showConfirmDialog(null,
					"¿Está seguro de que desea eliminar el " + entidad + " '" + entidadAEliminar + 
					"' ?", "Advertencia", JOptionPane.YES_NO_OPTION);

			if (respuesta == JOptionPane.YES_OPTION) {
				DefaultTableModel model = (DefaultTableModel) tablaSeleccion.getModel();
				ModeloMetodosBD.eliminarArticulo(idEntidadAEliminar);
				JOptionPane.showMessageDialog(null,
						"El " + entidad + ": '" + entidadAEliminar + "' ha sido eliminado.");
			} else if (respuesta == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(null, "Operación cancelada");
			}
		}
	}

	/**
	 * El método <b>gestionBorradoTicket()</b> tiene la función de eliminar un ticket de la aplicación.
	 * Antes de realizar el borrado, solicitará una confirmación en un cuadro de diálogo.
	 *  @param filaSeleccionada Parámetro de tipo {@code int}. Se corresponde con la fila seleccionada en la 
	 * tabla de selección de artículos.
	 * @param entidad Parámetro de tipo {@code String}. Se corresponde con la entidad del elemento que se pretende borrar.
	 * @param tablaSeleccion Parámetro de tipo {@code JTable}. Se corresponde con la tabla que contiene los elementos de 
	 * la entidad del elemento que se pretende eliminar.
	 */
	public void gestionBorradoTicket(int filaSeleccionada, String entidad, JTable tablaSeleccion) {

		if (filaSeleccionada != -1) {
			StringBuilder contenido = new StringBuilder();

			for (int col = 0; col < tablaSeleccion.getColumnCount(); col++) {
				Object valor = tablaSeleccion.getValueAt(filaSeleccionada, col);
				contenido.append(valor).append(" "); //
			}

			String cadenaContenido = contenido.toString();
			String[] ArrayContenido = cadenaContenido.split(" ");
			String idEntidadAEliminar = ArrayContenido[0];
			String fechaEntidadAEliminar = ArrayContenido[1];
			String importeEntidadAEliminar = ArrayContenido[2];

			int respuesta = JOptionPane.showConfirmDialog(null,
					"¿Está seguro de que desea eliminar el " + entidad + " '" + idEntidadAEliminar + 
					"' con fecha "+fechaEntidadAEliminar+" e importe de "+importeEntidadAEliminar+" €?", 
					"Advertencia", JOptionPane.YES_NO_OPTION);

			if (respuesta == JOptionPane.YES_OPTION) {
				DefaultTableModel model = (DefaultTableModel) tablaSeleccion.getModel();
				ModeloMetodosBD.eliminarTicket(idEntidadAEliminar);
				JOptionPane.showMessageDialog(null,
						"El " + entidad + ": '" + idEntidadAEliminar + "' ha sido eliminado.");
			} else if (respuesta == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(null, "Operación cancelada");
			}
		}
	}
	/**
	 * El método <b>mostrarDialogo()</b> crea un cuadro de diálogo que permite al usuario introducir el
	 * número de unidades de un artículo que desea introducir en un ticket.
	 * @param vista Parámetro de tipo {@code AltaTicket_vista}. Se corresponde con la vista donde se mostrará el diálogo.
	 * @param articulo Parámetro de tipo {@code String}. Se corresponde con el nombre del artículo cuyo número de unidades
	 * se ha de establecer.
	 * @return numUnidades que es un objeto de tipo {@code int} y se corresponde con el número de unidades
	 * que ha introducido el usuario.
	 */
	public int mostrarDialogo(AltaTicket_vista vista, String articulo) {
		final int[] numUnidades = { 1 };

		JDialog dialogo = new JDialog(vista, "Introduzca una cantidad", true);
		dialogo.setLayout(new FlowLayout());
		dialogo.setSize(380, 100);

		JLabel lblMensajeUnidades = new JLabel("¿Cuantas unidades de " + articulo + " desea incuir en el ticket?");
		dialogo.add(lblMensajeUnidades);

		JTextField txtUnidades = new JTextField(10);
		dialogo.add(txtUnidades);

		JButton btnAceptar = new JButton("Aceptar");
		dialogo.add(btnAceptar);

		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numUnidadesTexto = txtUnidades.getText();
				try {
					if (!numUnidadesTexto.isEmpty()) {
						numUnidades[0] = Integer.parseInt(numUnidadesTexto);
					}
					dialogo.dispose();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(vista, "Por favor, introduzca un número válido.");
				}
			}
		});

		dialogo.setVisible(true);

		return numUnidades[0];
	}

	/**
	 * El método <b>mostrarDialogo2()</b> crea un cuadro de diálogo que permite al usuario introducir el
	 * número de unidades de un artículo que desea introducir en un ticket.
	 * @param vista Parámetro de tipo {@code ModificarTicket2_vista}. Se corresponde con la vista donde se mostrará el diálogo.
	 * @param articulo Parámetro de tipo {@code String}. Se corresponde con el nombre del artículo cuyo número de unidades
	 * se ha de establecer.
	 * @return numUnidades que es un objeto de tipo {@code int} y se corresponde con el número de unidades
	 * que ha introducido el usuario.
	 */
	public int mostrarDialogo2(ModificarTicket2_vista vista, String articulo) {
		final int[] numUnidades = { 1 };

		JDialog dialogo = new JDialog(vista, "Introduzca una cantidad", true);
		dialogo.setLayout(new FlowLayout());
		dialogo.setSize(380, 100);

		JLabel lblMensajeUnidades = new JLabel("¿Cuantas unidades de " + articulo + " desea incuir en el ticket?");
		dialogo.add(lblMensajeUnidades);

		JTextField txtUnidades = new JTextField(10);
		dialogo.add(txtUnidades);

		JButton btnAceptar = new JButton("Aceptar");
		dialogo.add(btnAceptar);

		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numUnidadesTexto = txtUnidades.getText();
				try {
					if (!numUnidadesTexto.isEmpty()) {
						numUnidades[0] = Integer.parseInt(numUnidadesTexto);
					}
					dialogo.dispose();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(vista, "Por favor, introduzca un número válido.");
				}
			}
		});

		dialogo.setVisible(true);

		return numUnidades[0];
	}

	/**
	 * El método <b>borrarArticulo()</b> tiene la función de borrar una línea correspondiente a un artículo de la tabla
	 * que contiene los artículos pertenecientes a un ticket.
	 * @param vista Parámetro de tipo {@code AltaTicket_vista}. Se corresponde con la vista donde se encuentra la tabla
	 * que contiene los artículos correspondientes aun ticket.
	 * @param filaSeleccionada Parámetro de tipo {@code int}. Se corresponde con Se corersponde con el índice de la fila seleccionada en la tabla 
	 * que contiene los artículos de un ticket que se ha seleccionado y que, por tanto, se desea eliminar.
	 * @param modeloTablaTicket Parámetro de tipo {@code modeloTablaTicket}. Se corersponde con el modelo de la tabla
	 * que contiene los artículos correspondientes a un ticket.
	 * @param cuadroTextoTotal Parámetro de tipo {@code JTextField}. Se corresponde con el cuadro de texto donde se establece
	 * la suma de todos los precios de los artículos que permanecen en la tabla de artículos corerspondientes a un ticket.
	 */
	public void borrarArticulo(AltaTicket_vista vista, int filaSeleccionada, DefaultTableModel modeloTablaTicket,
			JTextField cuadroTextoTotal) {

		if (filaSeleccionada != -1) {

			modeloTablaTicket.removeRow(filaSeleccionada);
			cuadroTextoTotal.setText(sumarColumnaTotalesLinea(4, modeloTablaTicket) + "");
		}

	}

	/**
	 * El método <b>borrarArticulo2()</b> tiene la función de borrar una línea correspondiente a un artículo de la tabla
	 * que contiene los artículos pertenecientes a un ticket.
	 * @param vista Parámetro de tipo {@code ModificarTicket2_vista}. Se corresponde con la vista donde se encuentra la tabla
	 * que contiene los artículos correspondientes aun ticket.
	 * @param filaSeleccionada Parámetro de tipo {@code int}. Se corresponde con Se corersponde con el índice de la fila seleccionada en la tabla 
	 * que contiene los artículos de un ticket que se ha seleccionado y que, por tanto, se desea eliminar.
	 * @param modeloTablaTicket Parámetro de tipo {@code modeloTablaTicket}. Se corersponde con el modelo de la tabla
	 * que contiene los artículos correspondientes a un ticket.
	 * @param cuadroTextoTotal Parámetro de tipo {@code JTextField}. Se corresponde con el cuadro de texto donde se establece
	 * la suma de todos los precios de los artículos que permanecen en la tabla de artículos corerspondientes a un ticket.
	 */
	public void borrarArticulo2(ModificarTicket2_vista vista, int filaSeleccionada, 
			DefaultTableModel modeloTablaTicket, JTextField cuadroTextoTotal) {

		if (filaSeleccionada != -1) {

			modeloTablaTicket.removeRow(filaSeleccionada);
			cuadroTextoTotal.setText(sumarColumnaTotalesLinea(4, modeloTablaTicket) + "");
		}

	}

	/**
	 * El método <b>sumarColumnaTotalesLinea()</b> tiene la función de calcular la suma de los valores contenidos en
	 * la columna de una tabla.
	 * @param numColunmaTotalesLinea Parámetro de tipo {@code int}. Se corresponde con el índice de la columna de la
	 * cual se quiere calcular la suma de todos su valores.
	 * @param modeloTablaTicket Parámetro de tipo {@code DefaultTableModel}. Se corresponde con el modelo de la tabla
	 * que contiene los datos sobre los que se quiere realizar la operación.
	 * @return suma que es un objeto de tipo {@code BigDecimal} y se corresponde con la suma total de todos los valores de la columna.
	 */
	public BigDecimal sumarColumnaTotalesLinea(int numColunmaTotalesLinea, DefaultTableModel modeloTablaTicket) {
		BigDecimal suma = BigDecimal.ZERO;
		int numFilas = modeloTablaTicket.getRowCount();

		for (int i = 0; i < numFilas; i++) {
			Object valor = modeloTablaTicket.getValueAt(i, numColunmaTotalesLinea);

			if (valor != null) {
				try {
					BigDecimal valorDecimal = new BigDecimal(valor.toString());
					suma = suma.add(valorDecimal);
				} catch (NumberFormatException e) {
					System.out.println("Error al convertir valor: " + valor + " a decimal.");
				}
			}
		}
		return suma;
	}

	/**
	 * El método <b>mostrarContenidoTicket()</b> tiene la función de actualizar y mostrar el modelo de tabla de un ticket concreto.
	 * @param filaSeleccionada Parámetro del tipo {@code int}. Se corresponde con el índice de la fila seleccionada cuyo ticket se desea mostrar.
	 * @param modeloTablaTicket Parámetro del tipo {@code DefaultTableModel}. Se corresponde con el modelod e tabla que será actualizado con 
	 * los datos del ticket.
	 * @param tickets Parámetro de tipo {@code String[]}. Se corresponde con un array de tickets,cada uno representado como un array de cadenas.
	 */
	public void mostrarContenidoTicket(int filaSeleccionada, DefaultTableModel modeloTablaTicket, String[]... tickets) {

		modeloTablaTicket.setRowCount(0); 

		if (filaSeleccionada >= 0 && filaSeleccionada < tickets.length) {
		
			String[] ticketSeleccionado = tickets[filaSeleccionada];

			String[] ticketDesdePosicion3 = new String[ticketSeleccionado.length - 3];
			System.arraycopy(ticketSeleccionado, 3, ticketDesdePosicion3, 0, ticketDesdePosicion3.length);

			int fila = 0;
			int col = 0;

			for (String valor : ticketDesdePosicion3) {

				if (col >= modeloTablaTicket.getColumnCount()) {
					fila++;
					col = 0; 
				}

				while (modeloTablaTicket.getRowCount() <= fila) {
					modeloTablaTicket.addRow(new String[modeloTablaTicket.getColumnCount()]); 
																								
				}

				modeloTablaTicket.setValueAt(valor, fila, col);
				col++; 
			}
		} else {
			System.out.println("Fila seleccionada fuera de rango.");
		}
	}

	/**
	 * El método <b>esFechaValida()</b> tiene la función de comprobar que una fecha sigue el formato
	 * dd/mm/aaa.
	 * @param fecha Parámetro de tipo {@code String}. Se corresponde con la fecha que hay que validar.
	 * @return true / false que son valores de tipo {@code boolean} en funciónd e si la fecha tiene o no el formato
	 * válido.
	 */
	public static boolean esFechaValida(String fecha) {

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {

			LocalDate fechaParseada = LocalDate.parse(fecha, formato);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	/**
	 * El método <b>escalarImagen</b> tiene la función de redimensionar una imagen representada por un
	 * {@code ImageIcon} a un nuevo tamaño.
	 * @param icono Parámetro de tipo {@code ImageIcon}. Se corresponde con el objeto que contiene la imagen que se desea redimensionar.
	 * @param ancho Parámetro de tipo {@code int}. Se corresponde con el ancho que se le desea dar a la imágen.
	 * @param alto Parámetro de tipo {@code int}. Se corresponde con el alto que se le desea dar a la imágen.
	 * @return new ImageIcon(imagenEscalada), que se corresponde con una nueva instancia de ImageIcon pero esta vez a partir de la imagen redimensionada.
	 */
	public ImageIcon escalarImagen(ImageIcon icono, int ancho, int alto) {
		Image imagenOriginal = icono.getImage();
		Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH); 
		return new ImageIcon(imagenEscalada); 
	}

	/**
	 * El método <b>ajustarAnchoColumnas()</b> tiene la función de ajustar el de las columnas de un 
	 * {@code JTable} según el contenido más largo de cada columna, tomando en cuenta tanto las cabeceras como 
	 * los valores de las celdas.
	 * @param tabla Parámetro de tipo {@code JTable }. Se corresponde con la tabla cuyo ancho de columna
	 * se desea ajustar.
	 */
	public void ajustarAnchoColumnas(JTable tabla) {
		for (int columna = 0; columna < tabla.getColumnCount(); columna++) {

			String cabecera = tabla.getColumnName(columna);
			int maxLongitud = cabecera.length();

			for (int fila = 0; fila < tabla.getRowCount(); fila++) {
				Object valor = tabla.getValueAt(fila, columna);
				if (valor != null) {
					maxLongitud = Math.max(maxLongitud, valor.toString().length());
				}
			}

			TableColumn columnaTabla = tabla.getColumnModel().getColumn(columna);
			columnaTabla.setPreferredWidth(maxLongitud * 10);
		}
	}

	/**
	 * El método <b>String europeoMysql()</b> tiene la función de convertir una fecha en formato europeo
	 * (dd/mm/aaaa) en formato MySQL (aaaa-mm-dd).
	 * @param fecha Parámetro de tipo {@code String}. Se corersponde con la fecha que hay que transformar.
	 * @return fechaTransformada que es un parámetro de tipo {@code String}. Se corresponde con la fecha transformada.
	 */
	public static String europeoMysql(String fecha) {

		String fechaTransformada = "";
		String[] temporal = fecha.split("/");
		fechaTransformada = temporal[2] + "-" + temporal[1] + "-" + temporal[0];
		return (fechaTransformada);
	}

	/**
	 * El método <b>String mysqlEuropeo()</b> tiene la función de convertir una fecha en formato MySQL (aaaa-mm-dd) 
	 * en formato europeo (dd/mm/aaaa).
	 * @param fecha Parámetro de tipo {@code String}. Se corersponde con la fecha que hay que transformar.
	 * @return fechaTransformada que es un parámetro de tipo {@code String}. Se corresponde con la fecha transformada.
	 */
	public static String mysqlEuropeo(String fecha) {
		String fechaTransformada = "";
		String[] temporal = fecha.split("-");
		fechaTransformada = temporal[2] + "/" + temporal[1] + "/" + temporal[0];
		// recibe 2024-03-20
		// devuelve 20/03/2024
		return (fechaTransformada);
	}
	
	/**
	 * El método <b>obtenerValoresColumna()</b> tiene la función de obtener los valores de una columna específica
	 * de un {@code JTable} para devolverlos como un array de cadenas.
	 * @param tabla Parámetro de tipo {@code JTable}. Se corresponde con la tabla de la cual se extraerán los valores.
	 * @param numColumna Parámetro de tipo {@code in}. Se corresponde con el índice de la columna de la tabla del que se extraerán
	 * los valores.
	 * @return valores que es un objeto de tipo {@code String[]} y se corresponde con un array que contiene los valores de la columna
	 * seleccionada.
	 */
	public static String[] obtenerValoresColumna(JTable tabla, int numColumna) {
	    int numFilas = tabla.getRowCount();
	    String[] valores = new String[numFilas]; 

	    for (int i = 0; i < numFilas; i++) {
	        Object valor = tabla.getValueAt(i, numColumna); 
	        valores[i] = valor != null ? valor.toString() : null; 
	    }

	    return valores; 
	}
	
	/**
	 * El método <b>obtenerValorIdTicketSeleccionado</b> tiene la función de obtener el valor de la primera columna
	 * de la fila seleccionada de una tabla, el cuals e corresponde con el identificador de un ticket.
	 * @param tabla Parámetro de tipo {@code JTable}. Se corresponde con la tabla de la cual se obtendrá el valor.
	 * @return tabla.getModel().getValueAt(filaSeleccionada, 0).toString(), que se corresponde con el valor de la 
	 * primera columna de la fila seleccionada. Devolverá {@code null} si nos e ha seleccionado ninguna fila.
	 */
	public static String obtenerValorIdTicketSeleccionado(JTable tabla) {
	    int filaSeleccionada = tabla.getSelectedRow(); 
	    if (filaSeleccionada != -1) { 
	        return tabla.getModel().getValueAt(filaSeleccionada, 0).toString(); 
	    }
	    return null; 
	}
	
	/**
	 * El método <b>addArticuloActualizarTicket</b> tiene la función de añadir un artículo a la tabla que contiene los artículos 
	 * correspondientes a un ticket en la ventana 'Modificar tickets'. Muestra un diálogo para que el usuario introduzca
	 * la cantidad de dicho artículo que quiere incluir en el ticket.  A continuación, calcula la suma de los precios
	 * de todos los artículos presentes en el ticket y los agrega al modelo de la tabla.
	 * @param vista Parámetro tipo {@code ModificarTicket2_vista}. Se corresponde conn la vista donde se encuentra la
	 * tabla donde se encuentran lso artículos que pueden ser añadidos al ticket.
	 * @param filaSeleccionada Parámetro de tipo {@code int}. Se corresponde con la fila seleccionada de la tabla en la cual
	 * se encuentra la información del artículo que se pretende añadir al ticket.
	 * @param tablaArticulos Parámetro de tipo {@code JTable}. Se correspon decon la tabla que contiene los artículos que pueden ser 
	 * añadidos a un ticket.
	 * @param modeloTablaTicket Parámetro de tipo {@code DefaultTableModel}. Se corresponde con el modelo de la tabla donde
	 * se encuentran los artículos correspondientes a un ticket concreto.
	 * @param cuadroTextoTotal Parámetro de tipo {@code JTextField}. Se corresponde con el cuadro de texto donde se establece
	 * la suma de todos los precios de los artículos correspondientes a un ticket concreto.
	 * @return modeloTablaTicket el cual es un objeto de tipo {@code DefaultTableModel}. Se corresponde con el modelo actualizado de 
	 * la tabla que contiene los artículos correspondientes a un ticket concreto.
	 */
	public DefaultTableModel addArticuloActualizarTicket(ModificarTicket2_vista vista, int filaSeleccionada,
			JTable tablaArticulos, DefaultTableModel modeloTablaTicket, JTextField cuadroTextoTotal) {
		String articuloAAnadir = "";

		if (filaSeleccionada != -1) {
			StringBuilder contenido = new StringBuilder();

			for (int col = 0; col < tablaArticulos.getColumnCount(); col++) {
				Object valor = tablaArticulos.getValueAt(filaSeleccionada, col);
				contenido.append(valor).append(" "); //
			}

			String cadenaContenido = contenido.toString();
			String[] ArrayContenido = cadenaContenido.split(" ");
			articuloAAnadir = ArrayContenido[1];

			int numUnidades = mostrarDialogoActualizarTickets(vista, articuloAAnadir);

			if (!(numUnidades <= 0)) {

				BigDecimal precio = new BigDecimal(ArrayContenido[2]);
				BigDecimal unidades = new BigDecimal(numUnidades);
				BigDecimal totalLinea = precio.multiply(unidades);

				String[] nuevaLineaTabla = { ArrayContenido[0], ArrayContenido[1], ArrayContenido[2], 
						numUnidades + "", totalLinea.setScale(2, RoundingMode.HALF_UP) + "" };
				modeloTablaTicket.addRow(nuevaLineaTabla);

				cuadroTextoTotal.setText(sumarColumnaTotalesLinea(4, modeloTablaTicket) + "");

			} else {
				JOptionPane.showMessageDialog(null, "La cantidad mínima a añadir es una unidad");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Debe escoger un artículo de la lista.");
		}
		
		return modeloTablaTicket;
	}

	/**
	 * El método <b>mostrarDialogoActualizarTickets</b> tiene como función mostrar un diálogo para que el usuario ingrese la cantidad de unidades de un artículo
     * que desea agregar a un ticket ticket.
	 * @param vista Parámetro de tipo {@code ModificarTicket2_vista}. Se corresponde con la vista donde se mostrará el diálogo.
	 * @param articulo Parámetro de tipo {@code String}. Se corresponde con el nombre del artículo del cuál se solicita la cantidad.
	 * @return numUnidades que es una variable de tipo {@code int}. Se corresponde con el número de unidades del artículo que ha introducido el usuario.
	 */
	public int mostrarDialogoActualizarTickets(ModificarTicket2_vista vista, String articulo) {
		final int[] numUnidades = { 1 };

		JDialog dialogo = new JDialog(vista, "Introduzca una cantidad", true);
		dialogo.setLayout(new FlowLayout());
		dialogo.setSize(380, 100);

		JLabel lblMensajeUnidades = new JLabel("¿Cuantas unidades de " + articulo + " desea incuir en el ticket?");
		dialogo.add(lblMensajeUnidades);

		JTextField txtUnidades = new JTextField(10);
		dialogo.add(txtUnidades);

		JButton btnAceptar = new JButton("Aceptar");
		dialogo.add(btnAceptar);

		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numUnidadesTexto = txtUnidades.getText();
				try {
					if (!numUnidadesTexto.isEmpty()) {
						numUnidades[0] = Integer.parseInt(numUnidadesTexto);
					}
					dialogo.dispose();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(vista, "Por favor, introduzca un número válido.");
				}
			}
		});

		dialogo.setVisible(true);

		return numUnidades[0];
	}

}
