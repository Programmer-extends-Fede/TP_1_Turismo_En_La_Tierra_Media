package tierraMediaTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import TierraMedia.TierraMedia;

public class TierraMediaTest {
	
	@Before
	public void setup() {
		TierraMedia.borrarDatos();
	}
	
	@Test
	public void construirUsuariosTest() {
		assertTrue(TierraMedia.construirUsuarios());
		assertNotNull(TierraMedia.getUsuarios());
	}
	
	@Test
	public void construirAtracciones() {
		assertTrue(TierraMedia.construirAtracciones());
		assertFalse(TierraMedia.getAtracciones().isEmpty());
	}
	
	@Test
	public void construirPromocionesConAtracciones() {
		TierraMedia.construirAtracciones();
		
		assertTrue(TierraMedia.construirPromociones());
		assertFalse(TierraMedia.getPromociones().isEmpty());
	}
	
	@Test
	public void construirPromocionesSinAtracciones() {
		assertFalse(TierraMedia.construirPromociones());
		assertTrue(TierraMedia.getPromociones().isEmpty());
	}
	
	@Test
	public void construirSugerenciasConAtraccionTest() {
		TierraMedia.construirAtracciones();
		
		assertTrue(TierraMedia.construirSugerencias());
		assertFalse(TierraMedia.getSugerencias().isEmpty());
	}
	
	@Test
	public void construirSugerenciasSinAtraccionTest() {
		assertFalse(TierraMedia.construirSugerencias());
		assertTrue(TierraMedia.getSugerencias().isEmpty());
	}
}
