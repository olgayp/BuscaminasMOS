

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Model.NivelDificultad;

public class NivelDificultadTest {
	
	private NivelDificultad nd ;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void testGetNivel() {
		nd = NivelDificultad.FACIL;
		assertEquals(1,nd.getNivel());
	}

	@Test
	public void testGetNumFilas() {
		
		nd = NivelDificultad.FACIL;
		assertEquals(7,nd.getNumFilas());
		
	}

	@Test
	public void testGetNumColumnas() {
		nd = NivelDificultad.MEDIO;
		assertEquals(12,nd.getNumColumnas());
	}

	@Test
	public void testGetNumMinas() {
		nd = NivelDificultad.DIFICIL;
		assertEquals(45,nd.getNumMinas());
	}

}
