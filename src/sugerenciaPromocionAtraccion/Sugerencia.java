package sugerenciaPromocionAtraccion;

import java.util.ArrayList;

import tipo.Tipo;

public interface Sugerencia {

	public String getNombre();

	public int getPrecio();

	public double getDuracion();

	public int getCupo();

	public Tipo getTipo();

	public boolean esPromocion();

	public void restarCupo();

	public boolean noEstaIncluidaEn(ArrayList<Atraccion> atraccionesCompradas);
}
