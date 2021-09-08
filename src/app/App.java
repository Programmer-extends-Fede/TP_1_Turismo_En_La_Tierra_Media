package app;

import java.io.FileNotFoundException;

import consola.Consola;
import tierraMedia.TierraMedia;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		TierraMedia.construirUsuarios();
		TierraMedia.construirAtracciones();
		TierraMedia.construirPromociones();
		TierraMedia.construirSugerencias();
		Consola.iniciarInteraccion();
	}
}
