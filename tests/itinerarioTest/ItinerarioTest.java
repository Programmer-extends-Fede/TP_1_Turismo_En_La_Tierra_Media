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

public class ItinerarioTest {

	Itinerario itinerario;
	Atraccion atraccion;
	Atraccion atraccion2;
	ArrayList<Atraccion> misAtracciones = new ArrayList<Atraccion>();
	Sugerencia promocion;

	@Before
	public void setup() {
		atraccion = new Atraccion("Moria", 10, 5.5, 3, Tipo.PAISAJE);
		atraccion2 = new Atraccion("La Cueva", 15, 3.5, 10, Tipo.PAISAJE);
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
		
		int costoObtenido= itinerario.getCostoDelItinerario();
		int costoDeseado = 46;
		double duracionObtenida = itinerario.getDuracionDelItinerario();
		double duracionDeseada = 18.0;
		
		assertFalse(itinerario.getSugerenciasDiarias().isEmpty());
		assertEquals(costoDeseado, costoObtenido);
		assertEquals(duracionDeseada, duracionObtenida, 0);
	}
}
