

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Model.Casilla;
import Model.Matriz;
import Model.MatrizBuilder;
import Model.NivelDificultad;

public class MatrizBuilderTest {

	private static NivelDificultad nivel;
	private Casilla[][] matriz;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		nivel=NivelDificultad.FACIL;
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
	public void testCrearMatriz() {
		Matriz matriz=new Matriz(nivel);
		assertNotNull(matriz);
	}

	@Test
	public void testGetMatrizBuilder() {
		assertEquals(matriz,MatrizBuilder.getMatrizBuilder());
	}


}
