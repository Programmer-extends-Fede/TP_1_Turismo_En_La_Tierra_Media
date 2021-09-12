package app;

import javax.swing.JTextPane;

import tierraMediaYEntradaSalida.TierraMedia;
import ventanaConsolaNuevaYLaminas.ConsolaNueva;
import ventanaConsolaNuevaYLaminas.Marco;

public class App {

	public static void main(String[] args) {

		TierraMedia.construirAtracciones();
		TierraMedia.construirPromociones();
		TierraMedia.construirSugerencias();
		TierraMedia.construirUsuarios();

		JTextPane areaImpresion = new JTextPane(); //esto lo voy a usar como display
		
		ConsolaNueva consola = new ConsolaNueva("Hilo de mi consola", areaImpresion);
		
		@SuppressWarnings("unused")
		Marco marco = new Marco(consola, areaImpresion); //esta es la creacion de la ventana, con todo lo que contiene.

		consola.gethilo().start(); //aca inicia la ejecucion de la consola, empieza a mostrar los arrays y demas.
	}
}
