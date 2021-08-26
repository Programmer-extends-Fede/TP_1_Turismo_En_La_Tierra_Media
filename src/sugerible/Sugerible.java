package sugerible;

import tipo.Tipo;

public interface Sugerible {

	public String getNombre();

	public int getPrecio();

	public double getDuracion();
	
	public int getCupo();

	public Tipo getTipo();
	
	public boolean esPromocion();
	
	public void vender();
	
}
