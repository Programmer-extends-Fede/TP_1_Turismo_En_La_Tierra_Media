package ventanaConsolaNuevaYLaminas;

import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;

import atraccion.Atraccion;
import promocion.Promocion;
import sugerencia.Sugerencia;
import tierraMediaYEntradaSalida.EntradaSalida;
import tierraMediaYEntradaSalida.TierraMedia;
import usuarioEItinerario.Usuario;

public class ConsolaNueva implements Runnable {

	static ArrayList<Atraccion> atraccionesTemp = new ArrayList<Atraccion>();
	static final String ENTRADA_INCORRECTA = "<<<ENTRADA INCORRECTA>>>";
	static final String SEPARADOR_USUARIOS = "_".repeat(100) + "\n";
	static final String SUBRAYADO = "-".repeat(45);
	static final String MENSAJE_INICIAL = "\nESTE ES EL SISTEMA DE COMPRAS DE TIERRAMEDIA\n"
			+ "\nRECORDA, PRESIONA 'COMPRAR' PARA ACEPTAR LA COMPRA Y 'NO COMPRAR' PARA RECHAZARLA\n\n";
	static final String MENSAJE_FINAL = "\n" + SUBRAYADO + "\nMUCHAS GRACIAS, YA NO QUEDAN USUARIOS POR VER\n"
			+ SUBRAYADO;

	Thread hilo;
	boolean pausar; // Suspende el hilo cuando es true
	JTextPane miArea;
	String respuesta = "";

	public ConsolaNueva(String nombre, JTextPane areaImpresion) {
		// se crea un hilo de ejecucion. para poder ejecutar la consola a la vez que
		// puedo presionar los botones.
		hilo = new Thread(this, nombre);
		// se establece cual va a ser el display a imprimir todos los msj.
		this.miArea = areaImpresion;
		pausar = false;
	}

	public Thread gethilo() {
		return hilo;
	}

