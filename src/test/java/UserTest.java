

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Model.PosiMina;
import Model.User;

public class UserTest {
	
	private User user;

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
	public void testUser() {
		assertNotNull(new User("Desconocido", 100));
		
	}

	@Test
	public void testGetNombre() {
		user = new User("Desconocido", 100);
		assertEquals("Desconocido", user.getNombre());
	}

	@Test
	public void testGetPuntuacion() {
		user = new User("Desconocido", 100);
		assertEquals(100, user.getPuntuacion());
	}

	@Test
	public void testSetNombre() {
		user = new User("Desconocido", 100);
		user.setNombre("Anonimo");
		assertEquals("Anonimo", user.getNombre());
		
	}
	
	@Test
	public void testSetResultado() {
		user = new User("Desconocido", 100);
		user.setResultado(50);
		assertEquals(50, user.getPuntuacion());
		
	}

}
