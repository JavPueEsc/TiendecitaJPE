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

import es.studium.B1_AltaArticulo.AltaArticulo_vista;
import es.studium.C1_AltaTicket.AltaTicket_vista;
import es.studium.C3_ModificarTicket.ModificarTicket2_vista;

public class Modelo {

	JTextField campoTexto;

	public void limpiarCampos(JTextField... camposTexto) {
		for (JTextField campo : camposTexto) {
			campo.setText("");
		}
	}

	public void limpiarCamposYTablas(DefaultTableModel modeloTablaTicket, JTextField cuadroTextoTotal,
			JTextField... camposTexto) {
		for (JTextField campo : camposTexto) {
			campo.setText("");
		}
		modeloTablaTicket.setRowCount(0);

		cuadroTextoTotal.setText(sumarColumnaTotalesLinea(3, modeloTablaTicket) + "");
	}

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
			articuloAAnadir = ArrayContenido[0];

			int numUnidades = mostrarDialogo(vista, articuloAAnadir);

			if (!(numUnidades <= 0)) {

				BigDecimal precio = new BigDecimal(ArrayContenido[1]);
				BigDecimal unidades = new BigDecimal(numUnidades);
				BigDecimal totalLinea = precio.multiply(unidades);

				String[] nuevaLineaTabla = { ArrayContenido[0], ArrayContenido[1], numUnidades + "",
						totalLinea.setScale(2, RoundingMode.HALF_UP) + "" };
				modeloTablaTicket.addRow(nuevaLineaTabla);

				cuadroTextoTotal.setText(sumarColumnaTotalesLinea(3, modeloTablaTicket) + "");

			} else {
				JOptionPane.showMessageDialog(null, "La cantidad mínima a añadir es una unidad");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Debe escoger un artículo de la lista.");
		}
	}

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

				cuadroTextoTotal.setText(sumarColumnaTotalesLinea(3, modeloTablaTicket) + "");

			} else {
				JOptionPane.showMessageDialog(null, "La cantidad mínima a añadir es una unidad");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Debe escoger un articulo de la lista.");
		}
	}

	public void establecerTotal(ModificarTicket2_vista vista, DefaultTableModel modeloTablaTicket,
			JTextField cuadroTextoTotal) {

		BigDecimal sumaTotal = BigDecimal.ZERO;
		int numFilas = modeloTablaTicket.getRowCount();

		if (numFilas == 0) {
			cuadroTextoTotal.setText("0.00");
			return;
		}

		for (int i = 0; i < numFilas; i++) {
			Object valor = modeloTablaTicket.getValueAt(i, 3);

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

	public void gestionBorrado(int filaSeleccionada, String entidad, JTable tablaSeleccion) {

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
			// System.out.println(ArticuloAEliminar);

			int respuesta = JOptionPane.showConfirmDialog(null,
					"¿Está seguro de que desea eliminar el " + entidad + " '" + entidadAEliminar + "' ?", "Advertencia",
					JOptionPane.YES_NO_OPTION);

			if (respuesta == JOptionPane.YES_OPTION) {
				DefaultTableModel model = (DefaultTableModel) tablaSeleccion.getModel();
				ModeloMetodosBD.eliminarArticulo(idEntidadAEliminar);
				//model.removeRow(filaSeleccionada);
				JOptionPane.showMessageDialog(null,
						"El " + entidad + ": '" + entidadAEliminar + "' ha sido eliminado.");
			} else if (respuesta == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(null, "Operación cancelada");
			}
		}
	}

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

	public void borrarArticulo(AltaTicket_vista vista, int filaSeleccionada, DefaultTableModel modeloTablaTicket,
			JTextField cuadroTextoTotal) {

		// int numeroDeFilasTabla = modeloTablaTicket.getRowCount();

		if (filaSeleccionada != -1) {

			modeloTablaTicket.removeRow(filaSeleccionada);
			cuadroTextoTotal.setText(sumarColumnaTotalesLinea(3, modeloTablaTicket) + "");
		}

	}

	public void borrarArticulo2(ModificarTicket2_vista vista, int filaSeleccionada, DefaultTableModel modeloTablaTicket,
			JTextField cuadroTextoTotal) {

		// int numeroDeFilasTabla = modeloTablaTicket.getRowCount();

		if (filaSeleccionada != -1) {

			modeloTablaTicket.removeRow(filaSeleccionada);
			cuadroTextoTotal.setText(sumarColumnaTotalesLinea(3, modeloTablaTicket) + "");
		}

	}

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

	public void mostrarTicket(DefaultTableModel modeloTablaSeleccion, String[]... tickets) {
		for (String[] ticket : tickets) {
			String[] linea = { ticket[0], ticket[1], ticket[2] };
			modeloTablaSeleccion.addRow(linea);
		}
	}

	public void mostrarContenidoTicket(int filaSeleccionada, DefaultTableModel modeloTablaTicket, String[]... tickets) {

		// Limpiar el modelo de la tabla antes de mostrar los nuevos datos
		modeloTablaTicket.setRowCount(0); // Limpia todas las filas previas

		// Comprobar si la fila seleccionada está dentro del rango de los arrays
		// proporcionados
		if (filaSeleccionada >= 0 && filaSeleccionada < tickets.length) {
			// Obtener el array (ticket) correspondiente a la fila seleccionada
			String[] ticketSeleccionado = tickets[filaSeleccionada];

			// Crear un nuevo array que contenga solo los elementos a partir de la posición
			// 3
			String[] ticketDesdePosicion3 = new String[ticketSeleccionado.length - 3];
			System.arraycopy(ticketSeleccionado, 3, ticketDesdePosicion3, 0, ticketDesdePosicion3.length);

			// Inicializar el índice de fila y columna
			int fila = 0;
			int col = 0;

			// Añadir elementos a la tabla de izquierda a derecha
			for (String valor : ticketDesdePosicion3) {
				// Si se ha llegado al final de las columnas, mover a la siguiente fila
				if (col >= modeloTablaTicket.getColumnCount()) {
					fila++;
					col = 0; // Reiniciar el índice de columnas
				}

				// Asegurarse de que hay suficientes filas en el modelo
				while (modeloTablaTicket.getRowCount() <= fila) {
					modeloTablaTicket.addRow(new String[modeloTablaTicket.getColumnCount()]); // Añade una nueva fila
																								// vacía
				}

				// Asignar el valor a la celda correspondiente
				modeloTablaTicket.setValueAt(valor, fila, col);
				col++; // Mover a la siguiente columna
			}
		} else {
			// Si la fila seleccionada no es válida, mostrar un mensaje de error
			System.out.println("Fila seleccionada fuera de rango.");
		}
	}
	
public static boolean esFechaValida(String fecha) {
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            
            LocalDate fechaParseada = LocalDate.parse(fecha, formato);
            return true; 
        } catch (DateTimeParseException e) {
            return false; 
        }
    }

public ImageIcon escalarImagen(ImageIcon icono, int ancho, int alto) {
    Image imagenOriginal = icono.getImage();
    Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH); // Escalar suavemente
    return new ImageIcon(imagenEscalada); // Devolver la imagen escalada como ImageIcon
}

}
