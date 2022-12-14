package model;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.Test;

import model.entities.Player;
import model.exceptions.BadInventoryPositionException;
import model.exceptions.BadLocationException;
import model.exceptions.EntityIsDeadException;
import model.exceptions.StackSizeException;

// TODO: Auto-generated Javadoc
/**
 * The Class Player_P2Test.
 */
public class Player_P2Test {
	
	/** The out initial player. */
	final String outInitialPlayer = "Name=Peter\n"+
			"Location{world=Mercury,x=0.0,y=71.0,z=0.0}\n"+
			"Orientation=Location{world=Mercury,x=0.0,y=0.0,z=1.0}\n"+
			"Health=20.0\n"+
			"Food level=20.0\n"+
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n";
	
	/** The out player. */
	final String outPlayer = "Name=Peter\n"+
			"Location{world=Mercury,x=1.0,y=71.0,z=1.0}\n"+ 
	"Orientation=Location{world=Mercury,x=0.0,y=0.0,z=1.0}\n"+
	"Health=10.0\n"+
	"Food level=3.95\n"+
	"Inventory=(inHand=(BREAD,5),[(BEDROCK,5), (CHEST,5), (SAND,5), (DIRT,5), "+
	"(GRASS,5), (STONE,5), (GRANITE,5), (OBSIDIAN,5), (WATER_BUCKET,5), (APPLE,5), "+
	"(WOOD_SWORD,1), (BEEF,5), (IRON_SHOVEL,1), (IRON_PICKAXE,1), (WOOD_SWORD,1), (IRON_SWORD,1), "
	+ "(LAVA,5), (WATER,5), (AIR,5)])";

	/** The player null. */
	Player player, playerNull;
	
	/** The mercury. */
	static World mercury;
	
	/** The newpos. */
	Location locPlayer, newpos;
	
	/** The materias. */
	Material materias[]=Inventory_P2Test.materias;
	
	/** The inventory. */
	static Field inventory;
	
	/** The jugador. */
	static Object jugador;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */

	@Before
	public void setUp() throws Exception {
		Material.rng.setSeed(1L);
		mercury = new World(3,2,"Mercury", "Steve");
		player = new Player("Peter",mercury);
		locPlayer = new Location(mercury, 0,71,0);
		newpos = null;
	}

	/**
	 * Test getters player.
	 */
	@Test
	public void testGettersPlayer() {
		assertEquals("getName()", "Peter",player.getName());
		Location locPlayer = new Location(mercury, 0,71,0);
		assertEquals("getLocation()", locPlayer ,player.getLocation());
		assertEquals("getHealth()", Player.MAX_HEALTH, player.getHealth(),0.01);
		assertEquals("getFoodLevel()", Player.MAX_FOODLEVEL, player.getFoodLevel(),0.01);
		assertEquals("getInventorySize()", 0, player.getInventorySize());
	}
	
	/**
	 * Test composicion get location.
	 */
	@Test
	public void testComposicionGetLocation() {
		Location locPlayer2 = player.getLocation();
		assertNotSame("Composici??n en getLocation() (copia defensiva)", locPlayer2 ,player.getLocation());
	}
	
	/**
	 * Test set health.
	 */
	/* Comprueba valores admitidos de salud en setHealth() */
	@Test
	public void testSetHealth() {
		player.setHealth(5.5);
		assertEquals("Health = 5.5", 5.5, player.getHealth(),0.01);
		player.setHealth(-10.01);
		assertEquals("Health = -10.01", -10.01, player.getHealth(),0.01);
		player.setHealth(Player.MAX_HEALTH-0.01);
		assertEquals("Health = 19.99", 19.99, player.getHealth(),0.01);
		
	}
	
	/**
	 * Test set health out of limit.
	 */
	//SetHealth para valores fuera del l??mite
	@Test
	public void testSetHealthOutOfLimit() {
		player.setHealth(5.0);
		player.setHealth(100.01);
		assertEquals("Health = 20", Player.MAX_HEALTH, player.getHealth(),0.01);
		player.setHealth(20.1);
		assertEquals("Health = 20", Player.MAX_HEALTH, player.getHealth(),0.01);
	}

