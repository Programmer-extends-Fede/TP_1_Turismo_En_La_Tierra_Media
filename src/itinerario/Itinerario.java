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

	/*@Override
	public String toString() {
		return sugerenciasDiarias.toString().replace("[", " ").replace(".,", ".\n").replace("]", "\n")
				+ " \n       Costo total: " + this.costoDelItinerario + " monedas.                  Duracion total: "
				+ this.duracionDelItinerario + " hs.";
	}*/

	@Override
	public String toString() {
		ArrayList<String> detalleDeCompras = new ArrayList<String>();
		for (Sugerencia sugerenciaComprada : sugerenciasDiarias) {
			detalleDeCompras.add(sugerenciaComprada.toString());
		}
		return String.join("\n\n", detalleDeCompras).indent(2) + "\n\n" + ("Costo total: " + this.costoDelItinerario
				+ " monedas.                 Duracion total: " + this.duracionDelItinerario + " hs.").indent(35);
	}
}
