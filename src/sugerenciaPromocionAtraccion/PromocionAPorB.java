package sugerenciaPromocionAtraccion;

import java.util.ArrayList;
import java.util.Collections;

public class PromocionAPorB extends Promocion {

	private ArrayList<Atraccion> atraccionesACobrar = new ArrayList<Atraccion>();

	public PromocionAPorB(String nombre, ArrayList<Atraccion> atracciones, int cantPromosACobrar) {
		super(nombre, atracciones);
		Collections.sort(atracciones);

		for (int i = 0; i < cantPromosACobrar; i++) {
			atraccionesACobrar.add(atracciones.get(i));
		}
	}

	@Override
	public int getPrecio() {
		int importeACobrar = 0;
		for (Atraccion atraccion : atraccionesACobrar) {
			importeACobrar += atraccion.getPrecio();
		}
		return importeACobrar;
	}
}
