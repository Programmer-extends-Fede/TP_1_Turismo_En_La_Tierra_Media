package usuarioTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import sugerenciaPromocionAtraccion.Atraccion;
import sugerenciaPromocionAtraccion.PromocionPorcentual;
import tipo.Tipo;
import usuarioItinerario.Usuario;

public class UsuarioTest {

	Usuario usuario;
	Atraccion atraccion;
	Atraccion atraccion2;
	Atraccion atraccion3;
	ArrayList<Atraccion> misAtracciones = new ArrayList<Atraccion>();
	PromocionPorcentual promocion;

	@Before
	public void setup() {
		usuario = new Usuario("Emir", 50, 15, Tipo.AVENTURAS);
		atraccion = new Atraccion("Moria", 10, 5.5, 3, Tipo.PAISAJE);
		atraccion2 = new Atraccion("La Cueva", 15, 3.5, 10, Tipo.PAISAJE);
		atraccion3 = new Atraccion("Cafe Vasco", 10, 3, 15, Tipo.AVENTURAS);
		misAtracciones.add(atraccion);
		misAtracciones.add(atraccion2);
		promocion = new PromocionPorcentual("Promo Epica", misAtracciones, 0.15);
	}

	@Test
	public void crearUsuarioTest() {
		assertNotNull(usuario);
	}

	@Test
	public void comprarTest() {
		Atraccion miAtraccion = new Atraccion("VueltaAlMundo", 15, 0.5, 2, Tipo.AVENTURAS);
		usuario.comprar(miAtraccion);

		int dineroDispObtenido = usuario.getDineroDisponible();
		int dineroDispEsperado = 35;
		double tiempoDispObtenido = usuario.getTiempoDisponible();
		double tiempoDispEsperado = 14.5;

		assertEquals(dineroDispEsperado, dineroDispObtenido);
		assertEquals(tiempoDispEsperado, tiempoDispObtenido, 0);
	}

	@Test
	public void obtenerDatosDelItinerarioTest() {
		usuario.comprar(atraccion3);
		usuario.comprar(promocion);

		ArrayList<String> arrayEsperado = new ArrayList<String>();
		arrayEsperado.add("USUARIO: EMIR;SALDO INICIAL: 50 MONEDAS;TIEMPO INICIAL: 15.0 HS.\r\n\r\n");
		arrayEsperado.add("ESTE ES EL DETALLE DE TU ITINERARIO\r\n\r\n");
		arrayEsperado.add("Costo de tu Itinerario:;31 monedas.;Duracion de tu Itinerario:;12.0 hs.\r\n\r\n");
		arrayEsperado.add("\r\nPromocion / Atraccion Comprada;Tipo;Costo;Duracion\r\n\r\n");
		arrayEsperado.add("Cafe Vasco;aventuras;10 monedas.;3.0 hs.\r\n");
		arrayEsperado.add("Promo Epica: incluye (Moria)(La Cueva);paisaje;21 monedas.;9.0 hs.\r\n");
		arrayEsperado.add("\r\n\r\nTu saldo actual es:;19 monedas.;Tu tiempo restante es de:;3.0 hs.");

		ArrayList<String> arrayObtenido = usuario.obtenerDatosDelItinerario();

		assertEquals(arrayEsperado, arrayObtenido);
	}
}
