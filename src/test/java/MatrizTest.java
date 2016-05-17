

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Model.Casilla;
import Model.Matriz;
import Model.NivelDificultad;
import Model.PosiMina;

public class MatrizTest {
	
	private Matriz matriz;
	private NivelDificultad nivel= NivelDificultad.FACIL; 
		

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
	public void testConstruirMatriz() {
		Casilla[][]valores=Matriz.getMatriz().construirMatriz(nivel);
		assertNotNull(valores);		
	}

	@Test
	public void testGetMatriz() {
		assertNotNull(Matriz.getMatriz());
	}

	@Test
	public void testGenerarMinasTablero() {
		assertNotNull(Matriz.getMatriz().generarMinasTablero(nivel));
		
	}

	
}
