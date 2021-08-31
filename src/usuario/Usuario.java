package usuario;

import atraccion.Sugerencia;
import atraccion.Tipo;

public class Usuario {
	private String nombre;
	private int dineroDisponible;
	private double tiempoDisponible;
	private Tipo tipoPreferencia;
	private Itinerario itinerario;

	public Usuario(String nombre, int dineroDisponible, double tiempoDisponible, Tipo tipoPreferencia) {
		this.nombre = nombre;
		this.dineroDisponible = dineroDisponible;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoPreferencia = tipoPreferencia;
	}

	public String getNombre() {
		return nombre;
	}

	public int getDineroDisponible() {
		return dineroDisponible;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public Tipo getTipoPreferencia() {
		return tipoPreferencia;
	}

	public boolean comprar(Sugerencia sugerencia) {
		if (leAlcanzaElDinero(sugerencia) && leAlcanzaElTiempo(sugerencia)) {
			this.dineroDisponible -= sugerencia.getPrecio();
			this.tiempoDisponible -= sugerencia.getDuracion();
			this.itinerario.agregarCompra(sugerencia);
			return true;
		}
		return false;
	}

	private boolean leAlcanzaElTiempo(Sugerencia sugerencia) {
		return getTiempoDisponible() >= sugerencia.getDuracion();
	}

	private boolean leAlcanzaElDinero(Sugerencia sugerencia) {
		return getDineroDisponible() >= sugerencia.getPrecio();
	}

}