	// este metodo es el que implementa runnable, el hilo cuando inicie busca este
	// metodo y realiza todo lo que esta dentro.
	public void run() {

		ArrayList<Usuario> usuarios = TierraMedia.getUsuarios();
		ArrayList<Sugerencia> sugerencias = TierraMedia.getSugerencias();

		// Se crea objeto estilo y se definen los estilos a usar mientras
		Estilo estilo = new Estilo();
		SimpleAttributeSet letraPorDefecto = estilo.tipografiaPorDefecto();
		SimpleAttributeSet letraColorRoja = estilo.letraColorRojo();
		SimpleAttributeSet letraColorVerde = estilo.letraColorVerde();
		SimpleAttributeSet alineadoIzq = estilo.alinearIzquierda();
		SimpleAttributeSet alineadoCentro = estilo.alinearCentro();

		miArea.setText("");

		miArea.setParagraphAttributes(letraPorDefecto, false);
		miArea.setParagraphAttributes(alineadoCentro, false);
		try {
			if (usuarios.isEmpty())
				agregarTexto("NO EXISTE USUARIO A QUIEN MOSTRARLE LAS SUGERENCIAS, CARGUE LOS USUARIOS");
			else if (sugerencias.isEmpty())
				agregarTexto("NO EXISTEN SUGERENCIAS PARA MOSTRAR, CARGUE LAS ATRACCIONES Y/O LAS SUGERENCIAS.");
			else {
				agregarTexto(MENSAJE_INICIAL, letraPorDefecto);
				for (Usuario usuario : usuarios) {
					TierraMedia.ordenarSugerencias(usuario.getPreferencia());

					agregarTexto("\n\nBIENVENIDO " + usuario + "\n");

					for (Sugerencia laSugerencia : sugerencias) {
						if (tieneCupo(laSugerencia) && puedeComprar(laSugerencia, usuario)
								&& noSeCompro(laSugerencia)) {
							agregarTexto("Deseas comprar " + laSugerencia + "\n");

							// se pausa al hilo a la espera de presionar alguno de los botones de la lamina.
							pausarHilo();
							// mientras pausar sea true, el hilo espera.
							synchronized (this) {
								while (pausar)
									wait();
							}
							if (respuesta.equalsIgnoreCase("n"))
								agregarTexto("\nNo fue comprado\n\n");
							else if (respuesta.equalsIgnoreCase("s")) {
								laSugerencia.restarCupo();
								usuario.comprar(laSugerencia);
								agregarAtraccionComprada(laSugerencia);
								agregarTexto(
										"\n[Monedas restantes: " + usuario.getDineroDisponible()
												+ "]       [Horas restante: " + usuario.getTiempoDisponible() + "]\n\n",
										letraColorRoja);
							}
							respuesta = "";
						}

					}
					if (!usuario.obtenerDatosDelItinerario().isEmpty()) {
						EntradaSalida.guardarEnArchivo(usuario.obtenerDatosDelItinerario(),
								"Itinerario de " + usuario.getNombre());
						agregarTexto("\nEste es el detalle de tu itinerario\n" + SUBRAYADO + "\n\n", letraPorDefecto);
						// se alinea a la izq para mostrar las compras
						miArea.setParagraphAttributes(alineadoIzq, false);
						agregarTexto(usuario.getItinerario().toString());
						// se vuelve alinear al centro
						miArea.setParagraphAttributes(alineadoCentro, false);
						agregarTexto(
								"Costo total: " + usuario.getItinerario().getCostoDelItinerario() + " monedas."
										+ " ".repeat(17) + "Duraccion total: "
										+ usuario.getItinerario().getDuracionDelItinerario() + " hs.\n",
								letraColorVerde);
						agregarTexto(
								("Tu dinero restante: " + usuario.getDineroDisponible() + " monedas." + " ".repeat(11)
										+ "Tu tiempo restante: " + usuario.getTiempoDisponible() + " hs.\n"),
								letraColorVerde);
					} else
						agregarTexto("\nNO REALIZASTE COMPRAS\n", letraColorRoja);

					agregarTexto(SEPARADOR_USUARIOS);
					atraccionesTemp.clear();
				}
				agregarTexto(MENSAJE_FINAL);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Pausar el hilo
	public synchronized void pausarHilo() {
		pausar = true;
	}

	// Reanudar el hilo
	public synchronized void reanudarHilo() {
		pausar = false;
		// notifica al hilo que esta en espera "wait();" que puede seguir su ejecucion.
		notify();
	}

	public static boolean puedeComprar(Sugerencia laSugerencia, Usuario usuario) {
		return usuario.getDineroDisponible() >= laSugerencia.getPrecio()
				&& usuario.getTiempoDisponible() >= laSugerencia.getDuracion();
	}

	public static boolean tieneCupo(Sugerencia laSugerencia) {
		return laSugerencia.getCupo() > 0;
	}

	public static boolean noSeCompro(Sugerencia laSugerencia) {
		return laSugerencia.noEstaIncluidaEn(atraccionesTemp);
	}

	public static void agregarAtraccionComprada(Sugerencia sugerencia) {
		if (sugerencia.esPromocion()) {
			Promocion miPromo = (Promocion) sugerencia;
			atraccionesTemp.addAll(miPromo.getAtracciones());
		} else {
			atraccionesTemp.add((Atraccion) sugerencia);
		}
	}

	// metodo necesario para que cuando se presione un boton la respuesta cambia a
	// "s" o "n"
	public void setEntrada(String string) {
		this.respuesta = string;
	}

	// metodo necesario para que mi display "miArea" vaya imprimiendo el texto que
	// le mando.
	public void agregarTexto(String texto, SimpleAttributeSet miEstilo) {

		// numero entero que marca la posicion final de lo que contiene miArea.
		int longitudDocumento = miArea.getDocument().getLength();

		try {
			// pide el contenido de miArea y a eso le agrega el texto con mi estilo
			miArea.getDocument().insertString(longitudDocumento, texto, miEstilo);

			// mantiene el posicionamiento de miArea siempre en el final
			miArea.setCaretPosition(miArea.getDocument().getLength());

		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public void agregarTexto(String texto) {

		int longitudDocumento = miArea.getDocument().getLength();

		try {

			miArea.getDocument().insertString(longitudDocumento, texto, null);

			// mantiene el posicionamiento de miArea siempre en el final
			miArea.setCaretPosition(miArea.getDocument().getLength());

		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
