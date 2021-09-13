package app;

import consola.Consola;
import tierraMedia.tierraMedia;

public class App {

	public static void main(String[] args) {
		tierraMedia.construirUsuarios();
		tierraMedia.construirAtracciones();
		tierraMedia.construirPromociones();
		tierraMedia.construirSugerencias();
		Consola.iniciarInteraccion();
	}
}
