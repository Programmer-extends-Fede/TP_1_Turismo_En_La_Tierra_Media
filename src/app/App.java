package app;

import tierraMediaConsolaYEntradaSalida.Consola;
import tierraMediaConsolaYEntradaSalida.TierraMedia;

public class App {

	public static void main(String[] args) {
		TierraMedia.construirUsuarios();
		TierraMedia.construirAtracciones();
		TierraMedia.construirPromociones();
		TierraMedia.construirSugerencias();
		Consola.iniciarInteraccion();
	}
}
