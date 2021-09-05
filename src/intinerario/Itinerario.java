package intinerario;

import java.util.ArrayList;

import sugerencia.Sugerencia;

public class Itinerario {
	private ArrayList<Sugerencia> comprasRealizadas = new ArrayList<Sugerencia>();
	
	public void agregarLaCompraDe(Sugerencia sugerencia) {
		comprasRealizadas.add(sugerencia);
	}
	
	public ArrayList<Sugerencia> getComprasRealizadas() {
		return this.comprasRealizadas;
	}

	private int getPrecioTotalDeCompras() {
		int precioTotal = 0;
		for (int i = 0; i < comprasRealizadas.size(); i++) {
			precioTotal += comprasRealizadas.get(i).getPrecio();
		}
		return precioTotal;
	}
	
	private double getTiempoTotalDeCompras() {
		double tiempoTotal = 0;
		for (int i = 0; i < comprasRealizadas.size(); i++) {
			tiempoTotal += comprasRealizadas.get(i).getDuracion();
		}
		return tiempoTotal;
	}
	
	//pensar como fusionar los métodos anteriores que son iguales
	
	@Override
	public String toString() {
		return "Itinerario de las compras realizadas:" + "\n" + comprasRealizadas.toString() + "\n" + "Gasto total: " + getPrecioTotalDeCompras() + ". Duración total: " + getTiempoTotalDeCompras();
	}
}