	/**
	 * Test set food level.
	 */
	//SetFood para valores dentro del l??mite
	@Test
	public void testSetFoodLevel() {
		player.setFoodLevel(5.5);
		assertEquals("FoodLevel = 5.5", 5.5, player.getFoodLevel(),0.01);
		player.setFoodLevel(-10.01);
		assertEquals("FoodLevel = -10.01", -10.01, player.getFoodLevel(),0.01);
		player.setFoodLevel(19.99);
		assertEquals("FoodLevel = 19.99", Player.MAX_FOODLEVEL-0.01, player.getFoodLevel(),0.01);
	}

	/**
	 * Test set food level out of limit.
	 */
	//SetFoodLevel para valores fuera del l??mite
		@Test
		public void testSetFoodLevelOutOfLimit() {
			player.setFoodLevel(5.0);
			player.setFoodLevel(100.01);
			assertEquals("FoodLevel = 20", Player.MAX_FOODLEVEL, player.getHealth(),0.01);
			player.setFoodLevel(20.1);
			assertEquals("FoodLevel = 20", Player.MAX_FOODLEVEL, player.getHealth(),0.01);
		}
		
	/**
	 * Test is dead.
	 */
	//Testamos isDead()
	@Test
	public void testIsDead() {
		//Inicialmente no est?? muerto
		assertFalse(player.isDead());
		
		//No es foodLevel quien decide si est?? muerto
		player.setFoodLevel(-10);
		assertFalse(player.isDead());
		
		//Est?? al l??mite
		player.setHealth(0.001);
		assertFalse(player.isDead());
		
		//Lo matamos
		player.setHealth(0.0);
		assertTrue(player.isDead());
		player.setHealth(-0.001);
		assertTrue(player.isDead());
	}

	//Movimientos posibles controlando nuevas posiciones y nuevos valores food/health
	
	/**
	 * Test move ok 1.
	 */
	//Se mueve player decrementado solo foodLevel
	@Test
	public void testMoveOk1() {
		
		try {
			
			Location loc = player.getLocation();
			newpos = player.move(0, 1, 0);
			assertEquals("Nueva posici??n",newpos, player.getLocation());
			assertEquals("Decrementa food en 0.05", 19.95, player.getFoodLevel(),0.01);
			assertEquals("Health sigue a 20", 20.0, player.getHealth(),0.01);
			
			newpos=player.move(0, 1, 1);
			assertEquals("Nueva posici??n",newpos, player.getLocation());
			assertEquals("Decrementa food en 0.05", 19.90, player.getFoodLevel(),0.01);
			assertEquals("Health sigue a 20", 20.0, player.getHealth(),0.01);
						
		} catch (Exception e) {
			fail("Error: no debi?? lanzar la excepci??n "+e.getClass().toString());
		}
	}
	
	/**
	 * Test composicion move.
	 */
	@Test
	public void testComposicionMove() {
		try {
			Location loc = player.getLocation();
			newpos = player.move(0, 1, 0);
			assertNotSame("Composicion en Player.move() (copia defensiva)", loc, newpos);
		} catch (Exception e) {
			fail("Error: no debi?? lanzar la excepci??n "+e.getClass().toString());
		}
	}
	
	/**
	 * Test composicion move almost dead player.
	 */
	@Test
	public void testComposicionMoveAlmostDeadPlayer() {
		try {
			Location loc = player.getLocation();
			player.setHealth(0.04);
			player.setFoodLevel(0.01);
			newpos=player.move(0, 0, 1);
			assertNotSame("Composicion en Player.move() con jugador muerto.", loc, newpos);
		} catch (Exception e) {
			fail("Error: no debi?? lanzar la excepci??n "+e.getClass().toString());
		}
	}
	
