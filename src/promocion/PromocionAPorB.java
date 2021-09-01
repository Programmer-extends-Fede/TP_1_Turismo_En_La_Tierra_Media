package promocion;

import java.util.ArrayList;

import atraccion.Atraccion;
import tipo.Tipo;

public class PromocionAPorB extends Promocion {

	private ArrayList<Atraccion> atraccionesACobrar;

	public PromocionAPorB(String nombre, ArrayList<Atraccion> atracciones, Tipo tipoDePromocion, int cantPromosACobrar) {
		super(nombre, atracciones, tipoDePromocion);
		for (int i = 0; i < cantPromosACobrar; i++) {
			atraccionesACobrar.add(atracciones.get(i));
		}
	}

	@Override
	public int getPrecio() {
		int aCobrar = 0;
		for (Atraccion atraccion : atraccionesACobrar) {
			aCobrar += atraccion.getPrecio();
		}
		return aCobrar;
	}
}
