package usuario;

import itinerario.Itinerario;
import sugerencia.Sugerencia;
import tipo.Tipo;

public class Usuario {
	private String nombre;
	private int dineroDisponible;
	private double tiempoDisponible;
	private Tipo preferencia;
	private Itinerario miItinerario;

	public Usuario(String nombre, int dineroDisponible, double tiempoDisponible, Tipo preferencia) {
		this.nombre = nombre;
		this.dineroDisponible = dineroDisponible;
		this.tiempoDisponible = tiempoDisponible;
		this.preferencia = preferencia;
		this.miItinerario = new Itinerario();
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getDineroDisponible() {
		return this.dineroDisponible;
	}

	public double getTiempoDisponible() {
		return this.tiempoDisponible;
	}

	public Tipo getPreferencia() {
		return this.preferencia;
	}

	public void comprar(Sugerencia unaSugerencia) {
		this.dineroDisponible -= unaSugerencia.getPrecio();
		this.tiempoDisponible -= unaSugerencia.getDuracion();
		this.miItinerario.agregarLaCompraDe(unaSugerencia);
	}

	public Itinerario getMiItinerario() {
		return miItinerario;
	}

	@Override
	public String toString() {
		return this.nombre.toUpperCase() + "\n\nSu saldo inicial es: " + this.dineroDisponible + " monedas y su tiempo disponible: "
				+ this.tiempoDisponible + " hs.";
	}
}