package promocion;

import tipo.Tipo;

public class PromocionAbsoluta extends Promocion {

	private int precioDePaquete;

	public PromocionAbsoluta(String nombre, Atraccion[] misAtracciones, String tipoDePromocion, int precioDePaquete) {
		super(nombre, misAtracciones, tipoDePromocion);
		this.precioDePaquete= precioDePaquete;
	}
	
	 @Override
	 public int getPrecio() {
		 return this.precioDePaquete;
	 }
}
