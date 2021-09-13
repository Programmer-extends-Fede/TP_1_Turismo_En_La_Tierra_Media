package sugerenciaPromocionAtraccion;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {

	private int precioDePaquete;

	public PromocionAbsoluta(String nombre, ArrayList<Atraccion> atracciones, int precioDePaquete) {
		super(nombre, atracciones);
		this.precioDePaquete = precioDePaquete;
	}

	@Override
	public int getPrecio() {
		return this.precioDePaquete;
	}
}