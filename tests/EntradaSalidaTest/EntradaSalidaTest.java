package EntradaSalidaTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entradaSalida.EntradaSalida;

public class EntradaSalidaTest {

	@Test
	public void cargarArchivoTest() {

		ArrayList<String> lecturaObtenida = EntradaSalida.cargarArchivoDe("Entrada/EntradaTest/EntradaEsperada.txt");
		String[] esperado = { "A", "B", "C" };
		ArrayList<String> lecturaEsperada = new ArrayList<String>(Arrays.asList(esperado));

		assertFalse(lecturaObtenida.isEmpty());
		assertEquals(lecturaEsperada, lecturaObtenida);
	}

	@Test
	public void guardarEnArchivoTest() {
		File archivoEsperado = new File("Salida/SalidaTest/nombreDePrueba.csv");
		ArrayList<String> arrayDePrueba = new ArrayList<String>();
		String rutaRelativa = "SalidaTest/nombreDePrueba";

		EntradaSalida.guardarEnArchivo(rutaRelativa, arrayDePrueba);

		assertTrue(archivoEsperado.exists());

	}

}
