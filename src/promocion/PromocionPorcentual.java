package promocion;

import java.util.ArrayList;

import atraccion.Atraccion;
import tipo.Tipo;

public class PromocionPorcentual extends Promocion {

	private double porcentajeACobrar;

	public PromocionPorcentual(String nombre, ArrayList<Atraccion> atracciones, Tipo tipoDePromocion,double porcentajeDescuento) {
		super(nombre, atracciones, tipoDePromocion);
		this.porcentajeACobrar = 1 - porcentajeDescuento;
	}

	@Override
	public int getPrecio() {
		return (int) (super.getPrecio() * this.porcentajeACobrar);
	}
}