	/**
	 * Test move ok 2.
	 */
	//Vamos a ajustar foodLevel y mover el player para comprobar si baja en health
	@Test
	public void testMoveOk2() {
				
		try {
						
			player.setFoodLevel(0.05);
			
			newpos=player.move(1, 1, 0);
			assertEquals("Nueva posici??n",newpos, player.getLocation());
			assertEquals("Deja a food en 0.0", 0.0, player.getFoodLevel(),0.01);
			assertEquals("Health sigue a 20", 20.0, player.getHealth(),0.01);
			
			newpos=player.move(0, 0, 1);
			assertEquals("Nueva posici??n",newpos, player.getLocation());
			assertEquals("food se queda en 0.0", 0.0, player.getFoodLevel(),0.01);
			assertEquals("Health baja a 19.95", Player.MAX_HEALTH-0.05, player.getHealth(),0.01);
			
			newpos=player.move(0, -1, 0);
			assertEquals("Nueva posici??n",newpos, player.getLocation());
			assertEquals("food se queda en 0.0", 0.0, player.getFoodLevel(),0.01);
			assertEquals("Health baja a 19.90", Player.MAX_HEALTH-0.10, player.getHealth(),0.01);
		
		} catch (Exception e) {
				fail("Error: no debi?? lanzar la excepci??n "+e.getClass().toString());
		}
	}
	
	
	/**
	 * Test move ok 3.
	 */
	/* Comprueba si hay reparto de puntos entre foodLevel y health al mover el player 
	con foodlevel casi a 0 */
	@Test
	public void testMoveOk3() {
		
		try {
				
			player.setFoodLevel(0.02);
			newpos=player.move(1, -1, 1);
			assertEquals("Nueva posici??n",newpos, player.getLocation());
			assertEquals("food se queda en 0.0", 0.0, player.getFoodLevel(),0.01);
			assertEquals("Health baja a 19.97", Player.MAX_HEALTH-0.03, player.getHealth(),0.01);
			
		} catch (Exception e) {
			fail("Error: no debi?? lanzar la excepci??n "+e.getClass().toString());
		}
	}
	
	/**
	 * Test move ok 4.
	 */
	//Vamos a matar al player
	@Test
	public void testMoveOk4() {
		
		try {	
			
			player.setHealth(0.04);
			player.setFoodLevel(0.01);
			newpos=player.move(0, 0, 1);
			assertEquals("Nueva posici??n",newpos, player.getLocation());
			assertEquals("food se queda en 0.0", 0.0, player.getFoodLevel(),0.01);
			assertEquals("Health baja a -0.01", 0.0, player.getHealth(),0.001);
			assertTrue(player.isDead());	
			
		} catch (Exception e) {
			fail("Error: no debi?? lanzar la excepci??n "+e.getClass().toString());
		}
	}
	
	/**
	 * Test move error 1.
	 *
	 * @throws BadLocationException the bad location exception
	 * @throws EntityIsDeadException the entity is dead exception
	 */
	//Movimiento a una posici??n no adyacente
	@Test(expected=BadLocationException.class)
	public void testMoveError1() throws BadLocationException, EntityIsDeadException{
			
		//Movemos a posici??n no adyacente
		newpos = player.move(0, 73, 0);
	}

	/**
	 * Test move error 2.
	 *
	 * @throws BadLocationException the bad location exception
	 * @throws EntityIsDeadException the entity is dead exception
	 */
	//Movimiento de un Player muerto
	@Test(expected=EntityIsDeadException.class)
	public void testMoveError2() throws BadLocationException, EntityIsDeadException {
			player.setHealth(0.04);
			player.setFoodLevel(0.0);
			newpos=player.move(0, 0, 1);
			Location loc = new Location(newpos.getWorld(),0,71,1);
			assertEquals("Nueva posici??n",loc, player.getLocation());
			newpos=player.move(1, 0, 0); //Imposible, player is dead
	}
		
