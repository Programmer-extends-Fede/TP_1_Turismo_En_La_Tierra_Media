package tierraMediaTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import entradaSalidaTierraMediaConsola.TierraMedia;
import sugerenciaPromocionAtraccion.Atraccion;
import tipo.Tipo;
import usuarioItinerario.Usuario;

public class TierraMediaTest {

	@Before
	public void setup() {
		TierraMedia.borrarDatos();
	}

	public void construirUsuariostest() {
		assertTrue(TierraMedia.construirUsuarios());
		assertFalse(TierraMedia.getUsuarios().isEmpty());
		assertEquals(3, TierraMedia.getUsuarios().size());

		Usuario usuarioEsperado1 = new Usuario("Martin Suarez", 50, 20.0, Tipo.AVENTURAS);
		Usuario usuarioObtenido1 = TierraMedia.getUsuarios().get(0);
		assertEquals(usuarioEsperado1, usuarioObtenido1);
	}

	@Test
	public void contruirAtraccionesTest() {
		assertTrue(TierraMedia.construirAtracciones());
		assertFalse(TierraMedia.getAtracciones().isEmpty());
		assertEquals(8, TierraMedia.getAtracciones().size());

		Atraccion atraccionEsperada = new Atraccion("La Comarca", 3, 6.5, 150, Tipo.DEGUSTACION);
		Atraccion atraccionObtenida = TierraMedia.getAtracciones().get(2);
		assertEquals(atraccionEsperada, atraccionObtenida);
	}

	@Test
	public void construirPromocionesTest() {
		TierraMedia.construirAtracciones();
		assertTrue(TierraMedia.construirPromociones());
		assertFalse(TierraMedia.getPromociones().isEmpty());
		assertEquals(3, TierraMedia.getPromociones().size());
	}

	@Test
	public void construirPromocionesSinAtraccionesTest() {
		assertFalse(TierraMedia.construirPromociones());
		assertTrue(TierraMedia.getPromociones().isEmpty());
	}

	@Test
	public void construirSugerenciasTest() {
		TierraMedia.construirAtracciones();
		TierraMedia.construirPromociones();

		assertTrue(TierraMedia.construirSugerencias());
		assertFalse(TierraMedia.getSugerencias().isEmpty());
		assertEquals(11, TierraMedia.getSugerencias().size());
	}

	@Test
	public void construirSugerenciasSinAtraccionesTest() {
		assertFalse(TierraMedia.construirSugerencias());
		assertTrue(TierraMedia.getSugerencias().isEmpty());
	}
}
