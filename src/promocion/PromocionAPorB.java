package promocion;

import java.util.ArrayList;

import atraccion.Atraccion;
import tipo.Tipo;

public class PromocionAPorB extends Promocion {

	private ArrayList<Atraccion> atraccionesDeRegalo;

	public PromocionAPorB(String nombre, ArrayList<Atraccion> atracciones, Tipo tipoDePromocion, int cantPromosRegalo) {
		super(nombre, atracciones, tipoDePromocion);
		for (int i = 0; i < cantPromosRegalo; i++) {
			atraccionesDeRegalo.add(atracciones.get(i));
		}
	}

	@Override
	public int getPrecio() {
		int descuento = 0;
		
		for (Atraccion atraccionRegalada : atraccionesDeRegalo) {
			descuento = atraccionRegalada.getPrecio();
		}
		return super.getPrecio() - descuento;
	}
}
