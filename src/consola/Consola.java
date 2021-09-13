package consola;

import java.util.ArrayList;
import java.util.Scanner;

import atraccion.Atraccion;
import entradaSalida.EntradaSalida;
import promocion.Promocion;
import sugerencia.Sugerencia;
import tierraMedia.tierraMedia;
import usuario.Usuario;

public class Consola {

	private static ArrayList<Atraccion> atraccionesTemp = new ArrayList<Atraccion>();
	private static Scanner entrada = new Scanner(System.in);

	private static final String ENTRADA_INCORRECTA = "<<<ENTRADA INCORRECTA>>>".indent(50);
	private static final String SEPARADOR_USUARIOS = "_".repeat(80).indent(23);
	private static final String SUBRAYADO = "-".repeat(45);
	private static final String MENSAJE_INICIAL = "\nESTE ES EL SISTEMA DE COMPRAS DE TIERRAMEDIA\n".indent(47)
			+ "\nRECUERDE, PRESIONAR 'S' PARA ACEPTAR LA COMPRA Y 'N' PARA RECHAZARLA".indent(35)
			+ "\nPRESIONE ENTER PARA CONTINUAR".indent(53);
	private static final String MENSAJE_FINAL = SUBRAYADO.indent(43)
			+ "MUCHAS GRACIAS, YA NO QUEDAN USUARIOS POR VER".indent(43) + SUBRAYADO.indent(43);

	public static void iniciarInteraccion() {
		ArrayList<Usuario> usuarios = tierraMedia.getUsuarios();
		ArrayList<Sugerencia> sugerencias = tierraMedia.getSugerencias();

		if (usuarios.isEmpty())
			System.out.println("NO EXISTE USUARIO A QUIEN MOSTRARLE LAS SUGERENCIAS, CARGUE LOS USUARIOS");
		else if (sugerencias.isEmpty())
			System.out.println("NO EXISTEN SUGERENCIAS PARA MOSTRAR, CARGUE LAS ATRACCIONES Y/O LAS SUGERENCIAS.");
		else {
			System.out.println(MENSAJE_INICIAL);

			for (Usuario usuario : usuarios) {
				tierraMedia.ordenarSugerencias(usuario.getPreferencia());
				entrada.nextLine();
				System.out.println("BIENVENIDO " + usuario + "\n");

				for (Sugerencia laSugerencia : sugerencias) {
					if (tieneCupo(laSugerencia) && puedeComprar(laSugerencia, usuario) && noSeCompro(laSugerencia))
						ofertar(laSugerencia, usuario);
				}

				if (!usuario.getMiItinerario().getSugerenciasDiarias().isEmpty()) {

					guardarItinerarioDe(usuario.getMiItinerario().obtenerDatosDeItinerario(),
							"Itinerario de " + usuario.getNombre());

					System.out.println("\nEste es el detalle de tu itinerario".indent(6) + SUBRAYADO + "\n");
					System.out.println(usuario.getMiItinerario());
					System.out.println(
							("Tu dinero restante: " + usuario.getDineroDisponible() + " monedas." + " ".repeat(11)
									+ "Tu tiempo restante: " + usuario.getTiempoDisponible() + " hs.").indent(30));
				} else
					System.out.println("\nNO REALIZASTE COMPRAS");

				System.out.println(SEPARADOR_USUARIOS);
				if (!usuarios.get(usuarios.size() - 1).equals(usuario))
					System.out.print("PRESIONA ENTER PARA MOSTRAR EL SIGUIENTE USUARIO".indent(40));
				atraccionesTemp.clear();
			}
			System.out.println(MENSAJE_FINAL);
			entrada.close();
		}
	}

	private static void ofertar(Sugerencia laSugerencia, Usuario usuario) {
		System.out.println("Deseas comprar " + laSugerencia);
		String respuesta = "";
		do {
			respuesta = entrada.nextLine();

			if (respuesta.equalsIgnoreCase("n"))
				System.out.println();
			else if (respuesta.equalsIgnoreCase("s")) {
				laSugerencia.restarCupo();
				usuario.comprar(laSugerencia);
				agregarAtraccionComprada(laSugerencia);
				System.out.println(("[Monedas restantes: " + usuario.getDineroDisponible() + "]       [Horas restante: "
						+ usuario.getTiempoDisponible() + "]").indent(35));
			} else
				System.out.println(ENTRADA_INCORRECTA);
		} while (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n"));
	}

	public static void guardarItinerarioDe(ArrayList<String> datosDeItinerario, String ruta) {
		EntradaSalida.guardarEnArchivo(datosDeItinerario, ruta);
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
}