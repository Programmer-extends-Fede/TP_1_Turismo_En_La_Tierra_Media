package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import TierraMedia.TierraMedia;
import atraccion.Atraccion;
import tipo.Tipo;
import usuario.Usuario;

public class TierraMediaTests {

	@Test
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
		assertNotNull(TierraMedia.getAtracciones().get(5));
		assertEquals(8, TierraMedia.getAtracciones().size());

		Atraccion atraccionEsperada = new Atraccion("La Comarca", 3, 6.5, 150, Tipo.DEGUSTACION);
		Atraccion atraccionObtenida = TierraMedia.getAtracciones().get(2);
		assertEquals(atraccionEsperada, atraccionObtenida);
	}

	@Test
	public void construirPromocionesTest() {
		
		TierraMedia.construirAtracciones();
		assertTrue(TierraMedia.construirPromociones());
		assertNotNull(TierraMedia.getPromociones());
		assertEquals(3, TierraMedia.getPromociones().size());

	}

	@Test
	public void construirSugerenciasYOrdenarlasTest() {
		
		TierraMedia.construirAtracciones();
		TierraMedia.construirPromociones();
		
		assertTrue(TierraMedia.construirSugerencias());
		assertNotNull(TierraMedia.getSugerencias());
		assertEquals(11, TierraMedia.getSugerencias().size());
		
		TierraMedia.ordenarSugerencias(Tipo.PAISAJE);
		
		assertTrue(TierraMedia.getSugerencias().get(0).esPromocion());
		assertTrue(TierraMedia.getSugerencias().get(1).esPromocion());
		assertTrue(TierraMedia.getSugerencias().get(2).esPromocion());
		assertEquals(Tipo.PAISAJE, TierraMedia.getSugerencias().get(0).getTipo());
		assertEquals(Tipo.PAISAJE, TierraMedia.getSugerencias().get(3).getTipo());

	}

}
