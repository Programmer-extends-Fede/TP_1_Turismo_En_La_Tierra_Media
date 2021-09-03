package consola;

import java.util.ArrayList;
import java.util.Scanner;

import TierraMedia.TierraMedia;
import atraccion.Atraccion;
import promocion.Promocion;
import sugerencia.Sugerencia;
import usuario.Usuario;

public class Consola {

	static ArrayList<Atraccion> atraccionesTemp = new ArrayList<Atraccion>();

	public static void iniciarInteraccion() {
		ArrayList<Usuario> usuarios = TierraMedia.getUsuarios();
		ArrayList<Sugerencia> sugerencias = TierraMedia.getSugerencias();
		Scanner entrada = new Scanner(System.in);

		for (Usuario usuario : usuarios) {
			TierraMedia.ordenar(sugerencias, usuario.getPreferencia());
			System.out.println(
					"------------------------------------------------------------------------------------------------------");
			System.out.println("\n BIENVENIDO " + usuario.getNombre().toUpperCase());
			for (Sugerencia sugerencia : sugerencias) {
				if (sugerencia.getCupo() > 0 && puedeComprar(sugerencia, usuario)
						&& sugerencia.noEstaIncluidaEn(atraccionesTemp)) {
					System.out.println("\nDeseas comprar " + sugerencia);
					String respuesta = "";
					
					while (!respuesta.equals("s") && !respuesta.equals("n")) {
						respuesta = entrada.nextLine();

						if (!respuesta.equals("s") && !respuesta.equals("n"))
							System.out.println("entrada incorrecta: ingrese s/n");

						if (respuesta.equals("s")) {
							sugerencia.vender(); // cambiar nombre a restar cupo?
							usuario.comprar(sugerencia);
							agregarAtraccionTemp(sugerencia);
							System.out.println("    [Monedas restantes: " + usuario.getDineroDisponible()
									+ "]       [Horas restante: " + usuario.getTiempoDisponible() + "]\n");
						}
					}
				}
			}

			if (usuario.getMiItinerario().getSugerenciasDiarias().isEmpty())
				System.out.println("\nNo realizaste compras.");
			else {
				System.out.println("\nTu itinerario es el siguiente:\n");
				System.out.println(usuario.getMiItinerario());
			}
			atraccionesTemp.clear();
		}
		System.out.println("\n \n---------------------------------------------");
		System.out.println("MUCHAS GRACIAS, YA NO QUEDAN USUARIOS POR VER");
		System.out.println("---------------------------------------------");
		entrada.close();
	}

	private static boolean puedeComprar(Sugerencia sugerencia, Usuario usuario) {
		return usuario.getDineroDisponible() >= sugerencia.getPrecio()
				&& usuario.getTiempoDisponible() >= sugerencia.getDuracion();
	}

	private static void agregarAtraccionTemp(Sugerencia sugerencia) {
		if (sugerencia.esPromocion()) {
			Promocion miPromo = (Promocion) sugerencia;
			atraccionesTemp.addAll(miPromo.getAtracciones());
		} else {
			atraccionesTemp.add((Atraccion) sugerencia);
		}
	}
}