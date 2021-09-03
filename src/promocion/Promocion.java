package promocion;

import java.util.ArrayList;

import atraccion.Atraccion;
import sugerencia.Sugerencia;
import tipo.Tipo;

public abstract class Promocion implements Sugerencia {

	private String nombre;
	private Tipo tipoDePromocion;
	private ArrayList<Atraccion> atracciones;
	private String nombreAtracciones = "";

	public Promocion(String nombre, ArrayList<Atraccion> atracciones, Tipo tipoDePromocion) {
		this.nombre = nombre;
		this.tipoDePromocion = tipoDePromocion;
		this.atracciones = atracciones;

		for (Atraccion atraccion : atracciones) {
			this.nombreAtracciones += atraccion.getNombre() + ", " ;
		}
	}

	public ArrayList<Atraccion> getAtracciones() {
		return this.atracciones;
	}

	@Override
	public String getNombre() {
		return this.nombre + ": contiene " + nombreAtracciones;
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
		return this.tipoDePromocion;
	}

	@Override
	public boolean esPromocion() {
		return true;
	}

	@Override
	public void vender() {
		for (Atraccion atraccion : atracciones) {
			atraccion.vender();
		}
	}

	@Override
	public String toString() {
		return getNombre() + "de tipo " + tipoDePromocion.getDescripcion()
				+ ", su costo es de " + getPrecio() + " monedas y su duracion de " + getDuracion() + " hs.";
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
}
