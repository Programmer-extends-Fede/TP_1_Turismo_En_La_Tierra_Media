package tipo;

public enum Tipo {

	AVENTURAS("Aventuras"), DEGUSTACION("Degustacion"), PAISAJE("Paisaje");
	
	private String descripcion;
	
	private Tipo(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
}
