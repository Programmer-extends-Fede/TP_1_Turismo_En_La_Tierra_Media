package itinerario;

import java.util.ArrayList;

import sugerencia.Sugerencia;
import usuario.Usuario;

public class Itinerario {

	private ArrayList<Sugerencia> sugerenciasDiarias = new ArrayList<Sugerencia>();
	private int costoDelItinerario = 0;
	private double duracionDelItinerario = 0;
	private Usuario usuario;

	public Itinerario(Usuario usuario) {
		this.usuario = usuario;

	}

	public void agregarLaCompraDe(Sugerencia unaSugerencia) {
		this.sugerenciasDiarias.add(unaSugerencia);
		this.costoDelItinerario += unaSugerencia.getPrecio();
		this.duracionDelItinerario += unaSugerencia.getDuracion();
	}

	public ArrayList<Sugerencia> getSugerenciasDiarias() {
		return sugerenciasDiarias;
	}

	public ArrayList<String> obtenerDatosDeItinerario() {
		ArrayList<String> datosDelItinerario = new ArrayList<String>();

		if (!sugerenciasDiarias.isEmpty()) {

			datosDelItinerario.add(("Usuario: " + usuario.getNombre() + ";Saldo inicial: "
					+ (usuario.getDineroDisponible() + this.costoDelItinerario) + " monedas;Tiempo inicial: "
					+ (usuario.getTiempoDisponible() + this.duracionDelItinerario) + " hs.\n\n").toUpperCase());
			datosDelItinerario.add("ESTE ES EL DETALLE DE TU ITINERARIO\n\n");
			datosDelItinerario.add("Costo de tu Itinerario:;" + this.costoDelItinerario + ";Duracion de tu Itinerario:;"
					+ this.duracionDelItinerario + "\n\n");
			datosDelItinerario.add("\nPromocion / Atraccion Comprada;Tipo;Costo;Duracion\n\n");

			for (Sugerencia sugerencia : sugerenciasDiarias) {
				datosDelItinerario.add(sugerencia.getNombre() + ";" + sugerencia.getTipo().getDescripcion() + ";"
						+ sugerencia.getPrecio() + " monedas." + ";" + sugerencia.getDuracion() + " hs.\n");
			}
			datosDelItinerario.add("Te quedan " + usuario.getDineroDisponible() + " monedas y "
					+ usuario.getTiempoDisponible() + " hs.");
		}
		return datosDelItinerario;
	}

	public int getCostoDelItinerario() {
		return this.costoDelItinerario;
	}

	public double getDuracionDelItinerario() {
		return this.duracionDelItinerario;
	}

	@Override
	public String toString() {
		String sugerenciasDiariasLimpio = sugerenciasDiarias.toString().replace("[", " ").replace(", ", "\n ")
				.replace("]", "\n");
		return sugerenciasDiariasLimpio + ("\nCosto total: " + this.costoDelItinerario
				+ " monedas.                 Duracion total: " + this.duracionDelItinerario + " hs.").indent(30);
	}

}
