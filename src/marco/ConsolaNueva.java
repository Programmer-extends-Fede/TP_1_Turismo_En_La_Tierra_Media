package marco;

import java.util.ArrayList;

import javax.swing.JTextArea;

import atraccion.Atraccion;
import promocion.Promocion;
import sugerencia.Sugerencia;
import tierraMediaConsolaYEntradaSalida.EntradaSalida;
import tierraMediaConsolaYEntradaSalida.TierraMedia;
import usuarioEItinerario.Usuario;

public class ConsolaNueva implements Runnable {
	static ArrayList<Atraccion> atraccionesTemp = new ArrayList<Atraccion>();
	static final String ENTRADA_INCORRECTA = "<<<ENTRADA INCORRECTA>>>".indent(50);
	static final String SEPARADOR_USUARIOS = "_".repeat(80).indent(23);
	static final String SUBRAYADO = "-".repeat(45);
	static final String MENSAJE_INICIAL = "\nESTE ES EL SISTEMA DE COMPRAS DE TIERRAMEDIA\n".indent(47)
			+ "\nRECORDA, PRESIONA 'COMPRAR' PARA ACEPTAR LA COMPRA Y 'NO COMPRAR' PARA RECHAZARLA".indent(30);
	static final String MENSAJE_FINAL = SUBRAYADO.indent(43)
			+ "MUCHAS GRACIAS, YA NO QUEDAN USUARIOS POR VER".indent(43) + SUBRAYADO.indent(43);

	Thread hilo;
	boolean suspender; // Suspende el hilo cuando es true
	JTextArea miArea;
	String respuesta = "";

	public ConsolaNueva(String nombre, JTextArea areaImpresion) {
		hilo = new Thread(this, nombre);
		this.miArea = areaImpresion;
		suspender = false;
	}

	public void setArea(JTextArea miArea) {
		this.miArea = miArea;
	}

	public Thread gethilo() {
		return hilo;
	}

	public void run() {

		ArrayList<Usuario> usuarios = TierraMedia.getUsuarios();
		ArrayList<Sugerencia> sugerencias = TierraMedia.getSugerencias();

		if (usuarios.isEmpty())
			miArea.append("NO EXISTE USUARIO A QUIEN MOSTRARLE LAS SUGERENCIAS, CARGUE LOS USUARIOS");
		else if (sugerencias.isEmpty())
			miArea.append("NO EXISTEN SUGERENCIAS PARA MOSTRAR, CARGUE LAS ATRACCIONES Y/O LAS SUGERENCIAS.");
		else {
			miArea.append(MENSAJE_INICIAL);
			for (Usuario usuario : usuarios) {
				TierraMedia.ordenarSugerencias(usuario.getPreferencia());

				miArea.append("\n\nBIENVENIDO " + usuario + "\n");
				miArea.update(miArea.getGraphics());

				for (Sugerencia laSugerencia : sugerencias) {
					if (tieneCupo(laSugerencia) && puedeComprar(laSugerencia, usuario) && noSeCompro(laSugerencia)) {
						miArea.append("Deseas comprar " + laSugerencia);
						miArea.update(miArea.getGraphics());
						suspenderhilo();
						synchronized (this) {
							while (suspender) {
								try {
									wait();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}

						if (respuesta.equalsIgnoreCase("n"))
							miArea.append("\n");
						else if (respuesta.equalsIgnoreCase("s")) {
							laSugerencia.restarCupo();
							usuario.comprar(laSugerencia);
							agregarAtraccionComprada(laSugerencia);
							miArea.append(("[Monedas restantes: " + usuario.getDineroDisponible()
									+ "]       [Horas restante: " + usuario.getTiempoDisponible() + "]").indent(35));
						}
						respuesta = "";
					}

				}
				if (!usuario.obtenerDatosDelItinerario().isEmpty()) {
					EntradaSalida.guardarEnArchivo(usuario.obtenerDatosDelItinerario(),
							"Itinerario de " + usuario.getNombre());
					miArea.append("\nEste es el detalle de tu itinerario".indent(6) + SUBRAYADO + "\n");
					miArea.append(usuario.getItinerario().toString());
					miArea.append(("Tu dinero restante: " + usuario.getDineroDisponible() + " monedas." + " ".repeat(11)
							+ "Tu tiempo restante: " + usuario.getTiempoDisponible() + " hs.").indent(30));
				} else
					miArea.append("\nNO REALIZASTE COMPRAS");

				miArea.append(SEPARADOR_USUARIOS);
				if (!usuarios.get(usuarios.size() - 1).equals(usuario))
					miArea.append("PRESIONA ENTER PARA MOSTRAR EL SIGUIENTE USUARIO".indent(40));
				atraccionesTemp.clear();
			}
			miArea.append(MENSAJE_FINAL);

		}
	}

	// Suspender el hilo
	synchronized void suspenderhilo() {
		suspender = true;
	}

	// Renaudar el hilo
	synchronized void reanudarhilo() {
		suspender = false;
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

	public void setEntrada(String string) {
		this.respuesta = string;
	}
}
