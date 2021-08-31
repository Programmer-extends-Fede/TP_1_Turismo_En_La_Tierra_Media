package usuario;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
		
	}
	
	@Test
	public void queSeLeRestaDineroAlComprar() {
		
	}
	@Test
	public void queSeLeRestaTiempoAlComprar() {
		
	}

	@Test
	public void queNoPuedeComprarSiNoTieneDinero() {
		
	}

	@Test
	public void queNoPuedeComprarSiNoTieneTiempo() {

	}

}