	/**
	 * Test move error 3.
	 *
	 * @throws BadLocationException the bad location exception
	 * @throws EntityIsDeadException the entity is dead exception
	 */
	//Movimiento a una posici??n ocupada por un bloque
	@Test(expected=BadLocationException.class)
	public void testMoveError3() throws BadLocationException, EntityIsDeadException {
			newpos=player.move(0, -1, 0); //En esa posici??n hay un bloque
	}
		
	/**
	 * Test move error 4.
	 *
	 * @throws BadLocationException the bad location exception
	 * @throws EntityIsDeadException the entity is dead exception
	 */
	//Movimiento a una posici??n fuera de los l??mtes del mundo
	@Test(expected=BadLocationException.class)
	public void testMoveError4() throws BadLocationException, EntityIsDeadException {
			newpos=player.move(-1, -1, 0); //Esa posici??n no pertenece al mundo
	}
 
	
	/**
	 * Test use item in hand 1.
	 */
	//Pruebas 1 con useItemInHand
	@Test
	public void testUseItemInHand1() {
		try {
			ItemStack food = new ItemStack(Material.BEEF,5);
			ItemStack tool = new ItemStack(Material.IRON_PICKAXE,1);
			ItemStack block = new ItemStack(Material.GRANITE, 3);
			player.addItemsToInventory(food);	
			player.addItemsToInventory(tool);	
			player.addItemsToInventory(block);	
			assertEquals(20.0,player.getFoodLevel(),0.001);
			assertEquals(20.0,player.getHealth(),0.001);
			
			//usamos WOOD_SWORD 210 veces y vemos que foodLevel queda a 0 y health a 19
			player.useItemInHand(210);
			assertEquals(0.0,player.getFoodLevel(),0.001);
			assertEquals(19.0,player.getHealth(),0.001);
			
		} catch (Exception e) {
			fail("Error, se produjo la excepci??n: "+e.getClass().getName());
		}
		
		
	}

	/**
	 * Test use item in hand 2.
	 */
	//Pruebas 1 con useItemInHand
	@Test
	public void testUseItemInHand2() {
		try {
			ItemStack food = new ItemStack(Material.BEEF,5);
			ItemStack tool = new ItemStack(Material.IRON_PICKAXE,1);
			ItemStack block = new ItemStack(Material.GRANITE, 3);
			player.addItemsToInventory(food);	
			player.addItemsToInventory(tool);	
			player.addItemsToInventory(block);	
			
			
			//Ponemos health a 1 y usamos (BEEF,5) 3 veces. Vemos como satura foodLevel en 20 
			// e incrementa la salud en 4
			player.setHealth(1);
			player.setFoodLevel(0.0);
			player.selectItem(0);
			player.useItemInHand(3);
			assertEquals(20.0,player.getFoodLevel(),0.001);
			assertEquals(5.0,player.getHealth(),0.001);
			
		} catch (Exception e) {
			fail("Error, se produjo la excepci??n: "+e.getClass().getName());
		}
		
		
	}

	/**
	 * Test use item in hand 3.
	 */
	//Pruebas 1 con useItemInHand
	@Test
	public void testUseItemInHand3() {
		try {
			ItemStack food = new ItemStack(Material.BEEF,5);
			ItemStack tool = new ItemStack(Material.IRON_PICKAXE,1);
			ItemStack block = new ItemStack(Material.GRANITE, 3);
			player.addItemsToInventory(food);	
			player.addItemsToInventory(tool);	
			player.addItemsToInventory(block);	
			
			//Seleccionamos material de granito y lo usamos 50 veces.
			player.setHealth(5.0);
			player.selectItem(2);
			player.useItemInHand(50);
			assertEquals(15.0,player.getFoodLevel(),0.001);
			assertEquals(5.0,player.getHealth(),0.001);
			
		} catch (Exception e) {
			fail("Error, se produjo la excepci??n: "+e.getClass().getName());
		}
		
		
	}

