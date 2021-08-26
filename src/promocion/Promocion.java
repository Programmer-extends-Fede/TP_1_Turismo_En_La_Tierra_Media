package promocion;

import java.util.Arrays;

import sugerible.Sugerible;
import tipo.Tipo;

public abstract class Promocion implements Sugerible {

	private String nombre;
	private int precio, cupo;
	private double duracion;
	private Tipo tipoDePromocion;
	private Atraccion[] misAtracciones;
	private String[] nombreAtracciones;

	public Promocion(String nombre, Atraccion[] misAtracciones, Tipo tipoDePromocion) {
		this.nombre = nombre;
		this.tipoDePromocion = tipoDePromocion;
		this.misAtracciones = misAtracciones;
		this.nombreAtracciones = new String[misAtracciones.length];

		for (int i = 0; i < nombreAtracciones.length; i++) {
			this.precio += misAtracciones[i].getPrecio();
			this.duracion += misAtracciones[i].getDuracion();
			nombreAtracciones[i] = misAtracciones[i].getNombre();
		}
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public int getPrecio() {
		return this.precio;
	}

	@Override
	public double getDuracion() {
		return this.duracion;
	}

	@Override
	public int getCupo() {
		cupo = misAtracciones[0].getCupo();
		for (int i = 1; i < misAtracciones.length; i++) {
			cupo = misAtracciones[i].getCupo() < cupo ? misAtracciones[i].getCupo() : cupo;
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
		for (Atraccion atraccion : misAtracciones) {
			atraccion.vender();
		}
	}

	@Override
	public String toString() {
		return getNombre() + ": " + Arrays.toString(nombreAtracciones) + ", de tipo " + tipoDePromocion.getDescripcion()
				+ ", su costo es de " + getPrecio() + " monedas y su duracion de " + getDuracion() + " horas.";
	}
}
