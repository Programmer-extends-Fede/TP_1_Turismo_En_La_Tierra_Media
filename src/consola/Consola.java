package consola;

import java.util.ArrayList;
import java.util.Scanner;

import TierraMedia.TierraMedia;
import atraccion.Atraccion;
import entradaSalida.EntradaSalida;
import itinerario.Itinerario;
import promocion.Promocion;
import sugerencia.Sugerencia;
import usuario.Usuario;

public class Consola {

	static ArrayList<Atraccion> atraccionesTemp = new ArrayList<Atraccion>();
	static Scanner entrada = new Scanner(System.in);

	static final String ENTRADA_INCORRECTA = "<<<ENTRADA INCORRECTA>>>".indent(50);
	static final String SEPARADOR_USUARIOS = "_".repeat(80).indent(23);
	static final String SUBRAYADO = "-".repeat(45);
	static final String MENSAJE_INICIAL = "\nESTE ES EL SISTEMA DE COMPRAS DE TIERRAMEDIA\n".indent(47)
			+ "\nRECUERDE, PRESIONAR 'S' PARA ACEPTAR LA COMPRA Y 'N' PARA RECHAZARLA".indent(35)
			+ "\nPRESIONE ENTER PARA CONTINUAR".indent(53);
	static final String MENSAJE_FINAL = SUBRAYADO.indent(43)
			+ "MUCHAS GRACIAS, YA NO QUEDAN USUARIOS POR VER".indent(43) + SUBRAYADO.indent(43);

	public static void iniciarInteraccion() {
		ArrayList<Usuario> usuarios = TierraMedia.getUsuarios();
		ArrayList<Sugerencia> sugerencias = TierraMedia.getSugerencias();
		System.out.println(MENSAJE_INICIAL);

		for (Usuario usuario : usuarios) {
			TierraMedia.ordenarSugerencias(usuario.getPreferencia());
			entrada.nextLine();
			System.out.println("BIENVENIDO " + usuario + "\n");

			for (Sugerencia laSugerencia : sugerencias) {
				if (tieneCupo(laSugerencia) && puedeComprar(laSugerencia, usuario) && noSeCompro(laSugerencia))
					ofertar(laSugerencia, usuario);
			}

			if (!usuario.getMiItinerario().getSugerenciasDiarias().isEmpty()) {
				guardarItinerarioDe(usuario, "Itinerario de " + usuario.getNombre());
				System.out.println("\nEste es el detalle de tu itinerario".indent(6) + SUBRAYADO + "\n");
				System.out.println(usuario.getMiItinerario());
				System.out.println(("Tu dinero restante: " + usuario.getDineroDisponible() + " monedas."
						+ " ".repeat(11) + "Tu tiempo restante: " + usuario.getTiempoDisponible() + " hs.").indent(30));
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

	private static void ofertar(Sugerencia laSugerencia, Usuario usuario) {
		System.out.println("Deseas comprar " + laSugerencia);
		String respuesta = "";
		while (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n")) {
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
		}
	}


	public static void guardarItinerarioDe(Usuario usuario, String ruta) {
		Itinerario itinerario = usuario.getMiItinerario();
		ArrayList<String> datosDeItinerario = itinerario.obtenerDatosDeItinerario();
		String dineroDeUsuario = usuario.getDineroDisponible() + " monedas.";
		String tiempoDeUsuario = usuario.getTiempoDisponible() + " hs.";
		datosDeItinerario
				.add("\n\nTu saldo actual es:;" + dineroDeUsuario + ";Tu tiempo restante es de:;" + tiempoDeUsuario);
		EntradaSalida.guardarEnArchivo(datosDeItinerario);

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