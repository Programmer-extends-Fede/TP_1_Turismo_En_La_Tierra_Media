package entradaSalidaTierraMediaConsola;

import java.util.ArrayList;
import java.util.Arrays;

import ordenarSugerencias.OrdenarSugerencias;
import sugerenciaPromocionAtraccion.Atraccion;
import sugerenciaPromocionAtraccion.Promocion;
import sugerenciaPromocionAtraccion.PromocionAPorB;
import sugerenciaPromocionAtraccion.PromocionAbsoluta;
import sugerenciaPromocionAtraccion.PromocionPorcentual;
import sugerenciaPromocionAtraccion.Sugerencia;
import tipo.Tipo;
import usuarioItinerario.Usuario;

public abstract class TierraMedia {

	private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private static ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
	private static ArrayList<Promocion> promociones = new ArrayList<Promocion>();
	private static ArrayList<Sugerencia> sugerencias = new ArrayList<Sugerencia>();

	public static boolean construirUsuarios() {
		ArrayList<String> misDatos = EntradaSalida.cargarArchivoDe("Usuarios.csv");

		for (String misUsuarios : misDatos) {
			String[] datosUsuarios = misUsuarios.split(";");
			String nombre = datosUsuarios[0];
			int dineroDisponible = Integer.parseInt(datosUsuarios[1]);
			double tiempoDisponible = Double.parseDouble(datosUsuarios[2]);
			Tipo preferencia = Tipo.valueOf(datosUsuarios[3].toUpperCase());
			Usuario usuario = new Usuario(nombre, dineroDisponible, tiempoDisponible, preferencia);
			usuarios.add(usuario);

		}
		return !usuarios.isEmpty();
	}

	public static boolean construirAtracciones() {
		ArrayList<String> misDatos = EntradaSalida.cargarArchivoDe("Atracciones.csv");

		for (String misAtracciones : misDatos) {
			String[] datosAtracciones = misAtracciones.split(";");
			String nombre = datosAtracciones[0];
			int precio = Integer.parseInt(datosAtracciones[1]);
			double duracion = Double.parseDouble(datosAtracciones[2]);
			int cupo = Integer.parseInt(datosAtracciones[3]);
			Tipo tipo = Tipo.valueOf(datosAtracciones[4].toUpperCase());
			Atraccion atraccion = new Atraccion(nombre, precio, duracion, cupo, tipo);
			atracciones.add(atraccion);
		}
		return !atracciones.isEmpty();
	}

	public static boolean construirPromociones() {
		if (!atracciones.isEmpty()) {
			ArrayList<String> misDatos = EntradaSalida.cargarArchivoDe("Promociones.csv");

			for (String miPromo : misDatos) {
				String[] datosPromo = miPromo.split(";");
				String tipo = datosPromo[0];
				String nombre = datosPromo[1];
				ArrayList<String> nombreAtracciones = new ArrayList<String>(Arrays.asList(datosPromo[2].split(",")));
				double descuento = Double.parseDouble(datosPromo[3]);
				ArrayList<Atraccion> atraccionIncluidas = new ArrayList<Atraccion>();

				for (Atraccion atraccion : atracciones) {
					if (nombreAtracciones.contains(atraccion.getNombre()))
						atraccionIncluidas.add(atraccion);
				}

				if (tipo.equals("Porcentual")) {
					promociones.add(new PromocionPorcentual(nombre, atraccionIncluidas, descuento));
				} else if (tipo.equals("Absoluta")) {
					promociones.add(new PromocionAbsoluta(nombre, atraccionIncluidas, (int) descuento));
				} else {
					promociones.add(new PromocionAPorB(nombre, atraccionIncluidas, (int) descuento));
				}
			}
		}
		return !promociones.isEmpty();
	}

	public static boolean construirSugerencias() {
		if (!atracciones.isEmpty()) {
			sugerencias.addAll(atracciones);
			sugerencias.addAll(promociones);
		}
		return !sugerencias.isEmpty();
	}

	public static void ordenarSugerencias(Tipo preferenciaDeUsuario) {
		sugerencias.sort(new OrdenarSugerencias(preferenciaDeUsuario));
	}

	public static ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public static ArrayList<Atraccion> getAtracciones() {
		return atracciones;
	}

	public static ArrayList<Promocion> getPromociones() {
		return promociones;
	}

	public static ArrayList<Sugerencia> getSugerencias() {
		return sugerencias;
	}

	public static void borrarDatos() {
		usuarios.clear();
		atracciones.clear();
		promociones.clear();
		sugerencias.clear();
	}
}
