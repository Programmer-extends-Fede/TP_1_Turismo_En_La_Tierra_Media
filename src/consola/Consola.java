package consola;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import intinerario.Itinerario;
import ordenar.Ordenar;
import sugerencia.Sugerencia;
import tierraMedia.TierraMedia;
import usuario.Usuario;

public class Consola {
	private ArrayList<Usuario> usuarios = TierraMedia.getUsuarios();
	private ArrayList<Sugerencia> sugerencias = TierraMedia.getSugerencias();
	private Scanner entrada = new Scanner(System.in);
	private Itinerario itinerario;

	public Itinerario iniciarIteracion() {
		for (Usuario usuario : usuarios) {
			System.out.println(saludoInicial(usuario));

			for (Sugerencia sugerencia : seleccionarSugerenciasPara(usuario)) {
				System.out.println(sugerencia.toString());
				String seleccionDelUsuario;
				do {
					System.out.println("¿Desea elegir esta sugencia? (Presione 's' por SI o 'n' por NO");
					seleccionDelUsuario = entrada.nextLine();
				} while (seleccionDelUsuario != "s" || seleccionDelUsuario != "n");

				if (seleccionDelUsuario == "s") {
					this.itinerario.agregarLaCompraDe(sugerencia);
					System.out.println(mensajeDeCompraExitosa(usuario));
				}
			}
		}
		return itinerario;
	}

	private String saludoInicial(Usuario usuario) {
		return "Bienvenido/a " + usuario.getNombre() + "\n"
				+ "Seleccione alguna de las siguientes sugerencias para disfutar su día en Tierra Media: " + "\n";
	}

	private String mensajeDeCompraExitosa(Usuario usuario) {
		return "¡Compra exitosa!" + "\n" + "Dinero disponible: " + usuario.getDineroDisponible()
				+ " monedas de oro. Tiempo disponible: " + usuario.getTiempoDisponible() + " horas.";
	}

	private ArrayList<Sugerencia> seleccionarSugerenciasPara(Usuario usuario) {
		ArrayList<Sugerencia> sugerenciasAimprimir = new ArrayList<Sugerencia>();
		for (Sugerencia sugerencia : sugerencias) {
			if (puedeSerOfrecidaLa(sugerencia) && elUsuarioPuedeComprar(usuario, sugerencia)) {
				sugerenciasAimprimir.add(sugerencia);
			}
		}
		Collections.sort(sugerenciasAimprimir, new Ordenar(usuario.getPreferencia())); // Esto lo hace TierraMedia
		return sugerenciasAimprimir;
	}

	private boolean puedeSerOfrecidaLa(Sugerencia sugerencia) {
		return !this.itinerario.getComprasRealizadas().contains(sugerencia) && sugerencia.getCupo() > 0;
	}

	private boolean elUsuarioPuedeComprar(Usuario usuario, Sugerencia sugerencia) {
		return sugerencia.getPrecio() <= usuario.getDineroDisponible()
				&& sugerencia.getDuracion() <= usuario.getTiempoDisponible();
	}

}
