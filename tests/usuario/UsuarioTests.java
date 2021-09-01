package usuario;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import atraccion.Atraccion;
import atraccion.Tipo;

public class UsuarioTests {

	Usuario u1;

	@Before
	public void inicializa() {
		u1 = new Usuario("Emir", 1500, 2, Tipo.AVENTURA);
	}

	@Test
	public void noCreaNulo() {
		assertNotNull(u1);
	}

	@Test
	public void quePuedeComprar() {
		Atraccion miAtraccion = new Atraccion("VueltaAlMundo", 150, 0.5, 2, Tipo.AVENTURA);
		assertTrue(u1.comprar(miAtraccion));
	}
	
	@Test
	public void queSeLeRestaDineroAlComprar() {
		Atraccion miAtraccion = new Atraccion("VueltaAlMundo", 150, 0.5, 2, Tipo.AVENTURA);
		u1.comprar(miAtraccion);
		assertEquals(1350, u1.getDineroDisponible());
	}
	
	@Test
	public void queSeLeRestaTiempoAlComprar() {
		Atraccion miAtraccion = new Atraccion("VueltaAlMundo", 150, 0.5, 2, Tipo.AVENTURA);
		u1.comprar(miAtraccion);
		assertEquals(1.5, u1.getTiempoDisponible(), 0);
	}

	@Test
	public void queNoPuedeComprarSiNoTieneDinero() {
		Usuario u2 = new Usuario("Jair", 100, 1, Tipo.AVENTURA);
		Atraccion miAtraccion = new Atraccion("VueltaAlMundo", 150, 0.5, 2, Tipo.AVENTURA);
		assertFalse(u2.comprar(miAtraccion));
	}

	@Test
	public void queNoPuedeComprarSiNoTieneTiempo() {
		Atraccion miAtraccion = new Atraccion("VueltaAlMundo", 150, 0.5, 2, Tipo.AVENTURA);
		assertTrue(u1.comprar(miAtraccion));
		assertTrue(u1.comprar(miAtraccion));
		assertTrue(u1.comprar(miAtraccion));
		assertTrue(u1.comprar(miAtraccion));
		assertFalse(u1.comprar(miAtraccion));
	}

}
