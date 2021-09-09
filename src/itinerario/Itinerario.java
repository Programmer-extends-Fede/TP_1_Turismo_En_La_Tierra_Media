package itinerario;

import java.util.ArrayList;

import sugerencia.Sugerencia;

public class Itinerario {

	private ArrayList<Sugerencia> sugerenciasDiarias = new ArrayList<Sugerencia>();
	private int costoDelItinerario = 0;
	private double duracionDelItinerario = 0;

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
			datosDelItinerario.add("ESTE ES EL DETALLE DE TU ITINERARIO\n");
			datosDelItinerario.add("Costo de tu Itinerario:;" + this.costoDelItinerario + ";Duracion de tu Itinerario:;"
					+ this.duracionDelItinerario + "\n");
			datosDelItinerario.add("\nPromocion / Atraccion Comprada;Tipo;Costo;Duracion\n");

			for (Sugerencia sugerencia : sugerenciasDiarias) {
				datosDelItinerario.add(sugerencia.getNombre() + ";" + sugerencia.getTipo().getDescripcion() + ";"
						+ sugerencia.getPrecio() + " monedas." + ";" + sugerencia.getDuracion() + " hs.");
			}
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
