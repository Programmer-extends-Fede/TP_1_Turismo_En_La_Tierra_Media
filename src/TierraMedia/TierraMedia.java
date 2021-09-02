package TierraMedia;

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

	private static void construirUsuarios() {
		ArrayList<String> misDatos = EntradaSalida.cargarArchivoDe("Entrada/Usuarios.csv");
		usuarios = new ArrayList<Usuario>();

		for (String dato : misDatos) {
			String[] datoRecortado = dato.split(";");
			usuarios.add(new Usuario(datoRecortado[0], Integer.parseInt(datoRecortado[1]),
					Double.parseDouble(datoRecortado[2]), Tipo.valueOf(datoRecortado[3].toUpperCase())));
		}
	}

	private static void construirAtracciones() {
		ArrayList<String> misDatos = EntradaSalida.cargarArchivoDe("Entrada/Atracciones.csv");
		atracciones = new ArrayList<Atraccion>();

		for (String dato : misDatos) {
			String[] datoRecortado = dato.split(";");
			atracciones.add(new Atraccion(datoRecortado[0], Integer.parseInt(datoRecortado[1]),
					Double.parseDouble(datoRecortado[2]), Integer.parseInt(datoRecortado[3]),
					Tipo.valueOf(datoRecortado[4].toUpperCase())));
		}
	}

	private static void construirPromociones() {
		construirAtracciones();
		ArrayList<String> misDatos = EntradaSalida.cargarArchivoDe("Entrada/Promociones.csv");
		promociones = new ArrayList<Promocion>();

		for (String dato : misDatos) {
			String[] datoRecortado = dato.split(";");
			ArrayList<String> nombresDeAtracciones = new ArrayList<String>(Arrays.asList(datoRecortado[1].split(",")));
			ArrayList<Atraccion> atraccionesDePromo = new ArrayList<Atraccion>();

			for (Atraccion atraccion : atracciones) {
				if (nombresDeAtracciones.contains(atraccion.getNombre()))
					atraccionesDePromo.add(atraccion);
			}

			if (datoRecortado[4].equals("1"))
				promociones.add(new PromocionPorcentual(datoRecortado[0], atraccionesDePromo,
						Tipo.valueOf(datoRecortado[2].toUpperCase()), Double.parseDouble(datoRecortado[3])));
			else if (datoRecortado[4].equals("2"))
				promociones.add(new PromocionAbsoluta(datoRecortado[0], atraccionesDePromo,
						Tipo.valueOf(datoRecortado[2].toUpperCase()), Integer.parseInt(datoRecortado[3])));
			else
				promociones.add(new PromocionAPorB(datoRecortado[0], atraccionesDePromo,
						Tipo.valueOf(datoRecortado[2].toUpperCase()), Integer.parseInt(datoRecortado[3])));
		}
	}

	private static void construirSugerencias() {
		construirPromociones();
		sugerencias = new ArrayList<Sugerencia>();
		sugerencias.addAll(promociones);
		sugerencias.addAll(atracciones);
	}

	public static ArrayList<Sugerencia> getSugerencias() {
		construirSugerencias();
		return sugerencias;
	}

	public static ArrayList<Usuario> getUsuarios() {
		construirUsuarios();
		return usuarios;
	}

	public static void ordenar(ArrayList<Sugerencia> sugerencias, Tipo preferenciaDeUsuario) {
		Ordenar.setUsuarioTipo(preferenciaDeUsuario);
		sugerencias.sort(new Ordenar());
	}
}
