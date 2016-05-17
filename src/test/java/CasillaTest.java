/**
 * 
 */


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import Model.Casilla;
import javax.swing.*;

/**
 * @author Compaq
 *
 */
public class CasillaTest {

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
	 * Test method for {@link Model.Casilla#Casilla(boolean)}.
	 */
	@Test
	public final void testCasillaBoolean() {
		assertNotNull(new Casilla(true));

	}

	/**
	 * Test method for {@link Model.Casilla#Casilla(int, java.lang.String, int, int)}.
	 */
	@Test
	public final void testCasillaIntStringIntInt() {
		assertNotNull(new Casilla(-9,"bomba.jpg",0,0));

	}

	/**
	 * Test method for {@link Model.Casilla#getValor()}.
	 */
	@Test
	public final void testGetValor() {
		Casilla c1 = new Casilla(4,"4.PNG", 0, 1);
		assertEquals(4, c1.getValor());	
	}

	/**
	 * Test method for {@link Model.Casilla#getEstado()}.
	 */
	@Test
	public final void testGetEstado() {
		Casilla c1 = new Casilla(4,"4.PNG", 0, 1);
		assertEquals(0, c1.getEstado());	
	}

	/**
	 * Test method for {@link Model.Casilla#getMina()}.
	 */
	@Test
	public final void testGetMina() {
		Casilla c1 = new Casilla(4,"4.PNG", 0, 1);
		assertFalse(c1.getMina());
		Casilla c2 = new Casilla(-9,"9.PNG", 2, 1);
		assertTrue(c2.getMina());
	}

	/**
	 * Test method for {@link Model.Casilla#getI()}.
	 */
	@Test
	public final void testGetI() {
		Casilla c1 = new Casilla(4,"4.PNG", 0, 1);
		assertEquals(0, c1.getI());
	}

	/**
	 * Test method for {@link Model.Casilla#getJ()}.
	 */
	@Test
	public final void testGetJ() {
		Casilla c1 = new Casilla(4,"4.PNG", 0, 1);
		assertEquals(1, c1.getJ());
	}

	/**
	 * Test method for {@link Model.Casilla#getImagen()}.
	 */
	@Test
	public final void testGetImagen() {
		Casilla c1 = new Casilla(4,"4.PNG", 0, 1);
		assertEquals("4.PNG", c1.getImagen());
	}

	/**
	 * Test method for {@link Model.Casilla#setImg(java.lang.String)}.
	 */
	@Test
	public final void testSetImg() {
		Casilla c1 = new Casilla(4,"4.PNG", 0, 1);
		c1.setImg("9.PNG");
		assertEquals("9.PNG", c1.getImagen());
	}

	/**
	 * Test method for {@link Model.Casilla#setValor(int)}.
	 */
	@Test
	public final void testSetValor() {
		Casilla c1 = new Casilla(4,"4.PNG", 0, 1);
		c1.setValor(9);
		assertEquals(9, c1.getValor());
	}

	/**
	 * Test method for {@link Model.Casilla#setEstado(int)}.
	 */
	@Test
	public final void testSetEstado() {
		Casilla c1 = new Casilla(4,"4.PNG", 0, 1);
		c1.setEstado(1);
		assertEquals(1, c1.getEstado());
	}

	/**
	 * Test method for {@link Model.Casilla#setMina(boolean)}.
	 */
	@Test
	public final void testSetMina() {
		Casilla c1 = new Casilla(4,"4.PNG", 0, 1);
		c1.setMina(true);
		assertTrue(c1.getMina());
	}

	/**
	 * Test method for {@link Model.Casilla#setI(int)}.
	 */
	@Test
	public final void testSetI() {
		Casilla c1 = new Casilla(4,"4.PNG", 0, 1);
		c1.setI(2);
		assertEquals(2, c1.getI());
	}

	/**
	 * Test method for {@link Model.Casilla#setJ(int)}.
	 */
	@Test
	public final void testSetJ() {
		Casilla c1 = new Casilla(4,"4.PNG", 0, 1);
		c1.setJ(3);
		assertEquals(3, c1.getJ());
	}

}
