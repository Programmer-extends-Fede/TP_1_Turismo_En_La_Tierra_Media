package ordenarSugerenciasTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import ordenarSugerencias.OrdenarSugerencias;
import sugerenciaPromocionAtraccion.Atraccion;
import sugerenciaPromocionAtraccion.Promocion;
import sugerenciaPromocionAtraccion.PromocionAbsoluta;
import sugerenciaPromocionAtraccion.PromocionPorcentual;
import sugerenciaPromocionAtraccion.Sugerencia;
import tipo.Tipo;

public class OrdenarSugerenciasTest {

	@Test
	public void crearOrdenarTest() {
		OrdenarSugerencias miOrden = new OrdenarSugerencias(null);

		assertNotNull(miOrden);
	}

	@Test
	public void ordenCorrectoTest() {
		OrdenarSugerencias miOrden  = new OrdenarSugerencias(Tipo.AVENTURAS);
		Atraccion atraccion1 = new Atraccion("Erebor", 12, 6, 1, Tipo.PAISAJE);
		Atraccion atraccion2 = new Atraccion("Abismo de Helm", 12, 2, 15, Tipo.DEGUSTACION);
		Atraccion atraccion3 = new Atraccion("Minas Tirith", 10, 2.5, 25, Tipo.AVENTURAS);
		Atraccion atraccion4 = new Atraccion("Lothloriem", 35, 1, 30, Tipo.DEGUSTACION);
		Atraccion atraccion5 = new Atraccion("Mordor", 10, 3, 4, Tipo.AVENTURAS);
		Atraccion[] misAtracciones = {atraccion1, atraccion2, atraccion3, atraccion4, atraccion5};
		
		ArrayList<Atraccion> atraccionesAventura = new ArrayList<Atraccion>();
		atraccionesAventura.add(atraccion3);
		atraccionesAventura.add(atraccion5);
		ArrayList<Atraccion> atraccionesDegustacion = new ArrayList<Atraccion>();
		atraccionesDegustacion.add(atraccion2);
		atraccionesDegustacion.add(atraccion4);
		Promocion promo1 = new PromocionAbsoluta("Pack Degustacion", atraccionesDegustacion, 36);
		Promocion promo2 = new PromocionPorcentual("Pack Aventuras", atraccionesAventura, 0.20);
		
		ArrayList<Sugerencia> ordenObtenido = new ArrayList<Sugerencia>();
		ordenObtenido.addAll(Arrays.asList(misAtracciones));
		ordenObtenido.add(promo1);
		ordenObtenido.add(promo2);
		ordenObtenido.sort(miOrden);
		ArrayList<Sugerencia> ordenEsperado = new ArrayList<Sugerencia>();
		ordenEsperado.add(promo2);
		ordenEsperado.add(promo1);
		ordenEsperado.add(atraccion5);
		ordenEsperado.add(atraccion3);
		ordenEsperado.add(atraccion4);
		ordenEsperado.add(atraccion1);
		ordenEsperado.add(atraccion2);
		
		assertEquals(ordenEsperado, ordenObtenido);
	}
}