	/**
	 * Test use item in hand 4.
	 */
	//Pruebas 2 con useItemInHand
	@Test
	public void testUseItemInHand4() {
		try {
			//A??adimos 3 elementos al inventario
			ItemStack food = new ItemStack(Material.BEEF,4);
			ItemStack tool = new ItemStack(Material.IRON_SHOVEL,1);
			ItemStack block = new ItemStack(Material.GRANITE, 3);
			player.addItemsToInventory(food);	
			player.addItemsToInventory(tool);	
			player.addItemsToInventory(block);	
			assertEquals(3,player.getInventorySize());
			
			//Ponemos foodLevel a 0 y health a 1
			player.setFoodLevel(0);
			player.setHealth(1);
			
			// usamos (BEEF,4) 5 veces. Vemos como satura foodLevel en 20 
			// e incrementa la salud en 12
			player.selectItem(0);
			player.useItemInHand(5);
			assertEquals(20.0,player.getFoodLevel(),0.001);
			assertEquals(13.0,player.getHealth(),0.001);
		} catch (Exception e) {
			fail("Error, se produjo la excepci??n: "+e.getClass().getName());
		}
	}

	/**
	 * Test use item in hand 5.
	 */
	@Test
	public void testUseItemInHand5() {
		try {
			//A??adimos 3 elementos al inventario
			ItemStack food = new ItemStack(Material.BEEF,4);
			ItemStack tool = new ItemStack(Material.IRON_SHOVEL,1);
			ItemStack block = new ItemStack(Material.GRANITE, 3);
			player.addItemsToInventory(food);	
			player.addItemsToInventory(tool);	
			player.addItemsToInventory(block);	
			
			//Ponemos foodLevel a 0 y health a 1
			player.setFoodLevel(0);
			player.setHealth(1);
			
			// usamos (BEEF,4) 5 veces. Vemos como satura foodLevel en 20 
			// e incrementa la salud en 12
			player.selectItem(0);
			player.useItemInHand(5);
			
			//useInHand es null, lo usamos y no debe pasar nada
			player.useItemInHand(50);
			assertEquals(20.0,player.getFoodLevel(),0.001);
			assertEquals(13.0,player.getHealth(),0.001);
			
		} catch (Exception e) {
			fail("Error, se produjo la excepci??n: "+e.getClass().getName());
		}
	}

	/**
	 * Test use item in hand 6.
	 */
	@Test
	public void testUseItemInHand6() {
		try {
			//A??adimos 3 elementos al inventario
			ItemStack food = new ItemStack(Material.BEEF,4);
			ItemStack tool = new ItemStack(Material.IRON_SHOVEL,1);
			ItemStack block = new ItemStack(Material.GRANITE, 3);
			player.addItemsToInventory(food);	
			player.addItemsToInventory(tool);	
			player.addItemsToInventory(block);	
			
			//Ponemos foodLevel a 0 y health a 1
			player.setFoodLevel(0);
			player.setHealth(1);
			
			// usamos (BEEF,4) 5 veces. Vemos como satura foodLevel en 20 
			// e incrementa la salud en 12
			player.selectItem(0);
			player.useItemInHand(5);
			
			//useInHand es null, lo usamos y no debe pasar nada
			player.useItemInHand(50);
			
			//Cogemos IRON_SHOVEL y ahora el inventario debe de tener un elemento menos
			player.selectItem(0);
			assertEquals(2,player.getInventorySize());
			
			
		} catch (Exception e) {
			fail("Error, se produjo la excepci??n: "+e.getClass().getName());
		}
	}

