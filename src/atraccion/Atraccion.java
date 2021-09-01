package atraccion;

public class Atraccion implements Sugerencia {
	private String nombre;
	private int precio;
	private int cupo;
	private Tipo tipo;
	private double duracion;

	public Atraccion(String nombre, int precio, int cupo, Tipo tipo, double duracion) {
		this.nombre = nombre;
		this.precio = precio;
		this.cupo = cupo;
		this.tipo = tipo;
		this.duracion = duracion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getPrecio() {
		return this.precio;
	}

	public int getCupo() {
		return this.cupo;
	}

	public Tipo getTipo() {
		return this.tipo;
	}

	public double getDuracion() {
		return this.duracion;
	}

	public void vender() {
		this.cupo--;
	}

	@Override
	public String toString() {
		return "El costo de la atraccion" + this.nombre + ", de tipo" + this.tipo.getDescripcion() + ", es de"
				+ this.getPrecio() + "pesos y, su duracion es de" + this.getDuracion() + "horas.";
	}

	public boolean esPromocion() {
		return false;
	}

}
