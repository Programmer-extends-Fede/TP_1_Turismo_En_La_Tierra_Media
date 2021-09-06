package promocion;

import java.util.ArrayList;

import atraccion.Atraccion;

public class PromocionPorcentual extends Promocion {

	private double porcentajeACobrar;

	public PromocionPorcentual(String nombre, ArrayList<Atraccion> atracciones,double porcentajeDescuento) {
		super(nombre, atracciones);
		this.porcentajeACobrar = 1 - porcentajeDescuento;
	}

	@Override
	public int getPrecio() {
		return (int) (super.getPrecio() * this.porcentajeACobrar);
	}
}
