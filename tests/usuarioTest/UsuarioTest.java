package usuarioTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import atraccion.Atraccion;
import tipo.Tipo;
import usuario.Usuario;

public class UsuarioTest {

	Usuario u1;

	@Before
	public void inicializa() {
		u1 = new Usuario("Emir", 1500, 2, Tipo.AVENTURAS);
	}

	@Test
	public void noCreaNulo() {
		assertNotNull(u1);
	}
	
	@Test
	public void queSeLeRestaDineroAlComprar() {
		Atraccion miAtraccion = new Atraccion("VueltaAlMundo", 150, 0.5, 2, Tipo.AVENTURAS);
		u1.comprar(miAtraccion);
		assertEquals(1350, u1.getDineroDisponible());
	}
	
	@Test
	public void queSeLeRestaTiempoAlComprar() {
		Atraccion miAtraccion = new Atraccion("VueltaAlMundo", 150, 0.5, 2, Tipo.AVENTURAS);
		u1.comprar(miAtraccion);
		assertEquals(1.5, u1.getTiempoDisponible(), 0);
	}

}
