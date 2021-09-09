package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import TierraMedia.TierraMedia;
import atraccion.Atraccion;
import junit.framework.Assert;
import tipo.Tipo;
import usuario.Usuario;

public class TierraMediaTests {

	@Test
	public void construirUsuariostest() {
		Usuario usuarioEsperado1 = new Usuario("Martin Suarez", 50, 20.0, Tipo.AVENTURAS);
		TierraMedia.construirUsuarios();
		Usuario usuarioObtenido1 = TierraMedia.getUsuarios().get(0);

		assertTrue(TierraMedia.construirUsuarios());
		assertEquals(usuarioEsperado1, usuarioObtenido1);
		assertNotNull(TierraMedia.getUsuarios().get(2));
	}

	@Test
	public void contruirAtraccionesTest() {
		TierraMedia.construirAtracciones();
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
		TierraMedia.construirPromociones();
		assertTrue(TierraMedia.construirPromociones());
		assertNotNull(TierraMedia.getPromociones().get(2));
	}

}
