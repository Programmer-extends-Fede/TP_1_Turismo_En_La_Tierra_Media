package consola;

import java.util.ArrayList;
import java.util.Scanner;

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
			System.out.println("BIENVENIDO " + usuario.getNombre().toUpperCase() + "\n");
			for (Sugerencia sugerencia : sugerencias) {
				if (sugerencia.getCupo() > 0 && puedeComprar(sugerencia, usuario)
						&& sugerencia.noEstaIncluidaEn(atraccionesTemp)) {

					System.out.println("Deseas comprar " + sugerencia);
					String respuesta = "";
					while (!respuesta.equals("s") && !respuesta.equals("n")) {
						respuesta = entrada.nextLine();

						if (!respuesta.equals("s") && !respuesta.equals("n"))
							System.out.println("entrada incorrecta: ingrese s/n");

						if (respuesta.equals("s")) {
							sugerencia.vender();
							usuario.comprar(sugerencia);
							agregarAtraccionTemp(sugerencia);
						}
					}
				}
			}
			System.out.println(usuario.getMiItinerario());
			atraccionesTemp.clear();
		}
		System.out.println("Muchas gracias, ya no quedan mas usuarios por ver.");
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