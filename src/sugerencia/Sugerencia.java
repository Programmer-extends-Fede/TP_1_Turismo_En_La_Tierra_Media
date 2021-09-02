package sugerencia;

import java.util.ArrayList;

import atraccion.Atraccion;
import tipo.Tipo;

public interface Sugerencia {
	
	public String getNombre();

	public int getPrecio();

	public double getDuracion();
	
	public int getCupo();

	public Tipo getTipo();
	
	public boolean esPromocion();
	
	public void vender();
	
	public boolean noEstaIncluidaEn(ArrayList<Atraccion> atraccionesCompradas);
}
