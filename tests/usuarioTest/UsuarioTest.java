package usuarioTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import atraccion.Atraccion;
import tipo.Tipo;
import usuario.Usuario;

public class UsuarioTest {

	Usuario usuario;

	@Before
	public void setup() {
		usuario = new Usuario("Emir", 50, 2, Tipo.AVENTURAS);
	}

	@Test
	public void crearUsuarioTest() {
		assertNotNull(usuario);
	}
	
	@Test
	public void comprarTest() {
		Atraccion miAtraccion = new Atraccion("VueltaAlMundo", 15, 0.5, 2, Tipo.AVENTURAS);
		usuario.comprar(miAtraccion);
		
		int dineroDispObtenido = usuario.getDineroDisponible();
		int dineroDispEsperado = 35;
		double tiempoDispObtenido = usuario.getTiempoDisponible();
		double tiempoDispEsperado = 1.5;
		
		assertEquals(dineroDispEsperado, dineroDispObtenido);
		assertEquals(tiempoDispEsperado, tiempoDispObtenido,0);
	}
}
