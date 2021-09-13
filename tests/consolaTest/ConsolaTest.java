package consolaTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import entradaSalidaTierraMediaConsola.Consola;
import entradaSalidaTierraMediaConsola.EntradaSalida;
import sugerenciaPromocionAtraccion.Atraccion;
import sugerenciaPromocionAtraccion.Promocion;
import sugerenciaPromocionAtraccion.PromocionPorcentual;
import tipo.Tipo;
import usuarioItinerario.Usuario;

public class ConsolaTest {

	Usuario usuario;
	Atraccion atraccion;
	Atraccion atraccion2;
	Atraccion atraccion3;
	ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
	Promocion promo;

	@Before
	public void setup() {
		usuario = new Usuario("ObtenidoTest", 15, 5, Tipo.PAISAJE);
	}

	@Test
	public void guardarElItinerarioTest() {
		atraccion = new Atraccion("El Coliseo", 2, 1.5, 1, Tipo.PAISAJE);
		atraccion2 = new Atraccion("La Moria", 8, 1.5, 3, Tipo.DEGUSTACION);
		atraccion3 = new Atraccion("La Comarca", 5, 1, 6, Tipo.AVENTURAS);
		atraccionesPromo.add(atraccion2);
		atraccionesPromo.add(atraccion3);
		promo = new PromocionPorcentual("Promo X", atraccionesPromo, 0.20);

		usuario.comprar(atraccion);
		usuario.comprar(promo);
		EntradaSalida.guardarEnArchivo(usuario.obtenerDatosDelItinerario(),
				"ConsolaTest/Itinerario ObtenidoTest");

		BufferedInputStream ArchivoSalidaObtenido = null;
		BufferedInputStream ArchivoSalidaEsperado = null;
		try {
			ArchivoSalidaObtenido = new BufferedInputStream(
					new FileInputStream("Salida/ConsolaTest/Itinerario ObtenidoTest.csv"));
			ArchivoSalidaEsperado = new BufferedInputStream(
					new FileInputStream("Salida/ConsolaTest/Itinerario EsperadoTest.csv"));

			assertArrayEquals(ArchivoSalidaEsperado.readAllBytes(), ArchivoSalidaObtenido.readAllBytes());
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void usuarioPuedeComprarTest() {
		int dineroDisp = usuario.getDineroDisponible();
		double tiempoDisp = usuario.getTiempoDisponible();
		Atraccion atraccion = new Atraccion("El Coliseo", dineroDisp, tiempoDisp, 1, Tipo.PAISAJE);

		assertTrue(Consola.puedeComprar(atraccion, usuario));
	}

	@Test
	public void usuarioNoLeAlcanzaDineroTest() {
		int dineroDisp = usuario.getDineroDisponible();
		double tiempoDisp = usuario.getTiempoDisponible();
		Atraccion atraccion = new Atraccion("El Coliseo", dineroDisp + 1, tiempoDisp, 1, Tipo.PAISAJE);

		assertFalse(Consola.puedeComprar(atraccion, usuario));
	}

	@Test
	public void usuarioNoLeAlcanzaTiempoTest() {
		int dineroDisp = usuario.getDineroDisponible();
		double tiempoDisp = usuario.getTiempoDisponible();
		Atraccion atraccion = new Atraccion("El Coliseo", dineroDisp, tiempoDisp + 1, 1, Tipo.PAISAJE);

		assertFalse(Consola.puedeComprar(atraccion, usuario));
	}

	@Test
	public void mostrarSugerenciaSitieneCupoTest() {
		Atraccion atraccion = new Atraccion("El Coliseo", 2, 2, 1, Tipo.PAISAJE);

		assertTrue(Consola.tieneCupo(atraccion));
	}

	@Test
	public void mostrarSugerenciaSiNoTieneCupoTest() {
		Atraccion atraccion = new Atraccion("El Coliseo", 2, 2, 1, Tipo.PAISAJE);
		atraccion.restarCupo();

		assertFalse(Consola.tieneCupo(atraccion));
	}

	@Test
	public void mostrarSiSugerenciaNoSeComproTest() {
		atraccion = new Atraccion("El Coliseo", 10, 5, 1, Tipo.PAISAJE);
		atraccion2 = new Atraccion("La Moria", 12, 6, 3, Tipo.DEGUSTACION);
		atraccion3 = new Atraccion("La Comarca", 20, 2, 6, Tipo.AVENTURAS);
		Consola.agregarAtraccionComprada(atraccion2);
		Consola.agregarAtraccionComprada(atraccion3);

		assertTrue(Consola.noSeCompro(atraccion));
		assertFalse(Consola.noSeCompro(atraccion2));
		assertFalse(Consola.noSeCompro(atraccion3));
	}
}
