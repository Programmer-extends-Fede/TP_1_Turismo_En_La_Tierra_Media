package itinerario;

import java.util.ArrayList;

import sugerencia.Sugerencia;

public class Itinerario {

	private ArrayList<Sugerencia> sugerenciasDiarias = new ArrayList<Sugerencia>();
	private int costoDelItinerario = 0;
	private double duracionDelItinerario = 0;

	public void agregarLaCompraDe(Sugerencia unaSugerencia) {
		sugerenciasDiarias.add(unaSugerencia);
		costoDelItinerario += unaSugerencia.getPrecio();
		duracionDelItinerario += unaSugerencia.getDuracion();
	}

	public ArrayList<Sugerencia> getSugerenciasDiarias() {
		return sugerenciasDiarias;
	}

	public int getCostoDelItinerario() {
		return costoDelItinerario;
	}

	public double getDuracionDelItinerario() {
		return duracionDelItinerario;
	}

	private void imprimirSugerenciasCompradas() {
		for (Sugerencia sugerencia : sugerenciasDiarias) {
			System.out.println(sugerencia);
		}
	}

	@Override
	public String toString() {
		imprimirSugerenciasCompradas();
		return  " \n Duracion total: " + this.duracionDelItinerario + " hs.                    Costo total: "
				+ this.costoDelItinerario + " monedas.";
	}
}
