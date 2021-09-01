package atraccion;

public class Usuario {
	private String nombre;
	private int dineroDisponible;
	private double tiempoDisponible;
	private Tipo tipoPreferencia;
	private Itinerario miItinerario;

	public Usuario(String nombre, int dinero, double tiempo, Tipo tipoPreferencia) {
		this.nombre = nombre;
		this.dineroDisponible = dinero;
		this.tiempoDisponible = tiempo;
		this.tipoPreferencia = tipoPreferencia;
		this.miItinerario = new Itinerario();
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

	public void comprar(Sugerencia sugerencia) {	
		this.tiempoDisponible -= sugerencia.getDuracion();	
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
