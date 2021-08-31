package promocion;

import java.util.ArrayList;
import java.util.Arrays;

import sugerencia.Sugerencia;
import tipo.Tipo;

public abstract class Promocion implements Sugerencia {

	private String nombre;
	private Tipo tipoDePromocion;
	private ArrayList<Atraccion> atracciones;
	private String[] nombreAtracciones;

	public Promocion(String nombre, Atraccion[] misAtracciones, Tipo tipoDePromocion) {
		this.nombre = nombre;
		this.tipoDePromocion = tipoDePromocion;
		this.atracciones = misAtracciones;
		this.nombreAtracciones = new String[misAtracciones.length];

		for (int i = 0; i < nombreAtracciones.length; i++) {
			nombreAtracciones[i] = misAtracciones[i].getNombre();
		}
	}
	
	public ArrayList<Atraccion> getAtracciones()

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
		double cupo = atracciones[0].getCupo();
		for (int i = 1; i < atracciones.length; i++) {
			cupo = atracciones[i].getCupo() < cupo ? atracciones[i].getCupo() : cupo;
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
		return getNombre() + ": " + Arrays.toString(nombreAtracciones) + ", de tipo " + tipoDePromocion.getDescripcion()
				+ ", su costo es de " + getPrecio() + " monedas y su duracion de " + getDuracion() + " horas.";
	}
}