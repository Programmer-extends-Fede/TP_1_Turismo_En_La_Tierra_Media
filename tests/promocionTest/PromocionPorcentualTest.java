package promocionTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import promocion.Atraccion;
import promocion.Promocion;
import promocion.PromocionPorcentual;
import tipo.Tipo;

public class PromocionPorcentualTest {

	Atraccion atraccion1;
	Atraccion atraccion2;
	Atraccion atraccion3;
	Atraccion[] misAtracciones = new Atraccion[3];

	@Before
	public void setup() {
		atraccion1 = new Atraccion("Moria", 10, 2, 6, Tipo.AVENTURAS);
		atraccion2 = new Atraccion("Cueva Maldita", 15, 8, 3.5, Tipo.AVENTURAS);
		atraccion3 = new Atraccion("Zafari", 3, 10, 1, Tipo.AVENTURAS);
		misAtracciones[0] = atraccion1;
		misAtracciones[1] = atraccion2;
		misAtracciones[2] = atraccion3;
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
