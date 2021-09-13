package sugerenciaPromocionAtraccion;

import java.util.ArrayList;
import java.util.Objects;

import tipo.Tipo;

public abstract class Promocion implements Sugerencia {

	private String nombre;
	private ArrayList<Atraccion> atracciones;
	private Tipo tipoDePromocion;

	public Promocion(String nombre, ArrayList<Atraccion> atracciones) {
		this.nombre = nombre + ": incluye ";
		this.tipoDePromocion = atracciones.get(0).getTipo();
		this.atracciones = atracciones;

		for (Atraccion atraccion : atracciones) {
			this.nombre += "(" + atraccion.getNombre() + ")";
		}
	}

	public ArrayList<Atraccion> getAtracciones() {
		return this.atracciones;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public int getPrecio() {
		int precio = 0;
		for (Atraccion atraccion : atracciones) {
			precio += atraccion.getPrecio();
		}
		return precio;
	}

	@Override
	public double getDuracion() {
		double duracion = 0;
		for (Atraccion atraccion : atracciones) {
			duracion += atraccion.getDuracion();
		}
		return duracion;
	}

	@Override
	public int getCupo() {
		int cupo = atracciones.get(0).getCupo();
		for (Atraccion atraccion : atracciones) {
			cupo = atraccion.getCupo() < cupo ? atraccion.getCupo() : cupo;
		}
		return cupo;
	}

	@Override
	public Tipo getTipo() {
		return tipoDePromocion;
	}

	@Override
	public boolean esPromocion() {
		return true;
	}

	@Override
	public void restarCupo() {
		for (Atraccion atraccion : atracciones) {
			atraccion.restarCupo();
		}
	}

	@Override
	public String toString() {
		return getNombre() + ". Tipo " + tipoDePromocion.getDescripcion() + ". Su costo es de " + getPrecio()
				+ " monedas. Su duracion de " + getDuracion() + " hs.\n";
	}

	@Override
	public boolean noEstaIncluidaEn(ArrayList<Atraccion> atraccionesCompradas) {
		boolean noEstaIncluida = true;
		for (Atraccion atraccion : atracciones) {
			if (atraccionesCompradas.contains(atraccion)) {
				noEstaIncluida = false;
				break;
			}
		}
		return noEstaIncluida;
	}

	@Override
	public int hashCode() {
		return Objects.hash(atracciones, nombre, tipoDePromocion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Objects.equals(atracciones, other.atracciones) && Objects.equals(nombre, other.nombre)
				&& tipoDePromocion == other.tipoDePromocion;
	}
}