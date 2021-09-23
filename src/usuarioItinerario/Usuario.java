package usuarioItinerario;

import java.util.ArrayList;
import java.util.Objects;

import sugerenciaPromocionAtraccion.Sugerencia;
import tipo.Tipo;

public class Usuario {
	private String nombre;
	private int dineroDisponible;
	private double tiempoDisponible;
	private Tipo preferencia;
	private Itinerario itinerario;

	public Usuario(String nombre, int dineroDisponible, double tiempoDisponible, Tipo preferencia) {
		this.nombre = nombre;
		this.dineroDisponible = dineroDisponible;
		this.tiempoDisponible = tiempoDisponible;
		this.preferencia = preferencia;
		this.itinerario = new Itinerario();
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

	public Itinerario getItinerario() {
		return this.itinerario;
	}

	public void comprar(Sugerencia unaSugerencia) {
		this.dineroDisponible -= unaSugerencia.getPrecio();
		this.tiempoDisponible -= unaSugerencia.getDuracion();
		this.itinerario.agregarLaCompraDe(unaSugerencia);
	}

	public ArrayList<String> obtenerDatosDelItinerario() {
		ArrayList<String> datosADevolver = itinerario.obtenerDetalleDeCompras();
		if (!datosADevolver.isEmpty()) {
			datosADevolver.add(0,
					("Usuario: " + this.nombre + ";Saldo inicial: "
							+ (this.dineroDisponible + itinerario.getCostoDelItinerario()) + " monedas;Tiempo inicial: "
							+ (this.tiempoDisponible + itinerario.getDuracionDelItinerario()) + " hs.\n\n")
									.toUpperCase());
			datosADevolver.add("\n\nTu saldo actual es:;" + this.dineroDisponible
					+ " monedas.;Tu tiempo restante es de:;" + this.tiempoDisponible + " hs.");
		}
		return datosADevolver;
	}

	@Override
	public String toString() {
		return this.nombre.toUpperCase() + "\n\nSu saldo inicial es: " + this.dineroDisponible
				+ " monedas y su tiempo disponible: " + this.tiempoDisponible + " hs.";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dineroDisponible, nombre, preferencia, tiempoDisponible);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return dineroDisponible == other.dineroDisponible && Objects.equals(nombre, other.nombre)
				&& preferencia == other.preferencia
				&& Double.doubleToLongBits(tiempoDisponible) == Double.doubleToLongBits(other.tiempoDisponible);
	}
}
