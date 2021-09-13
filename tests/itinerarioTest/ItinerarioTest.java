package itinerarioTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import sugerenciaPromocionAtraccion.Atraccion;
import sugerenciaPromocionAtraccion.PromocionPorcentual;
import sugerenciaPromocionAtraccion.Sugerencia;
import tipo.Tipo;
import usuarioItinerario.Itinerario;

public class ItinerarioTest {

	Itinerario itinerario;
	Atraccion atraccion;
	Atraccion atraccion2;
	Atraccion atraccion3;
	ArrayList<Atraccion> misAtracciones = new ArrayList<Atraccion>();
	Sugerencia promocion;

	@Before
	public void setup() {
		atraccion = new Atraccion("Moria", 10, 5.5, 3, Tipo.PAISAJE);
		atraccion2 = new Atraccion("La Cueva", 15, 3.5, 10, Tipo.PAISAJE);
		atraccion3 = new Atraccion("Cafe Vasco", 10, 3, 15, Tipo.AVENTURAS);
		misAtracciones.add(atraccion);
		misAtracciones.add(atraccion2);
		promocion = new PromocionPorcentual("Promo Epica", misAtracciones, 0.15);
		itinerario = new Itinerario();
	}

	@Test
	public void crearItinerarioTest() {
		assertNotNull(itinerario);
	}

	@Test
	public void agregarCompraTest() {
		itinerario.agregarLaCompraDe(atraccion);
		itinerario.agregarLaCompraDe(atraccion2);
		itinerario.agregarLaCompraDe(promocion);

		int costoObtenido = itinerario.getCostoDelItinerario();
		int costoDeseado = 46;
		double duracionObtenida = itinerario.getDuracionDelItinerario();
		double duracionDeseada = 18.0;

		assertFalse(itinerario.getSugerenciasDiarias().isEmpty());
		assertEquals(costoDeseado, costoObtenido);
		assertEquals(duracionDeseada, duracionObtenida, 0);
	}

	@Test
	public void obtenerDetalleDeComprasTest() {
		itinerario.agregarLaCompraDe(atraccion3);
		itinerario.agregarLaCompraDe(promocion);
	
		ArrayList<String> arrayEsperado = new ArrayList<String>();
		arrayEsperado.add("ESTE ES EL DETALLE DE TU ITINERARIO\r\n\r\n");
		arrayEsperado.add("Costo de tu Itinerario:;31 monedas.;Duracion de tu Itinerario:;12.0 hs.\r\n\r\n");
		arrayEsperado.add("\r\nPromocion / Atraccion Comprada;Tipo;Costo;Duracion\r\n\r\n");
		arrayEsperado.add("Cafe Vasco;aventuras;10 monedas.;3.0 hs.\r\n");
		arrayEsperado.add("Promo Epica: incluye (Moria)(La Cueva);paisaje;21 monedas.;9.0 hs.\r\n");
		ArrayList<String> arrayObtenido = itinerario.obtenerDetalleDeCompras();

		assertEquals(arrayEsperado, arrayObtenido);
	}
}
