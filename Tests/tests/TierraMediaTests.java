package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import TierraMedia.TierraMedia;
import junit.framework.Assert;
import tipo.Tipo;
import usuario.Usuario;

public class TierraMediaTests {

	@Test
	public void ConstruirUsuariostest() {
		TierraMedia.construirUsuarios();
		Usuario usuarioEsperado= new Usuario("Martin Suarez", 50, 20, Tipo.AVENTURAS);
		Usuario usuarioObtenido= TierraMedia.getUsuarios().get(0);
		
		assertEquals(usuarioEsperado, usuarioObtenido);
	}

}
