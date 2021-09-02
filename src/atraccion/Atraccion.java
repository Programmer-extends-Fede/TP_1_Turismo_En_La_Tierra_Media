package atraccion;

import sugerencia.Sugerencia;
import tipo.Tipo;

public class Atraccion implements Sugerencia {
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
	public void vender() {
		this.cupo--;
	}

	@Override
	public String toString() {
		return "Atraccion: " + this.nombre + "\n" + "Precio: " + precio + " oros \n" + "Duracion: " + duracion
				+ " horas";
	}

	@Override
	public boolean atraccionIncluida(Atraccion atraccion) {
		return this == atraccion;
	}

}
