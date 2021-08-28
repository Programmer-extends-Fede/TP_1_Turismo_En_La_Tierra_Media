package sugerencia;

import tipo.Tipo;

public interface Sugerencia {
	
	public String getNombre();

	public int getPrecio();

	public double getDuracion();
	
	public int getCupo();

	public Tipo getTipo();
	
	public boolean esPromocion();
	
	public void vender();
	
}
