package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class LocationInventoryWorldMaterial_P4Test.
 */
public class LocationInventoryWorldMaterial_P4Test {
	
	/** The w. */
	World w;
	
	/** The is grass. */
	static ItemStack isApple, isGrass;
	
	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void  setUpBeforeClass() throws Exception {
		isApple= new ItemStack(Material.APPLE, ItemStack.MAX_STACK_SIZE);
		isGrass = new ItemStack(Material.GRASS, 5);
	}

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		w =  new World(5, 20, "World 10x10","Raul");
	}

	/**
	 * Test compare to different X.
	 */
	//TESTS P4 PARA Location
	@Test
	public void testCompareToDifferentX() {
		Location loc1 = new Location(w, 7, 5, -3);
		Location loc2 = new Location(w, 6.9, 7, -2);
		assertTrue (loc1.compareTo(loc2)>0);
		assertTrue (loc2.compareTo(loc1)<0);
	}

	/**
	 * Test compare to same X different Y.
	 */
	@Test
	public void testCompareToSameXDifferentY() {
		Location loc1 = new Location(w, 7, 6.9, -3);
		Location loc2 = new Location(w, 7, 7, -5);
		assertTrue (loc1.compareTo(loc2)<0);
		assertTrue (loc2.compareTo(loc1)>0);
	}
	
	/**
	 * Test compare to same XY different Z.
	 */
	@Test
	public void testCompareToSameXYDifferentZ() {
		Location loc1 = new Location(w, 7, 20, -3);
		Location loc2 = new Location(w, 7, 20, -2.9);
		assertTrue (loc1.compareTo(loc2)<0);
		assertTrue (loc2.compareTo(loc1)>0);
	}
	
	/**
	 * Test compare to same XYZ.
	 */
	@Test
	public void testCompareToSameXYZ() {
		Location loc1 = new Location(w, 7, 20, -2.9);
		Location loc2 = new Location(w, 7, 20, -2.9);
		assertTrue (loc1.compareTo(loc2)==0);
		assertTrue (loc2.compareTo(loc1)==0);
	}
	
	//TEST P4 PARA Inventory
	/**
	 * Test constructor copia inventory 1.
	 */
	/* Comprueba que el constructor copia de un inventario vac??o crea otro igual vac??o */
	@Test
	public void testConstructorCopiaInventory1() {
		Inventory inv1 = new Inventory();
		Inventory inv2 = new Inventory(inv1);
		assertEquals(inv1,inv2);
		inv1.addItem(isApple);
		assertNotEquals(inv1,inv2);
	}
	
	/**
	 * Test constructor copia inventory 2.
	 */
	/* Crea un inventario y a??adele algunos items y pon uno en su mano. Crea un inventario 
	 * nuevo copia del anterior. Comprueba que ambos son iguales
	 */
	@Test
	public void testConstructorCopiaInventory2() {
		Inventory inv1 = new Inventory();
		
			inv1.addItem(isApple);
			inv1.addItem(isGrass);
			inv1.addItem(new ItemStack(isApple));
			inv1.addItem(new ItemStack(isGrass));
			inv1.setItemInHand(new ItemStack(isGrass));
			Inventory inv2 = new Inventory(inv1);
			assertEquals(inv1,inv2);	
	}
		
	//TEST P4 PARA World
	/**
	 * Test constructor world.
	 */
	//Comprueba que el nombre del jugador del mundo w es "Raul".
	@Test
	public void testConstructorWorld() {
		
		assertEquals("Raul",w.getPlayer().getName());
	}
	
	//TEST P4 PARA Material
	/**
	 * Test AIR.
	 */
	/* Comprueba que el material AIR est?? en la posici??n 18 de los materiales. Que es un bloque 
	 * y es l??quido nada m??s. Que su valor es 0 y su s??mbolo ' ' */
	@Test
	public void testAIR() {
		Material air = Material.AIR;
		assertEquals(18,air.ordinal());
		assertTrue(air.isBlock());
		assertTrue(air.isLiquid());
		assertFalse(air.isEdible());
		assertFalse(air.isTool());
		assertFalse(air.isWeapon());
		assertEquals(0,air.getValue(),0.01);
		assertEquals(' ',air.getSymbol());
	}
}
