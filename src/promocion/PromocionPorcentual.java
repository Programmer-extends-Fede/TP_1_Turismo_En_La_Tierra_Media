package promocion;

import tipo.Tipo;

public class PromocionPorcentual extends Promocion {

	private double porcentajeACobrar;

	public PromocionPorcentual(String nombre, Atraccion[] misAtracciones, String tipoDePromocion,double porcentajeDescuento) {
		super(nombre, misAtracciones, tipoDePromocion);
		this.porcentajeACobrar = 1 - porcentajeDescuento;
	}

	@Override
	public int getPrecio() {
		return (int) (super.getPrecio() * this.porcentajeACobrar);
	}
}
