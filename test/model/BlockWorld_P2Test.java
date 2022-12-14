package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockWorld_P2Test.
 */
public class BlockWorld_P2Test {

	
	/** The Constant STARTPLAY1. */
	final static String STARTPLAY1 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=0.0,y=66.0,z=0.0}\n" + 
			"Health=20.0\n" + 
			"Food level=20.0\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			".>. ggg ddd\n" + 
			"... gPW dgg\n" + 
			"... ... g..";
	
	/** The Constant HALFPLAY1. */
	final static String HALFPLAY1 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=0.0,y=67.0,z=-1.0}\n" + 
			"Health=2.8500000000000014\n" + 
			"Food level=0.0\n" + 
			"Inventory=(inHand=(IRON_SHOVEL,1),[(WATER_BUCKET,1), (WOOD_SWORD,1)])\n" + 
			"... ... gg.\n" + 
			"... .P. ggg\n" + 
			"... ... g..";
	
	/** The Constant ENDPLAY1. */
	final static String ENDPLAY1 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=0.0,y=67.0,z=-1.0}\n" + 
			"Health=-0.14999999999999858\n" + 
			"Food level=0.0\n" + 
			"Inventory=(inHand=(IRON_SHOVEL,1),[(WOOD_SWORD,1)])\n" + 
			"... ... gg.\n" + 
			"... .P. ggg\n" + 
			"... ... g..";
	
	/** The Constant STARTPLAY2. */
	final static String STARTPLAY2 ="Name=Steve\n" + 
			"Location{world=World 3x3,x=0.0,y=63.0,z=0.0}\n" +
			"Orientation=Location{world=World 3x3,x=0.0,y=0.0,z=1.0}\n"+
			"Health=20.0\n" + 
			"Food level=20.0\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			"... .B. gg.\n" + 
			"... .P. gg.\n" + 
			"... ... g..";
	
	/** The Constant HALFPLAY2. */
	final static String HALFPLAY2 ="Name=Steve\n" + 
			"Location{world=World 3x3,x=0.0,y=61.0,z=1.0}\n" + 
			"Health=20.0\n" + 
			"Food level=9.749999999999996\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[(IRON_SWORD,1), (WATER_BUCKET,3)])\n" + 
			"gg. dd. dd.\n" + 
			"g.. dP. dA.\n" + 
			"XXX XXX XXX";
	
	/** The Constant ENDPLAY2. */
	final static String ENDPLAY2 ="Name=Steve\n" + 
			"Location{world=World 3x3,x=0.0,y=60.0,z=1.0}\n" + 
			"Health=-0.30000000000000426\n" + 
			"Food level=0.0\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[(IRON_SWORD,1)])\n" + 
			"dd. dd. dd.\n" + 
			"d.. dP. dgg\n" + 
			"XXX XXX XXX";
	
	/** The bw. */
	BlockWorld bw;
	
	/** The world 5 x 5. */
	World world5x5;
	
	/** The world 3 x 3. */
	World world3x3;
	
	/** The world 10 x 10. */
	World world10x10;
	
	
	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		Material.rng.setSeed(1L);
		bw = BlockWorld.getInstance();
		world3x3 =  bw.createWorld(1, 3, "World 3x3", "Steve");
	//	world5x5 =  bw.createWorld(4, 5, "World 5x5");
	}

	/**
	 * Test get instance.
	 */
	@Test
	public void testGetInstance() {
		BlockWorld bw=BlockWorld.getInstance();
		assertNotNull(bw);
		assertSame (bw, BlockWorld.getInstance());
	}

	/**
	 * Test create world.
	 *
	 * @throws RuntimeException the runtime exception
	 */
	@Test
	public void testCreateWorld() throws RuntimeException{
		BlockWorld bw=BlockWorld.getInstance();
		World world = bw.createWorld(1, 3, "World 3x3", "Steve");
		assertTrue("world == world3x3",world3x3.equals(world));
	}

	/**
	 * Test show player info.
	 *
	 * @throws RuntimeException the runtime exception
	 */
	@Test
	public void testShowPlayerInfo() throws RuntimeException {
		System.out.println(bw.showPlayerInfo(world3x3.getPlayer()));
		compareLines(STARTPLAY2,bw.showPlayerInfo(world3x3.getPlayer()));
		//System.out.println(bw.showPlayerInfo(world3x3.getPlayer()));
	}
	
	/**
	 * Test show player info 2.
	 *
	 * @throws RuntimeException the runtime exception
	 */
	@Test
	public void testShowPlayerInfo2() throws RuntimeException {
		world10x10 =  bw.createWorld(5, 10, "World 10x10", "Steve");
		
		System.out.println(bw.showPlayerInfo(world10x10.getPlayer()));
		//compareLines(STARTPLAY2,bw.showPlayerInfo(world3x3.getPlayer()));
		//System.out.println(bw.showPlayerInfo(world3x3.getPlayer()));
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
                                }
                        }
                        else {
			    	            String myexp=exp[i].trim().replaceAll("world= ", "world=");
			    	            String myres=res[i].trim().replaceAll("world= ", "world=");
					            assertEquals("linea "+i, myexp,myres);
			               }
                }
        }
}

