package promocionTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import atraccion.Atraccion;
import promocion.Promocion;
import promocion.PromocionPorcentual;
import tipo.Tipo;

public class PromocionPorcentualTest {

	Atraccion atraccion1;
	Atraccion atraccion2;
	Atraccion atraccion3;
	ArrayList<Atraccion> misAtracciones = new ArrayList<Atraccion>();

	@Before
	public void setup() {
		atraccion1 = new Atraccion("Moria", 10, 2, 6, Tipo.AVENTURAS);
		atraccion2 = new Atraccion("Cueva Maldita", 15, 3.5, 8, Tipo.AVENTURAS);
		atraccion3 = new Atraccion("Zafari", 3, 10, 1, Tipo.AVENTURAS);
		misAtracciones.add(atraccion1);
		misAtracciones.add(atraccion2);
		misAtracciones.add(atraccion3);
	}

	@Test
	public void crearPromocionTestv() {
		Promocion miPromo = new PromocionPorcentual("Pack 1", misAtracciones, Tipo.AVENTURAS, 0.15);

		assertNotNull(miPromo);
	}

	@Test
	public void obtenerPrecioDePromocionTest() {
		Promocion miPromo = new PromocionPorcentual("Pack 1", misAtracciones, Tipo.AVENTURAS, 0.15);
		int precioObtenido = miPromo.getPrecio();
		int precioEsperado = 23;

		assertEquals(precioEsperado, precioObtenido);
	}

	@Test
	public void obtenerCupoDePromocionTest() {
		Promocion miPromo = new PromocionPorcentual("Pack 1", misAtracciones, Tipo.AVENTURAS, 0.15);
		int cupoObtenido = miPromo.getCupo();
		int cupoEsperado = 2;

		assertEquals(cupoEsperado, cupoObtenido);
	}

	@Test
	public void venderPromocionTest() {
		Promocion miPromo = new PromocionPorcentual("Pack 1", misAtracciones, Tipo.AVENTURAS, 0.15);
		miPromo.vender();
		int cupoObtenido = miPromo.getCupo();
		int cupoEsperado = 1;

		assertEquals(cupoEsperado, cupoObtenido);
	}

	@Test
	public void obtenerDuracionDePromocionTest() {
		Promocion miPromo = new PromocionPorcentual("Pack 1", misAtracciones, Tipo.AVENTURAS, 0.15);
		double duracionObtenida = miPromo.getDuracion();
		double duracionEsperada = 10.5;

		assertEquals(duracionEsperada, duracionObtenida, 0);
	}
}