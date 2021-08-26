package promocion;

import sugerible.Sugerible;
import tipo.Tipo;

public abstract class Promocion implements Sugerible {

	private String nombre;
	private int precio, cupo;
	private double duracion;
	private Tipo tipoDePromocion;
	private Atraccion[] misAtracciones;

	public Promocion(String nombre, Atraccion[] misAtracciones, Tipo tipoDePromocion) {
		this.nombre = nombre;
		this.tipoDePromocion = tipoDePromocion;
		this.misAtracciones = misAtracciones;

		for (Atraccion atraccion : misAtracciones) {
			this.precio += atraccion.getPrecio();
			this.duracion += atraccion.getDuracion();
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
}
