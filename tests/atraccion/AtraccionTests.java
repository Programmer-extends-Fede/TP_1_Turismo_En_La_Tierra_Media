package atraccion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import tipo.Tipo;

public class AtraccionTests {
	
	Atraccion miAtraccion;
	
	@Before
	public void inicializa() {
		miAtraccion = new Atraccion("VueltaAlMundo", 150, 0.5, 2, Tipo.AVENTURAS);
	}
	
	@Test
	public void noCreaNulo() {
		assertNotNull(miAtraccion);
	}
	
	@Test
	public void queSePuedeVenderYDescuentaUnCupo() {
		miAtraccion.vender();
		assertEquals(1, miAtraccion.getCupo());;
	}
	
	/* Esto no se valida acá
	@Test
	public void queNoSePuedeVenderSinCupo() {
		miAtraccion.vender();
		miAtraccion.vender();
		assertFalse(miAtraccion.vender());
	}*/

}
