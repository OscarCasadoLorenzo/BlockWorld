package model;

import static org.junit.Assert.*;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class Material_P2Test.
 */
public class Material_P2Test {

	/** The types alumno. */
	final  Material typesAlumno[]=Material.values();
	
	/** The materias. */
	final  Material materias[] = { Material.BEDROCK, Material.CHEST, Material.SAND, Material.DIRT, Material.GRASS, 
			Material.STONE, Material.GRANITE, Material.OBSIDIAN, Material.WATER_BUCKET, 
			Material.APPLE, Material.BREAD, Material.BEEF, Material.IRON_SHOVEL,Material.IRON_PICKAXE, 
			Material.WOOD_SWORD, Material.IRON_SWORD, Material.LAVA, Material.WATER, Material.AIR };
	
	/** The values. */
	final double values[]= {-1, 0.1, 0.5, 0.5, 0.6, 1.5, 1.5, 5, 1, 4, 5, 8, 0.2, 0.5, 1, 2, 1.0, 0.0, 0.0};
	
	/** The symbols. */
	final char symbols[]= {'*','C','n','d','g','s','r','o','W','A','B','F','>','^','i','I', '#', '@', ' '};

	/**
	 * Test types.
	 */
	//Comprueba el orden de los tipos de materias del Enum del alumno
	@Test
	public void testTypes() {
		for (int i = 0; i<=15;i++) {
			  assertEquals (materias[i], typesAlumno[i]);  
		}
	}

	/**
	 * Test is block.
	 */
	@Test
	public void testIsBlock() {
		for (int i = 0; i<=17;i++) {
		  if (( (i>=0) && (i<=7) )|| (i>15) )
			  assertTrue(materias[i].isBlock());
		  else 
			  assertFalse(materias[i].isBlock());
		}
	}

	/**
	 * Test is edible.
	 */
	@Test
	public void testIsEdible() {
		for (int i = 0; i<=17;i++) {
			  if ( (i>=8) && (i<=11) )
				  assertTrue(materias[i].isEdible());
			  else 
				  assertFalse(materias[i].isEdible());
		}
	}
	
	/**
	 * Test is tool.
	 */
	@Test
	public void testIsTool() {
		for (int i = 0; i<=17;i++) {
			  if ( (i>=12) && (i<=13) )
				  assertTrue(materias[i].isTool());
			  else 
				  assertFalse(materias[i].isTool());
		}
	}

	/**
	 * Test is weapon.
	 */
	@Test
	public void testIsWeapon() {
		for (int i = 0; i<=17;i++) {
			  if ( (i>=14) && (i<=15) )
				  assertTrue(materias[i].isWeapon());
			  else 
				  assertFalse(materias[i].isWeapon());
		}
	}
	
	/**
	 * Test is liquid.
	 */
	@Test
	public void testIsLiquid() {
		for (int i = 0; i<=17;i++) {
			  if ( (i>=16) && (i<=17) )
				  assertTrue(materias[i].isLiquid());
			  else 
				  assertFalse(materias[i].isLiquid());
		}
	}
	
	/**
	 * Test get value.
	 */
	@Test
	public void testGetValue() {
		for (int i = 0; i<=17;i++) {
			  assertEquals ("Value["+i+"] == getValue()", values[i], materias[i].getValue(),0.01);
		}
	}

	/**
	 * Test get symbol.
	 */
	@Test
	public void testGetSymbol() {
		for (int i = 0; i<=17;i++){
				  assertEquals ("symbol["+i+"] == getSymbol()",symbols[i], materias[i].getSymbol());
			}
	}

	/**
	 * Test get random item 1.
	 */
	@Test
	public void testGetRandomItem1() {
		for (int i = 0; i<=18;i++) {
			Material mat = Material.getRandomItem(i,i);
			assertTrue (materias[i]==mat);
		}
	}

	/**
	 * Test get random item 2.
	 */
	//Indices fuera del rango
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetRandomItem2() {
			 Material.getRandomItem(19,20);
	}
	
	/**
	 * Test get random item 3.
	 */
	//Indices fuera del rango
		@Test(expected = ArrayIndexOutOfBoundsException.class)
		public void testGetRandomItem3() {
			
				 Material.getRandomItem(-5,-1);
		}
	
	/**
	 * Test get random item 4.
	 */
	//Indice superior menor que el inferior
	@Test(expected = IllegalArgumentException.class)
	public void TestGetRandomItem4() {
		 Material.getRandomItem(7,6);
	}

}