	/**
	 * Test use item in hand 7.
	 */
	@Test
	public void testUseItemInHand7() {
		try {
			//A??adimos 3 elementos al inventario
			ItemStack food = new ItemStack(Material.BEEF,4);
			ItemStack tool = new ItemStack(Material.IRON_SHOVEL,1);
			ItemStack block = new ItemStack(Material.GRANITE, 3);
			player.addItemsToInventory(food);	
			player.addItemsToInventory(tool);	
			player.addItemsToInventory(block);	
			
			//Ponemos foodLevel a 0 y health a 1
			player.setFoodLevel(0);
			player.setHealth(1);
			
			// usamos (BEEF,4) 5 veces. Vemos como satura foodLevel en 20 
			// e incrementa la salud en 12
			player.selectItem(0);
			player.useItemInHand(5);
			
			//useInHand es null, lo usamos y no debe pasar nada
			player.useItemInHand(50);
			
			//Cogemos IRON_SHOVEL y ahora el inventario debe de tener un elemento menos
			player.selectItem(0);
			
			//Lo usamos 330 veces y player muere
			player.useItemInHand(330);
			assertEquals(0.0,player.getFoodLevel(),0.001);
			assertEquals(0.0,player.getHealth(),0.001);
			assertTrue(player.isDead());		
			
		} catch (Exception e) {
			fail("Error, se produjo la excepci??n: "+e.getClass().getName());
		}
	}

	/**
	 * Test use item in hand exceptions.
	 */
	//Pruebas de salto de excepciones IllegalArgumentException y EntityIsDeadException
		@Test
		public void testUseItemInHandExceptions() {
			try {
				//A??adimos 3 elementos al inventario
				ItemStack food = new ItemStack(Material.BEEF,4);
				ItemStack tool = new ItemStack(Material.IRON_SHOVEL,1);
				ItemStack block = new ItemStack(Material.GRANITE, 3);
				player.addItemsToInventory(food);	
				player.addItemsToInventory(tool);	
				player.addItemsToInventory(block);	
				assertEquals(3,player.getInventorySize());
				
				//Excepci??n por uso de -1 vez el WOOD_SWORD
				try {
				   player.useItemInHand(-1);
				   fail("Error, no se produjo la excepci??n IllegalArgumentException");
				}catch (IllegalArgumentException e1) {
					assertEquals(20.0,player.getFoodLevel(),0.001);
					assertEquals(20.0,player.getHealth(),0.001);
					//Excepci??n por uso de un player muerto
					player.useItemInHand(400); //Lo matamos
					try {
						player.useItemInHand(1);
						fail("Error, no se produjo la excepci??n EntityIsDeadException");
					}catch (EntityIsDeadException e2) {
						assertEquals(0.0,player.getFoodLevel(),0.001);
						assertEquals(0.0,player.getHealth(),0.001);
					}
				}
			} catch (Exception e) {
				fail("Error, se produjo la excepci??n: "+e.getClass().getName());
			}
		}
		
	

	/**
	 * Test select item.
	 */
	/* Hay un inventario con 1 (APPLE,5). En la mano ponemos APPLE y lo usamos todo.
	 * Ahora en la mano no tiene nada y se pone el elemento 0 en la mano.
	 * Inventario queda a 0
	 */
	@Test
	public void testSelectItem() {
		try {
		  ItemStack food = new ItemStack(Material.APPLE,5);
		  player.addItemsToInventory(food);
		  assertEquals("Inventario 1 Item",1,player.getInventorySize());
		  
		  player.selectItem(0);
		  assertEquals("Intercambio Materiales",1,player.getInventorySize());
		  
		  player.useItemInHand(5); 
		  player.selectItem(0); //Pone de nuevo WOOD_SWORD
		  assertEquals("Cogi?? elemento sin intercambio",0,player.getInventorySize()); 
		} catch (Exception e) {
			fail("Error: no debi?? lanzar la excepci??n "+e.getClass().toString());
		}
		
	}
	
