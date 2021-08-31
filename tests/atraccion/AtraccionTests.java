package atraccion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AtraccionTests {
	
	Atraccion miAtraccion;
	
	@Before
	public void inicializa() {
		miAtraccion = new Atraccion("VueltaAlMundo", 150, 0.5, 2, Tipo.AVENTURA);
	}
	
	@Test
	public void noCreaNulo() {
		assertNotNull(miAtraccion);
	}
	
	@Test
	public void queSePuedeVender() {
		assertTrue(miAtraccion.vender());;
	}
	
	@Test
	public void queDescuentaUnCupoAlVender() {
		miAtraccion.vender();
		assertEquals(1, miAtraccion.getCupo());
	}
	
	@Test
	public void queNoSePuedeVenderSinCupo() {
		miAtraccion.vender();
		miAtraccion.vender();
		assertFalse(miAtraccion.vender());
	}

}
