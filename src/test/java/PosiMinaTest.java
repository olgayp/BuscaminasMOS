/**
 * 
 */


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Model.PosiMina;

/**
 * @author Compaq
 *
 */
public class PosiMinaTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link Model.PosiMina#PosiMina(int, int)}.
	 */
	@Test
	public final void testPosiMina() {
		assertNotNull(new PosiMina(1,2));
	}

	/**
	 * Test method for {@link Model.PosiMina#getFbomb()}.
	 */
	@Test
	public final void testGetFbomb() {
		PosiMina pm1 = new PosiMina(1,3);
		assertEquals(1,pm1.getFbomb());
	}

	/**
	 * Test method for {@link Model.PosiMina#getCbomb()}.
	 */
	@Test
	public final void testGetCbomb() {
		PosiMina pm1 = new PosiMina(1,3);
		assertEquals(3,pm1.getCbomb());
	}

}