	/**
	 * Test select item exception.
	 */
	// selecItem produce Exception BadInventoryPositionException 
	@Test
	public void testSelectItemException() {
	 try {
		try {
		  player.selectItem(0);
		  fail("Error, no se produjo la excepci??n BadInventoryPositionException");
		} catch (BadInventoryPositionException e1) {
		   ItemStack food = new ItemStack(Material.APPLE,5);
		   player.addItemsToInventory(food);
		   try {
			   player.selectItem(1);
			   fail("Error, no se produjo la excepci??n BadInventoryPositionException");
		   } catch (BadInventoryPositionException e2) {
			   assertEquals(1,player.getInventorySize());
		   }
		}
	 } catch (Exception e) {
			fail("Error: no debi?? lanzar la excepci??n "+e.getClass().toString());
	 }	
	}

	/**
	 * Test add items to inventory and get inventory size.
	 */
	/* A??ade todos los materiales al inventario y se comprueba que el tama??o del mismo
	 * se va incrementando */
	@Test
	public void testAddItemsToInventoryAndGetInventorySize() {
		  int i=0;
		  assertEquals("Inventario vac??o", 0, player.getInventorySize());
		  try {
		   for (Material m : Material.values()) {
				player.addItemsToInventory(new ItemStack(m,1));	
				assertTrue(player.getInventorySize()==++i);
			
		   }
		  } catch (StackSizeException e) {
				fail("Error: no debi?? lanzar la excepci??n "+e.getClass().toString());
		  } 
	}

	
	/**
	 * Test to string initial player.
	 */
	//Player creado, sin elementos en el inventario.
	@Test
	public void testToStringInitialPlayer() {
		compareLines(outInitialPlayer, player.toString());
	}

	//Player con todos los atributos de player modificados. Algunos m??todos deben
	/**
	 * Test to string player.
	 */
	//funcionar ya correctamente.
	@Test
	public void testToStringPlayer() {
		int quantity;
		//Inciamos el inventario con elementos y nuevos valores en health, foodLevel y location
		try {
		  for (Material m : Material.values()) {
			  quantity = (m.isBlock()|| m.isEdible()) ? 5 : 1;
			  player.addItemsToInventory(new ItemStack(m,quantity));	
		  }
		  player.setHealth(10.0);
		  player.setFoodLevel(4.0);
		  player.move(1, 0, 1);
		  player.selectItem(Material.BREAD.ordinal());	  
		  compareLines(outPlayer, player.toString());
		} catch (Exception e){
			fail("Error: no debi?? lanzar la excepci??n "+e.getClass().toString());
		}
	}
	
	/**
	 * Test equals object.
	 *
	 * @throws RuntimeException the runtime exception
	 */
	//Probamos todas las posibilidades de equals con los atributos que intervienen
	@Test
	public void testEqualsObject() throws RuntimeException {
		Player player2 = new Player("Peter",mercury);
		assertFalse(player.equals(null));
		assertTrue(player.equals(player));
		assertFalse(player.equals(mercury));
		assertEquals(player,player2);
		//Modificamos alg??n atributo:
		//name
		Player player3 = new Player("John",mercury);
		assertNotEquals(player2, player3);
		
		//foodLevel
		player2.setFoodLevel(Player.MAX_FOODLEVEL-1);
		assertNotEquals(player, player2);
		
		//health
		player2 = new Player("Peter",mercury);
		player2.setHealth(Player.MAX_HEALTH-1);
		assertNotEquals(player, player2);
		
		//location
		player2 = new Player("Peter",mercury);
		try {
			player2.move(1, 0, 1);
		
		} catch (Exception e) {
			fail("Error: no debi?? lanzar la excepci??n "+e.getClass().toString());
		}
		assertNotEquals(player, player2);
		
		//inventory iguales
		player2 = new Player("Peter",mercury);
		ItemStack is=null;
		try {
			is = new ItemStack(Material.APPLE,5);
			player2.addItemsToInventory(is);
			player.addItemsToInventory(new ItemStack(is));
			assertEquals(player, player2);
		} catch (Exception e) {
			fail("Error: no debi?? lanzar la excepci??n "+e.getClass().toString());
		}
		//Inventory distintos	
		try {
			player.addItemsToInventory(new ItemStack(is));
			assertNotEquals(player, player2);
		} catch (Exception e) {
			fail("Error: no debi?? lanzar la excepci??n "+e.getClass().toString());
		}		
	}
	
