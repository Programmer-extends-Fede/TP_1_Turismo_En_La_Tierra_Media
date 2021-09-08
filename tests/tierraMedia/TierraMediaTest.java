package tierraMedia;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import tipo.Tipo;
import usuario.Usuario;

public class TierraMediaTest {

	@Test
	public void testConstruirArrayListUsuarios() {
		ArrayList<Usuario> usuariosEsperados= new ArrayList<Usuario>();
		usuariosEsperados.add(new Usuario("Martin Iriarte", 36, 15.0, Tipo.AVENTURAS));
				//{"Usuario [nombre=Martin Iriarte, dineroDisponible=36, tiempoDisponible=15.0, preferencia=Aventuras",
				//"Usuario [nombre=Jose Perez, dineroDisponible=15, tiempoDisponible=100.0, preferencia=Paisaje]",
				//"Usuario [nombre=Monica Garcia, dineroDisponible=3, tiempoDisponible=25.0, preferencia=Degustacion]",
//"Usuario [nombre=Jesica Solis, dineroDisponible=100, tiempoDisponible=2.0, preferencia=Degustacion]",
//"Usuario [nombre=Agustina Echeto, dineroDisponible=50, tiempoDisponible=45.0, preferencia=Paisaje]"};
		TierraMedia.construirUsuarios();
		ArrayList <Usuario> usuariosObtenidos= TierraMedia.getUsuarios();
		Assert.assertEquals(usuariosEsperados, usuariosObtenidos);
	}

}
