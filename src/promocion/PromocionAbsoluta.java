package promocion;

import java.util.ArrayList;

import atraccion.Atraccion;

public class PromocionAbsoluta extends Promocion {

	private int precioDePaquete;

	public PromocionAbsoluta(String nombre, ArrayList<Atraccion> atracciones, int precioDePaquete) {
		super(nombre, atracciones);
		this.precioDePaquete= precioDePaquete;
	}
	
	 @Override
	 public int getPrecio() {
		 return this.precioDePaquete;
	 }
}
