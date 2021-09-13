package ordenarSugerencias;

import java.util.Comparator;

import sugerenciaPromocionAtraccion.Sugerencia;
import tipo.Tipo;

public class OrdenarSugerencias implements Comparator<Sugerencia> {

	private Tipo usuarioTipo;

	public OrdenarSugerencias(Tipo usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	@Override
	public int compare(Sugerencia o1, Sugerencia o2) {
		int resultado;
		Tipo o1Tipo = o1.getTipo();
		Tipo o2Tipo = o2.getTipo();

		resultado = -Boolean.compare(o1.esPromocion(), o2.esPromocion());
		if (resultado == 0)
			resultado = o1Tipo.equals(usuarioTipo) && !o2Tipo.equals(usuarioTipo) ? -1
					: !o1Tipo.equals(usuarioTipo) && o2Tipo.equals(usuarioTipo) ? 1 : 0;
		if (resultado == 0)
			resultado = -Integer.compare(o1.getPrecio(), o2.getPrecio());
		if (resultado == 0)
			resultado = -Double.compare(o1.getDuracion(), o2.getDuracion());
		return resultado;
	}
}
