package tierraMedia;

import java.util.ArrayList;
import java.util.Arrays;

import atraccion.Atraccion;
import entradaSalida.EntradaSalida;
import ordenar.Ordenar;
import promocion.Promocion;
import promocion.PromocionAPorB;
import promocion.PromocionAbsoluta;
import promocion.PromocionPorcentual;
import sugerencia.Sugerencia;
import tipo.Tipo;
import usuario.Usuario;

public abstract class TierraMedia {

	private static ArrayList<Atraccion> atracciones;
	private static ArrayList<Usuario> usuarios;
	private static ArrayList<Promocion> promociones;
	private static ArrayList<Sugerencia> sugerencias;

	public static boolean construirUsuarios() {
		ArrayList<String> misDatos = EntradaSalida.cargarArchivoDe("Entrada/Usuarios.csv");
		usuarios = new ArrayList<Usuario>();

		for (String dato : misDatos) {
			String[] datoRecortado = dato.split(";");
			usuarios.add(new Usuario(datoRecortado[0], Integer.parseInt(datoRecortado[1]),
					Double.parseDouble(datoRecortado[2]), Tipo.valueOf(datoRecortado[3].toUpperCase())));
		}
		return !usuarios.isEmpty();
	}

	public static boolean construirAtracciones() {
		ArrayList<String> misDatos = EntradaSalida.cargarArchivoDe("Entrada/Atracciones.csv");
		atracciones = new ArrayList<Atraccion>();

		for (String dato : misDatos) {
			String[] datoRecortado = dato.split(";");
			atracciones.add(new Atraccion(datoRecortado[0], Integer.parseInt(datoRecortado[1]),
					Double.parseDouble(datoRecortado[2]), Integer.parseInt(datoRecortado[3]),
					Tipo.valueOf(datoRecortado[4].toUpperCase())));
		}
		return !atracciones.isEmpty();
	}

	public static boolean construirPromociones() {
		if (!atracciones.isEmpty()) {
			ArrayList<String> misDatos = EntradaSalida.cargarArchivoDe("Entrada/Promociones.csv");
			promociones = new ArrayList<Promocion>();

			for (String dato : misDatos) {
				String[] datoRecortado = dato.split(";");
				ArrayList<String> nombresDeAtracciones = new ArrayList<String>(
						Arrays.asList(datoRecortado[1].split(",")));
				ArrayList<Atraccion> atraccionesDePromo = new ArrayList<Atraccion>();

				for (Atraccion atraccion : atracciones) {
					if (nombresDeAtracciones.contains(atraccion.getNombre()))
						atraccionesDePromo.add(atraccion);
				}

				switch (datoRecortado[3]) {
				case "Porcentual":
					promociones.add(new PromocionPorcentual(datoRecortado[0], atraccionesDePromo,
							Double.parseDouble(datoRecortado[2])));
					break;
				case "Absoluta":
					promociones.add(new PromocionAbsoluta(datoRecortado[0], atraccionesDePromo,
							Integer.parseInt(datoRecortado[2])));
					break;
				case "APorB":
					promociones.add(new PromocionAPorB(datoRecortado[0], atraccionesDePromo,
							Integer.parseInt(datoRecortado[2])));
				}
			}
		}
		return !promociones.isEmpty();
	}

	public static boolean construirSugerencias() {
		if (!promociones.isEmpty() && !atracciones.isEmpty()) {
			sugerencias = new ArrayList<Sugerencia>();
			sugerencias.addAll(promociones);
			sugerencias.addAll(atracciones);
		}
		return !sugerencias.isEmpty();
	}

	public static ArrayList<Sugerencia> getSugerencias() {
		return sugerencias;
	}

	public static ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public static void ordenar(Tipo preferenciaDeUsuario) {
		sugerencias.sort(new Ordenar(preferenciaDeUsuario));
	}
}
