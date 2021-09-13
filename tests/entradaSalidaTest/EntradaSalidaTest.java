package entradaSalidaTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entradaSalidaTierraMediaConsola.EntradaSalida;

public class EntradaSalidaTest {

	@Test
	public void cargarArchivoTest() {

		ArrayList<String> lecturaObtenida = EntradaSalida.cargarArchivoDe("EntradaTest/EntradaObtenidaTest.csv");
		String[] esperado = { "A", "B", "C" };
		ArrayList<String> lecturaEsperada = new ArrayList<String>(Arrays.asList(esperado));

		assertFalse(lecturaObtenida.isEmpty());
		assertEquals(lecturaEsperada, lecturaObtenida);
	}

	@Test
	public void guardarEnArchivoTest() {

		File archivoEsperado = new File("Salida/SalidaTest/ArchivoSalidaTest.csv");
		ArrayList<String> arrayDePrueba = new ArrayList<String>();
		String rutaRelativa = "SalidaTest/ArchivoSalidaTest";

		EntradaSalida.guardarEnArchivo(arrayDePrueba, rutaRelativa);

		assertTrue(archivoEsperado.exists());
	}
}