	/**
	 * Test hash code.
	 *
	 * @throws RuntimeException the runtime exception
	 */
	/* Testamos hasCode() con los atributos que intervienen, comprobando que si modificamos alguno
	 * los codes son distintos */
	@Test
	public void testHashCode() throws RuntimeException{
		Player player2 = new Player("Peter",mercury);
		assertEquals("Codes iguales para players iguales",player.hashCode(),player2.hashCode());
		//Modificamos alg??n atributo:
		//name
		Player player3 = new Player("John",mercury);
		assertNotEquals("Codes distintos para players con name distintos", player3.hashCode(),player2.hashCode());
		
		//foodLevel
		player2.setFoodLevel(Player.MAX_FOODLEVEL-1);
		assertNotEquals("Codes distintos para players con foodLevel distintos",player.hashCode(),player2.hashCode());
		
		//health
		player2 = new Player("Peter",mercury);
		player2.setHealth(Player.MAX_HEALTH-1);
		assertNotEquals("Codes distintos para players con health distintos",player.hashCode(),player2.hashCode());
		
		//location
		player2 = new Player("Peter",mercury);
		try {
			player2.move(1, 0, 1);
		
		} catch (Exception e) {
			fail("Error: no debi?? lanzar la excepci??n "+e.getClass().toString());
		}
		assertNotEquals("Codes distintos para players con location distintos",player.hashCode(),player2.hashCode());
		
		//inventory iguales
		player2 = new Player("Peter",mercury);
		ItemStack is=null;
		try {
			is = new ItemStack(Material.APPLE,5);
			player2.addItemsToInventory(is);
			player.addItemsToInventory(new ItemStack(is));
			assertEquals("Codes iguales para players con inventory iguales",player.hashCode(),player2.hashCode());
		} catch (Exception e) {
			fail("Error: no debi?? lanzar la excepci??n "+e.getClass().toString());
		}
		//Inventory distintos	
		try {
			player.addItemsToInventory(new ItemStack(is));
			assertNotEquals("Codes distintos para players con inventory distintos",player.hashCode(),player2.hashCode());
		} catch (Exception e) {
			fail("Error: no debi?? lanzar la excepci??n "+e.getClass().toString());
		}		
	}
	
        /**
         * ***************************************************************.
         *
         * @param expected the expected
         * @param result the result
         */
        //FUNCION DE APOYO
        /* Se usa para comparar Strings que devuelven algunos m??todos. Evita sacar error por los espacios
         * finales de cada l??nea y por los saltos de l??nea al final.
         * Los valores con decimales son comparados con un margen de error de 0.01
         */
        void compareLines(String expected, String result) {
                String exp[]=expected.split("\n");
                String res[]=result.split("\n");
                boolean iguales = true;
                for (int i=0; i<exp.length && iguales; i++) {

                        // si la l??nea es Health=1.2345  o Food level=1.2345  
                        // se extraen los n??meros y se comparan con 0.01 de margen
                        String ee[]=exp[i].split("=");
                        if (ee[0].equals("Health") || ee[0].equals("Food level")) {
                                String rr[]=res[i].trim().split("=");
                                if (ee[0].equals(rr[0])) {
                                        double ed = Double.parseDouble(ee[1]);
                                        double rd = Double.parseDouble(rr[1]);

                                        assertEquals(ee[0],ed,rd,0.01);
                                } else
                                    fail("linea "+i+": \""+ee[0]+"\" != \""+rr[0]+"\"");
                        }
                        else {
			    	            String myexp=exp[i].trim().replaceAll("world= ", "world=");
			    	            String myres=res[i].trim().replaceAll("world= ", "world=");
					            assertEquals("linea "+i, myexp,myres);
			               }
                }
        }

}
