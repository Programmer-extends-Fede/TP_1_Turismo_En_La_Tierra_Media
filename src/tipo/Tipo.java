package tipo;

public enum Tipo {

	AVENTURAS("aventuras"), DEGUSTACION("degustacion"), PAISAJE("paisaje");

	private String descripcion;

	private Tipo(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
}
