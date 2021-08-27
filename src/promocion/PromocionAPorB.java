package promocion;

import tipo.Tipo;

public class PromocionAPorB extends Promocion {

	private int importeADescontar;

	public PromocionAPorB(String nombre, Atraccion[] misAtracciones, String tipoDePromocion, int cantPromosRegalo) {
		super(nombre, misAtracciones, tipoDePromocion);
		for (int i = 0; i < cantPromosRegalo; i++) {
			this.importeADescontar += misAtracciones[i].getPrecio();
		}
	}

	@Override
	public int getPrecio() {
		return super.getPrecio() - this.importeADescontar;
	}
}
