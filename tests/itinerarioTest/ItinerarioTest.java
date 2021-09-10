package itinerarioTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import atraccion.Atraccion;
import itinerario.Itinerario;
import promocion.PromocionPorcentual;
import sugerencia.Sugerencia;
import tipo.Tipo;
import usuario.Usuario;

public class ItinerarioTest {

	Itinerario itinerario;
	Atraccion atraccion;
	Atraccion atraccion2;
	Atraccion atraccion3;
	ArrayList<Atraccion> misAtracciones = new ArrayList<Atraccion>();
	Sugerencia promocion;
	Usuario miUsuario;

	@Before
	public void setup() {
		atraccion = new Atraccion("Moria", 10, 5.5, 3, Tipo.PAISAJE);
		atraccion2 = new Atraccion("La Cueva", 15, 3.5, 10, Tipo.PAISAJE);
		atraccion3 = new Atraccion("Cafe Vasco", 10, 3, 15, Tipo.AVENTURAS);
		misAtracciones.add(atraccion);
		misAtracciones.add(atraccion2);
		miUsuario = new Usuario("Jose", 100, 100, Tipo.AVENTURAS);
		promocion = new PromocionPorcentual("Promo Epica", misAtracciones, 0.15);
		itinerario = new Itinerario(miUsuario);
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
	public void obtenerDatosDelItinerario() {
		miUsuario.comprar(atraccion3);
		miUsuario.comprar(promocion);
	
		ArrayList<String> arrayEsperado = new ArrayList<String>();
		arrayEsperado.add("USUARIO: JOSE;SALDO INICIAL: 100 MONEDAS;TIEMPO INICIAL: 100.0 HS.\n\n");
		arrayEsperado.add("ESTE ES EL DETALLE DE TU ITINERARIO\n\n");
		arrayEsperado.add("Costo de tu Itinerario:;31;Duracion de tu Itinerario:;12.0\n\n");
		arrayEsperado.add("\nPromocion / Atraccion Comprada;Tipo;Costo;Duracion\n\n");
		arrayEsperado.add("Cafe Vasco;aventuras;10 monedas.;3.0 hs.\n");
		arrayEsperado.add("Promo Epica: incluye (Moria)(La Cueva);paisaje;21 monedas.;9.0 hs.\n");
		arrayEsperado.add("Te quedan 69 monedas y 88.0 hs.");

		ArrayList<String> arrayObtenido = miUsuario.getMiItinerario().obtenerDatosDeItinerario();

		assertEquals(arrayEsperado, arrayObtenido);
	}
}
