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
		ArrayList <String> misDatos= EntradaSalida.cargarArchivoDe("Entrada/Usuarios.csv");
		usuarios= new ArrayList<Usuario>();
		
		int indice = 0;
		
		for(String dato : misDatos) {
			String[] datosUsuarios = misDatos.get(indice).split(";");
			
			String nombre = datosUsuarios[0];
			int dineroDisponible = Integer.parseInt(datosUsuarios[1]);
			double tiempoDisponible= Integer.parseInt(datosUsuarios[2]);
			Tipo preferencia = Tipo.valueOf(datosUsuarios[3].toUpperCase());
			
			Usuario usuario = new Usuario(nombre, dineroDisponible, tiempoDisponible, preferencia);
			usuarios.add(usuario);
			indice++;
		}
	
	}

	public static ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public static void construirAtracciones() {
	
		ArrayList <String> misDatos = EntradaSalida.cargarArchivoDe("Entrada/Atracciones.csv");
		atracciones = new ArrayList <Atraccion>();
		
		int indice = 0;
		
		for(String dato : misDatos) {

			String[] datosAtracciones = misDatos.get(indice).split(";");
			
			String nombre = datosAtracciones[0];
			int precio = Integer.parseInt(datosAtracciones[1]);
			double duracion= Double.parseDouble(datosAtracciones[2]);
			int cupo = Integer.parseInt(datosAtracciones[3]);
			Tipo tipo = Tipo.valueOf(datosAtracciones[4].toUpperCase());
			
			Atraccion atraccion = new Atraccion(nombre, precio, duracion, cupo, tipo);
			atracciones.add(atraccion);
			indice++;
		}
	}

	public static ArrayList<Atraccion> getAtracciones() {
		return atracciones;
	}
	
	public static void construirPromociones() {
		
		ArrayList <String> misDatos = EntradaSalida.cargarArchivoDe("Entrada/Promociones.csv");
		promociones = new ArrayList <Promocion>();
		
		int indice = 0;
		
		for(String dato : misDatos) {

			String[] datosPromociones = misDatos.get(indice).split(";");
			
			String nombre = datosPromociones[0];
			
			String[] atraccionesString = datosPromociones[1].split(",");
			
			ArrayList<Atraccion> atracciones= new ArrayList<Atraccion>();
			
			for (int i = 0; i < atraccionesString.length; i++) {
				atracciones.add(Atraccion.obtenerAtraccionPorNombre(atraccionesString[i]));
			}

			Tipo tipo = Tipo.valueOf(datosPromociones[2].toUpperCase());
			
			if(datosPromociones[4].equals("PromocionAbsoluta")) {
				
				int precioPaquete = Integer.parseInt(datosPromociones[3]);
				PromocionAbsoluta promocionAbsoluta = new PromocionAbsoluta(nombre, atracciones, tipo, precioPaquete);
				promociones.add(promocionAbsoluta);
			}
			
			else if(datosPromociones[4].equals("PromocionPorcentual")) {
				
				double descuento = Double.parseDouble(datosPromociones[3]);
				PromocionPorcentual promocionPorcentual = new PromocionPorcentual(nombre, atracciones, tipo, descuento);
				promociones.add(promocionPorcentual);
			}
			
			else if(datosPromociones[4].equals("PromocionAPorB")) {
				
				int cantPromosACobrar = Integer.parseInt(datosPromociones[3]);
				PromocionAPorB promocionAPorB = new PromocionAPorB(nombre, atracciones, tipo, cantPromosACobrar);
				promociones.add(promocionAPorB);
			}		
			indice++;
		}
	}
	
	public static Atraccion obtenerAtraccionPorNombre(String nombre) {
		
		for (Atraccion atraccion : atracciones) {
			if (atraccion.getNombre().equals(nombre)) {
				return atraccion;
			}
		} return null;
	}

	public static ArrayList<Promocion> getPromociones() {
		return promociones;
	}


public static void construirSugerencias() {
		
		TierraMedia.sugerencias= new ArrayList<Sugerencia>();
		sugerencias.addAll(promociones);
		sugerencias.addAll(atracciones);		
	}
	
	public static ArrayList<Sugerencia> getSugerencias() {
		return sugerencias;
	}
	
	public static String informacionParaGuardar() {
		
		String datosAGuardar= null;
		for(Usuario cadaUsuario : usuarios) {
			datosAGuardar= cadaUsuario.toString() + cadaUsuario.getMiItinerario();
		}
		return datosAGuardar;
	}
}
