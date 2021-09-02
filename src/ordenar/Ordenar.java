package ordenar;



import java.util.Comparator;
import sugerencia.Sugerencia;
import tipo.Tipo;

public class Ordenar implements Comparator<Sugerencia> {

	private static Tipo usuarioTipo;

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

	public static void setUsuarioTipo(Tipo preferenciaDeUsuario) {
		usuarioTipo = preferenciaDeUsuario;
	}
}
