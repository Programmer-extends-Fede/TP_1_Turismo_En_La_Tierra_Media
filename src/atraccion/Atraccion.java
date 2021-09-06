package atraccion;

import java.util.ArrayList;

import sugerencia.Sugerencia;
import tipo.Tipo;

public class Atraccion implements Sugerencia, Comparable<Atraccion> {
	private String nombre;
	private int precio;
	private double duracion;
	private int cupo;
	private Tipo tipo;

	public Atraccion(String nombre, int precio, double duracion, int cupo, Tipo tipo) {
		this.nombre = nombre;
		this.precio = precio;
		this.duracion = duracion;
		this.cupo = cupo;
		this.tipo = tipo;
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
		return this.cupo;
	}

	@Override
	public Tipo getTipo() {
		return this.tipo;
	}

	@Override
	public boolean esPromocion() {
		return false;
	}

	@Override
	public void restarCupo() {
		this.cupo--;
	}

	@Override
	public String toString() {
		return this.nombre + ": de tipo " + this.tipo.getDescripcion() + ". Su precio es de " + this.precio
				+ " monedas. Su duracion de " + this.duracion + " hs.";
	}

	@Override
	public boolean noEstaIncluidaEn(ArrayList<Atraccion> atraccionesCompradas) {
		return !atraccionesCompradas.contains(this);
	}

	@Override
	public int compareTo(Atraccion o) {
		return -Integer.compare(this.precio, o.precio);
	}
}
