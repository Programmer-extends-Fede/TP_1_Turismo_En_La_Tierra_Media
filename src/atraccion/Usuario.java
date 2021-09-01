package atraccion;

public class Usuario {
	private String nombre;
	private double dineroDisponible;
	private double tiempoDisponible;
	private Tipo tipoPreferencia;
	private Itinerario miItinerario;

	public Usuario(String nombre, double dinero, double tiempo, Tipo tipoPreferencia, Itinerario miItinerario) {
		this.nombre = nombre;
		this.dineroDisponible = dinero;
		this.tiempoDisponible = tiempo;
		this.tipoPreferencia = tipoPreferencia;
		this.miItinerario = miItinerario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public double getDineroDisponible() {
		return this.dineroDisponible;
	}

	public double getTiempoDisponible() {
		return this.tiempoDisponible;
	}

	public Tipo getTipoPreferencia() {
		return this.tipoPreferencia;
	}

	public Itinerario getItinerario() {
		return this.miItinerario;
	}

	public boolean comprar(Sugerencia sugerencia) {
		if (sugerencia.getPrecio <= this.dineroDisponible && sugerencia.getDuracion <= this.tiempoDisponible) {
			this.descontarDinero();
			this.descontarTiempo();
		}
		return true;
	}

	public void descontarTiempo() {
		this.tiempoDisponible -= sugerencia.getDuracion();
	}

	public void descontarDinero() {
		this.dineroDisponible -= sugerencia.getPrecio();
	}

	@Override
	public String toString() {
		return "El usuario" + this.getNombre() + "le gusta las atracciones del tipo" 
		+ this.getTipoPreferencia() + ", posee" + this.getDineroDisponible() 
		+ "pesos y" + this.tiempoDisponible 
		+ "horas disponibles";
	}

}
