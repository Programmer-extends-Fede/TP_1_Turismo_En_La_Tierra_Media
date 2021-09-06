package itinerario;

import java.util.Arrays;

import sugerencia.Sugerencia;

public class Itinerario {
	private Sugerencia[] comprasRealizadas = new Sugerencia[10];
	private int posicionACargar = 0;
	
	public void agregarLaCompraDe(Sugerencia sugerencia) {
		comprasRealizadas[posicionACargar] = sugerencia;
		posicionACargar++;
		//comprasRealizadas[posicionACargar++] = sugerencia;
	}
	

	private int getPrecioTotalDeCompras() {
		int precioTotal = 0;
		for (int i = 0; i < comprasRealizadas.length; i++) {
			if (comprasRealizadas[i] != null)
				precioTotal += comprasRealizadas[i].getPrecio();
		}
		return precioTotal;
	}
	
	private double getTiempoTotalDeCompras() {
		double tiempoTotal = 0;
		for (int i = 0; i < comprasRealizadas.length; i++) {
			if (comprasRealizadas[i] != null)
				tiempoTotal += comprasRealizadas[i].getDuracion();
		}
		return tiempoTotal;
	}
	

	@Override
	public String toString() {
		return "Itinerario de las compras realizadas:" + "\n" + Arrays.toString(comprasRealizadas) + "\n" + "Gasto total: " + getPrecioTotalDeCompras() + ". Duración total: " + getTiempoTotalDeCompras();
	}
	

}
