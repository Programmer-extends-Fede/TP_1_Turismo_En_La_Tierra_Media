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

	@Override
	public String toString() {
		return "Itinerario [sugerenciasDiarias=" + sugerenciasDiarias + ", costoDelItinerario=" + costoDelItinerario
				+ ", duracionDelItinerario=" + duracionDelItinerario + "]";
	}
}
