package app;

import javax.swing.JTextArea;

import marco.ConsolaNueva;
import marco.Marco;
import tierraMediaConsolaYEntradaSalida.TierraMedia;

public class App {

	public static void main(String[] args) {

		TierraMedia.construirAtracciones();
		TierraMedia.construirPromociones();
		TierraMedia.construirSugerencias();
		TierraMedia.construirUsuarios();

		JTextArea areaImpresion = new JTextArea();
		ConsolaNueva consola = new ConsolaNueva("Pepe", areaImpresion);
		Marco marco = new Marco(consola, areaImpresion);
		
		consola.gethilo().start();
	}
}
