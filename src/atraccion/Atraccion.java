package atraccion;

public class Atraccion implements Sugerencia {
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

	public String getNombre() {
		return this.nombre;
	}

	public int getPrecio() {
		return this.precio;
	}

	public double getDuracion() {
		return this.duracion;
	}

	public int getCupo() {
		return this.cupo;
	}

	public Tipo getTipo() {
		return this.tipo;
	}

	public boolean esPromocion() {
		return false;
	}

	public boolean vender() {
		if (this.cupo > 0) {
			this.cupo--;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Atraccion: " + this.nombre + "\n" + "Precio: " + precio + " oros \n" + "Duracion: " + duracion
				+ " horas";
	}

}
