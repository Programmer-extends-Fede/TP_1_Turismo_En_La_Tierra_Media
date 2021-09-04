package consola;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import intinerario.Itinerario;
import ordenar.Ordenar;
import sugerencia.Sugerencia;
import usuario.Usuario;

public class Consola {
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private ArrayList<Sugerencia> sugerencias = new ArrayList<Sugerencia>();
	private Scanner entrada = new Scanner(System.in);
	private Itinerario itinerario;

	public Itinerario iniciarIteracion() {
		for (Usuario usuario : usuarios) {
			System.out.println(saludoInicial(usuario));

			for (Sugerencia sugerencia : seleccionarSugerenciasPara(usuario)) {
				System.out.println(sugerencia.toString());
				String seleccion;
				do {
					System.out.println("¿Desea elegir esta sugencia? (Presione 's' por SI o 'n' por NO");
					seleccion = entrada.nextLine();
				} while (seleccion != "s" || seleccion != "n");

				if (seleccion == "s") {
					this.itinerario.agregarLaCompraDe(sugerencia);
					System.out.println("¡Compra exitosa!");
					System.out.println("Dinero disponible: " + usuario.getDineroDisponible()
							+ " monedas de oro. Tiempo disponible: " + usuario.getTiempoDisponible() + " horas.");
				}
			}
		}
		return itinerario;
	}

	private String saludoInicial(Usuario usuario) {
		return "Bienvenido/a " + usuario.getNombre() + "\n"
				+ "Seleccione alguna de las siguientes sugerencias para disfutar su día en Tierra Media: " + "\n";
	}

	private ArrayList<Sugerencia> seleccionarSugerenciasPara(Usuario usuario) {
		ArrayList<Sugerencia> sugerenciasAimprimir = new ArrayList<Sugerencia>();
		for (Sugerencia sugerencia : sugerencias) {
			if (sugerencia.getCupo() > 0 && sugerencia.getPrecio() <= usuario.getDineroDisponible()
					&& sugerencia.getDuracion() <= usuario.getTiempoDisponible()) {
				sugerenciasAimprimir.add(sugerencia);
			}
		}
		Collections.sort(sugerenciasAimprimir, new Ordenar(usuario.getPreferencia()));
		return sugerenciasAimprimir;
	}

}
