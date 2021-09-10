package app;

import java.util.ArrayList;

import TierraMedia.TierraMedia;
import consola.Consola;
import entradaSalida.EntradaSalida;

public class App {

	public static void main(String[] args) {
		TierraMedia.construirUsuarios();
		TierraMedia.construirAtracciones();
		TierraMedia.construirPromociones();
		TierraMedia.construirSugerencias();
		//Consola.iniciarInteraccion();
		
		ArrayList<String> arrayDePrueba= new ArrayList<String>();
		arrayDePrueba.add("Usuario");
		arrayDePrueba.add("Atraccion");
		arrayDePrueba.add("Promocion");
		arrayDePrueba.add("Sugerencia");
		
		EntradaSalida.guardarEnArchivo(arrayDePrueba, arrayDePrueba.get(0));
		
		//System.out.println(arrayDePrueba);
	}
}
