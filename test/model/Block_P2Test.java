package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import model.exceptions.WrongMaterialException;

// TODO: Auto-generated Javadoc
/**
 * The Class Block_P2Test.
 */
public class Block_P2Test {

	/** The bedrock. */
	Material bedrock=Material.BEDROCK;
	
	/** The chest. */
	Material chest=Material.CHEST;
	
	/** The grass. */
	Material grass=Material.GRASS;
	
	/** The obsidian. */
	Material obsidian=Material.OBSIDIAN;
	
	/** The water. */
	Material water=Material.WATER_BUCKET;
	
	/** The shovel. */
	Material shovel=Material.IRON_SHOVEL;
	
	/** The wsword. */
	Material wsword=Material.WOOD_SWORD;
	
	
	/**
	 * Test constructor getters.
	 */
	@Test
	public final void testConstructorGetters() {
		
		Block bl;
		try {
			bl = new SolidBlock(grass);
			SolidBlock b = (SolidBlock)bl;
			assertTrue(b.getType()==grass);
			assertTrue(b.getDrops()==null);

			b = new SolidBlock(bedrock);
			assertTrue(b.getType()==bedrock);

			b = new SolidBlock(obsidian);
			assertTrue(b.getType()==obsidian);

			/*Block bb = new Block(b);
			assertTrue(bb.getType()==obsidian);
			assertNull(bb.getDrops());*/
		} catch (WrongMaterialException e) {
			fail("WrongMaterialException incorrectamente lanzada");
		} 
	}
	
	/**
	 * Test constructor exceptions.
	 */
	@Test
	public final void testConstructorExceptions() {
		Block b=null;
		try {
			b = new SolidBlock(water);
			fail("WrongMaterialException no se ha lanzado");
		} catch (WrongMaterialException e) {
			assertNull(b);
		} 
		
		try {
			b = new SolidBlock(shovel);
			fail("WrongMaterialException no se ha lanzado");
		} catch (WrongMaterialException e) {
			assertNull(b);
		} 
		
		try {
			b = new SolidBlock(wsword);
			fail("WrongMaterialException no se ha lanzado");
		} catch (WrongMaterialException e) {
			assertNull(b);
		} 
	}


	/**
	 * Test to string.
	 */
	//Prueba toString() para algunos bloques
	@Test
	public final void testToString() {
		Block b;
		try {
			b = new SolidBlock(grass);
			assertEquals("toString","[GRASS]", b.toString());

			b = new SolidBlock(bedrock);
			assertEquals("toString","[BEDROCK]", b.toString());

			b = new SolidBlock(obsidian);
			assertEquals("toString","[OBSIDIAN]", b.toString());
		} catch (WrongMaterialException e) {
			fail("WrongMaterialException incorrectamente lanzada");
		} 	
	}

	/**
	 * Test equals object.
	 */
	// Test para equals probando con cada uno de los atributos que deben intervenir
	@Test
	public void testEqualsObject() {
		Block b1;
		try {
			b1 = new SolidBlock(grass);
			assertFalse(b1.equals(null));
			assertTrue(b1.equals(b1));
			assertFalse(b1.equals(obsidian));
			
			//Distintos type
			SolidBlock b2=new SolidBlock (bedrock);
			b2.setDrops(shovel, 1);
			assertFalse(b1.equals(b2));
		} catch (Exception e) {
			fail("Error: excepci??n "+e.getClass().toString()+" inesperada");;
		}
	}
	
	/**
	 * Test hash code.
	 */
	//Test para hasCode() probando los atributos que deben intervenir
	@Test
	public void testHashCode() {
	  try {
		Block b1 = new SolidBlock(bedrock);
		Block b2 = new SolidBlock(bedrock);
		assertEquals(b1.hashCode(),b2.hashCode());
		
		b1= new SolidBlock(obsidian);
		assertNotEquals(b1.hashCode(), b2.hashCode());
	  } catch (Exception e) {
			fail("Error: excepci??n "+e.getClass().toString()+" inesperada");;
	  }
	}

}
