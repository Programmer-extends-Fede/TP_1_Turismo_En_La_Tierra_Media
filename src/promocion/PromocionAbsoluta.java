package promocion;

import java.util.ArrayList;

import atraccion.Atraccion;
import tipo.Tipo;

public class PromocionAbsoluta extends Promocion {

	private int precioDePaquete;

	public PromocionAbsoluta(String nombre, ArrayList<Atraccion> atracciones, Tipo tipoDePromocion, int precioDePaquete) {
		super(nombre, atracciones, tipoDePromocion);
		this.precioDePaquete= precioDePaquete;
	}
	
	 @Override
	 public int getPrecio() {
		 return this.precioDePaquete;
	 }
}
