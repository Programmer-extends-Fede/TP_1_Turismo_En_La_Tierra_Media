package promocion;

import tipo.Tipo;

public class PromocionPorcentual extends Promocion {

	private double porcentajeACobrar;

	public PromocionPorcentual(String nombre, Atraccion[] misAtracciones, Tipo tipoDePromocion,double porcentajeACobrar) {
		super(nombre, misAtracciones, tipoDePromocion);
		this.porcentajeACobrar = 1 - porcentajeACobrar;
	}

	@Override
	public int getPrecio() {
		return (int) (super.getPrecio() * this.porcentajeACobrar);
	}
}
