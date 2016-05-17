

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Grafica.Tablero;
import Model.Buscaminas;
import Model.NivelDificultad;
import junit.framework.Assert;

public class BuscaminasTest {
	private NivelDificultad nivel=NivelDificultad.FACIL;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Tablero tablero = new Tablero();
		Buscaminas.getConBuildTablero().guardarDatos(NivelDificultad.FACIL, "Desconocido", tablero);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testGetConBuildTablero() {
		assertNotNull(Buscaminas.getConBuildTablero());
	}


	@Test
	public void testGetNivel() {
		assertEquals(NivelDificultad.FACIL,Buscaminas.getConBuildTablero().getNivel());
	}

	@Test
	public void testGetUser() {
		assertEquals("Desconocido",Buscaminas.getConBuildTablero().getUser());
		
		
	}

	@Test
	public void testFichero() {
		assertEquals("fichero-l1.txt", Buscaminas.getConBuildTablero().getFichero());
		
	}


}
