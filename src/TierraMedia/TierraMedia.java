package TierraMedia;

import java.util.ArrayList;

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

	private static ArrayList<Usuario> usuarios;
	private static ArrayList<Atraccion> atracciones;
	private static ArrayList<Promocion> promociones;
	private static ArrayList<Sugerencia> sugerencia;

	
	private static void construirUsuarios() {
		usuarios = new ArrayList<Usuario>();
		ArrayList<String> misDatos = EntradaSalida.cargarArchivoDe("Entrada/Usuarios.csv");

		for (String misUsuarios : misDatos) {

			String[] datosUsuarios = misUsuarios.split(";");

			String nombre = datosUsuarios[0];
			int dineroDisponible = Integer.parseInt(datosUsuarios[1]);
			double tiempoDisponible = Double.parseDouble(datosUsuarios[2]);
			Tipo preferencia = Tipo.valueOf(datosUsuarios[3].toUpperCase());

			Usuario usuario = new Usuario(nombre, dineroDisponible, tiempoDisponible, preferencia);
			usuarios.add(usuario);

		}

	} 

	private static void construirAtracciones() {
		atracciones = new ArrayList<Atraccion>();
		ArrayList<String> misDatos = EntradaSalida.cargarArchivoDe("Entrada/Atracciones.csv");

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
	}

	private static void construirPromociones() {
		construirAtracciones();
		promociones = new ArrayList<Promocion>();
		ArrayList<String> misDatos = EntradaSalida.cargarArchivoDe("Entrada/Promociones.csv");

		for (String miPromo : misDatos) {

			String[] datosPromo = miPromo.split(";");

			String tipo = datosPromo[0];
			String nombre = datosPromo[1];
			String[] nombreAtracciones = datosPromo[2].split(",");
			double descuento = Double.parseDouble(datosPromo[3]);

			ArrayList<Atraccion> atraccionIncluidas = new ArrayList<Atraccion>();
			for (int i = 0; i < nombreAtracciones.length; i++) {
				for (Atraccion atraccion : atracciones) {
					if (nombreAtracciones[i].equals(atraccion.getNombre())) {
						atraccionIncluidas.add(atraccion);
					}

				}
			}

			if (tipo.equals("Porcentual")) {
				promociones.add(new PromocionPorcentual(nombre, atraccionIncluidas, descuento));
			}

			if (tipo.equals("Absoluta")) {
				promociones.add(new PromocionAbsoluta(nombre, atraccionIncluidas, (int) descuento));
			}

			if (tipo.equals("AxB")) {
				promociones.add(new PromocionAPorB(nombre, atraccionIncluidas, (int) descuento));
			}
		}
	}

	private static void construirSugerencias() {
		construirPromociones();
		sugerencia = new ArrayList<Sugerencia>();
		sugerencia.addAll(atracciones);
		sugerencia.addAll(promociones);

	}

	public static void ordenarSugerencias(ArrayList<Sugerencia> sugerencias, Tipo preferenciaDeUsuario) {
		sugerencias.sort(new Ordenar(preferenciaDeUsuario));
	}

	public ArrayList<Usuario> getUsuarios() {
		construirUsuarios();
		return usuarios;
	}

	public ArrayList<Sugerencia> getSugerencia() {
		construirSugerencias();
		return sugerencia;
	}

}