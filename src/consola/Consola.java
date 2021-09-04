package consola;

import java.util.ArrayList;
import java.util.Scanner;

import TierraMedia.TierraMedia;
import atraccion.Atraccion;
import promocion.Promocion;
import sugerencia.Sugerencia;
import usuario.Usuario;

public class Consola {

	static ArrayList<Atraccion> atraccionesTemp = new ArrayList<Atraccion>();
	static final String ENTRADA_INCORRECTA = "<---ENTRADA INCORRECTA--->".indent(50);
	static final String SEPARADOR_USUARIOS = "______________________________________________________________________________________"
			.indent(23);
	static final String SUBRAYADO = "------------------------------";
	static final String LATERAL_CUADRADO = "|                                                                    |"
			.indent(35);
	static final String TECHO_CUADRADO = "                                    ____________________________________________________________________\n";
	static final String SUELO_CUADRADO = "|____________________________________________________________________|"
			.indent(35);
	static final String MENSAJE_FINAL = "---------------------------------------------".indent(43)
			+ "MUCHAS GRACIAS, YA NO QUEDAN USUARIOS POR VER".indent(43)
			+ "---------------------------------------------".indent(43);
	static final String MENSAJE_INICIAL = TECHO_CUADRADO
			+ "|            ESTE ES EL SISTEMA DE COMPRAS DE TIERRAMEDIA            |".indent(35) + LATERAL_CUADRADO
			+ "|RECUERDE, PRESIONAR 'S' PARA ACEPTAR LA COMPRA Y 'N' PARA RECHAZARLA|".indent(35) + LATERAL_CUADRADO
			+ LATERAL_CUADRADO + LATERAL_CUADRADO
			+ "|                  PRESIONE ENTER PARA CONTINUAR                     |".indent(35) + SUELO_CUADRADO;

	public static void iniciarInteraccion() {
		ArrayList<Usuario> usuarios = TierraMedia.getUsuarios();
		ArrayList<Sugerencia> sugerencias = TierraMedia.getSugerencias();
		Scanner entrada = new Scanner(System.in);
		System.out.println(MENSAJE_INICIAL);

		for (Usuario usuario : usuarios) {
			TierraMedia.ordenar(sugerencias, usuario.getPreferencia());
			entrada.nextLine();
			System.out.println("BIENVENIDO " + usuario + "\n");

			for (Sugerencia sugerencia : sugerencias) {
				if (sugerencia.getCupo() > 0 && puedeComprarEl(usuario, sugerencia)
						&& sugerencia.noEstaIncluidaEn(atraccionesTemp)) {

					System.out.println("Deseas comprar " + sugerencia);
					String respuesta = "";

					while (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n")) {
						respuesta = entrada.nextLine();

						if (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n"))
							System.out.println(ENTRADA_INCORRECTA);
						else if (respuesta.equalsIgnoreCase("s")) {
							sugerencia.restarCupo();
							usuario.comprar(sugerencia);
							agregarAtraccionTemp(sugerencia);
							System.out.println(("[Monedas restantes: " + usuario.getDineroDisponible()
									+ "]       [Horas restante: " + usuario.getTiempoDisponible() + "]").indent(35));
						} else
							System.out.println();
					}
				}
			}
			if (usuario.getMiItinerario().getSugerenciasDiarias().isEmpty())
				System.out.println("\nNO REALIZASTE COMPRAS.".indent(2));
			else {
				System.out
						.println("\n\nTu itinerario es el siguiente:\n" + SUBRAYADO + "\n\n" + usuario.getMiItinerario()
								+ ("\nSu saldo actual: " + usuario.getDineroDisponible()
										+ " monedas.                  Horas restantes: " + usuario.getTiempoDisponible()
										+ " hs.").indent(31));
			}
			System.out.println(SEPARADOR_USUARIOS);
			if (!usuarios.get(usuarios.size() - 1).equals(usuario))
				System.out.print("PRESIONA ENTER PARA MOSTRAR EL SIGUIENTE USUARIO".indent(40));
			atraccionesTemp.clear();
		}
		System.out.println(MENSAJE_FINAL);
		entrada.close();
	}

	private static boolean puedeComprarEl(Usuario usuario, Sugerencia laSugerencia) {
		return usuario.getDineroDisponible() >= laSugerencia.getPrecio()
				&& usuario.getTiempoDisponible() >= laSugerencia.getDuracion();
	}

	private static void agregarAtraccionTemp(Sugerencia sugerencia) {
		if (sugerencia.esPromocion()) {
			Promocion miPromo = (Promocion) sugerencia;
			atraccionesTemp.addAll(miPromo.getAtracciones());
		} else {
			atraccionesTemp.add((Atraccion) sugerencia);
		}
	}
}