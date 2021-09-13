package tierraMediaTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import atraccion.Atraccion;
import tierraMedia.tierraMedia;
import tipo.Tipo;
import usuario.Usuario;

public class TierraMediaTest {

	@Before
	public void setup() {
		tierraMedia.borrarDatos();
	}

	public void construirUsuariostest() {
		assertTrue(tierraMedia.construirUsuarios());
		assertFalse(tierraMedia.getUsuarios().isEmpty());
		assertEquals(3, tierraMedia.getUsuarios().size());

		Usuario usuarioEsperado1 = new Usuario("Martin Suarez", 50, 20.0, Tipo.AVENTURAS);
		Usuario usuarioObtenido1 = tierraMedia.getUsuarios().get(0);
		assertEquals(usuarioEsperado1, usuarioObtenido1);
	}

	@Test
	public void contruirAtraccionesTest() {
		assertTrue(tierraMedia.construirAtracciones());
		assertFalse(tierraMedia.getAtracciones().isEmpty());
		assertEquals(8, tierraMedia.getAtracciones().size());

		Atraccion atraccionEsperada = new Atraccion("La Comarca", 3, 6.5, 150, Tipo.DEGUSTACION);
		Atraccion atraccionObtenida = tierraMedia.getAtracciones().get(2);
		assertEquals(atraccionEsperada, atraccionObtenida);
	}

	@Test
	public void construirPromocionesTest() {
		tierraMedia.construirAtracciones();
		assertTrue(tierraMedia.construirPromociones());
		assertFalse(tierraMedia.getPromociones().isEmpty());
		assertEquals(3, tierraMedia.getPromociones().size());
	}

	@Test
	public void construirPromocionesSinAtraccionesTest() {
		assertFalse(tierraMedia.construirPromociones());
		assertTrue(tierraMedia.getPromociones().isEmpty());
	}

	@Test
	public void construirSugerenciasTest() {
		tierraMedia.construirAtracciones();
		tierraMedia.construirPromociones();

		assertTrue(tierraMedia.construirSugerencias());
		assertFalse(tierraMedia.getSugerencias().isEmpty());
		assertEquals(11, tierraMedia.getSugerencias().size());
	}

	@Test
	public void construirSugerenciasSinAtraccionesTest() {
		assertFalse(tierraMedia.construirSugerencias());
		assertTrue(tierraMedia.getSugerencias().isEmpty());
	}
}
