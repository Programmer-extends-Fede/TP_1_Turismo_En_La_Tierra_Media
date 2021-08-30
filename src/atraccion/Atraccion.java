package atraccion;

public class Atraccion implements Sugerencia{
	private String nombre;
	private int precio;
	private double duracion;
	private int cupo;
	private Tipo tipo;

	public Atraccion(String nombre, int precio, double duracion, int cupo, Tipo tipo) {
		this.nombre = nombre;
		this.precio = precio;
		this.duracion = duracion;
		this.cupo = cupo;
		this.tipo = tipo;
	}
	
	public boolean esPromocion() {
		return false;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public double getDuracion() {
		return duracion;
	}

	public int getCupo() {
		return cupo;
	}
	
	public Tipo getTipo() {
		return this.tipo; //devuelve tipo o string getDescripcion?
	}
	
	private void descontarCupo() {
		this.cupo--;
	}
	public void vender() {
		this.descontarCupo();
	}

	@Override
	public String toString() {
		return "La atraccion " + this.getNombre() + ", de tipo " + this.tipo.getDescripcion()
				+ ", tiene un costo de "+ this.getPrecio() + " pesos y su duracion es de "
				+ this.getDuracion() + "horas ";
	}
	
	
}
