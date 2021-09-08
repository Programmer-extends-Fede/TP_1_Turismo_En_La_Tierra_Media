package tierraMedia;

import java.util.ArrayList;

import atraccion.Atraccion;
import entradaSalida.EntradaSalida;
import promocion.Promocion;
import promocion.PromocionAPorB;
import promocion.PromocionAbsoluta;
import promocion.PromocionPorcentual;
import sugerencia.Sugerencia;
import tipo.Tipo;
import usuario.Usuario;

public abstract class TierraMedia {
	private static ArrayList<Usuario> usuarios;
	private static ArrayList<Atraccion> atracciones;
	private static ArrayList<Promocion> promociones;
	private static ArrayList<Sugerencia> sugerencias;

	public static void construirUsuarios() {
		usuarios = new ArrayList<Usuario>();
		ArrayList<String> misDatos = EntradaSalida.cargarArchivoDe("Entrada/Usuarios.csv");
		for (String entrada : misDatos) {
			String[] infoUsuario = entrada.split(";");
			String nombre = infoUsuario[0];
			int dineroDisponible = Integer.parseInt(infoUsuario[1]);
			double tiempoDisponible = Double.parseDouble(infoUsuario[2]);
			Tipo preferencia = Tipo.valueOf(infoUsuario[3].toUpperCase());
			Usuario usuario = new Usuario(nombre, dineroDisponible, tiempoDisponible, preferencia);
			usuarios.add(usuario);
		}
	}

	public static void construirAtracciones() {
		atracciones = new ArrayList<Atraccion>();
		ArrayList<String> misDatos = EntradaSalida.cargarArchivoDe("Entrada/Atracciones.csv");
		for (String entrada : misDatos) {
			String[] datosAtracciones = entrada.split(";");
			String nombre = datosAtracciones[0];
			int precio = Integer.parseInt(datosAtracciones[1]);
			double duracion = Double.parseDouble(datosAtracciones[2]);
			int cupo = Integer.parseInt(datosAtracciones[3]);
			Tipo tipo = Tipo.valueOf(datosAtracciones[4].toUpperCase());
			Atraccion atraccion = new Atraccion(nombre, precio, duracion, cupo, tipo);
			atracciones.add(atraccion);
		}
	}

	public static void construirPromociones() {
		construirAtracciones();
		promociones = new ArrayList<Promocion>();
		ArrayList<String> misDatos = EntradaSalida.cargarArchivoDe("Entrada/Promociones.csv");
		for (String entrada : misDatos) {
			String[] datosPromocion = entrada.split(";");
			String nombre = datosPromocion[0];
			String[] nombreAtracciones = datosPromocion[1].split(",");
			Tipo tipo = Tipo.valueOf(datosPromocion[2].toUpperCase());
			double descuento = Double.parseDouble(datosPromocion[3]);
			String tipoPromo = datosPromocion[4];
			ArrayList<Atraccion> atraccionEnPromocion = new ArrayList<Atraccion>();
			for (int i = 0; i < nombreAtracciones.length; i++) {
				for (Atraccion atraccion : atracciones) {
					if (nombreAtracciones[i].contains(atraccion.getNombre())) {
						atraccionEnPromocion.add(atraccion);
					}

				}
			}
			if (tipoPromo.contains("PromocionPorcentual")) {
				promociones.add(new PromocionPorcentual(nombre, atraccionEnPromocion, tipo, descuento));
			} else if (tipoPromo.contains("PromocionAbsoluta")) {
				promociones
						.add(new PromocionAbsoluta(nombre, atraccionEnPromocion, tipo, (int) descuento));
			} else if (tipoPromo.contains("PromocionAPorB")) {
				promociones.add(new PromocionAPorB(nombre, atraccionEnPromocion, tipo, (int) descuento));
			}
		} 
	}

	public static void construirSugerencias() {
		construirPromociones();
		sugerencias = new ArrayList<Sugerencia>();
		sugerencias.addAll(atracciones);
		sugerencias.addAll(promociones);
	}

	public static ArrayList<Usuario> getUsuarios() {
		construirUsuarios();
		return usuarios;
	}

	public static ArrayList<Sugerencia> getSugerencia() {
		construirSugerencias();
		return sugerencias;
	}

	public static String informacionParaGuardar() {
		String datosAGuardar = null;
		for (Usuario cadaUsuario : usuarios) {
			datosAGuardar = cadaUsuario.toString() + cadaUsuario.getMiItinerario();
		}
		return datosAGuardar;
	}
}
