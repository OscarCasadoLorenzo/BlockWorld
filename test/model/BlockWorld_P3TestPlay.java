package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.entities.Animal;
import model.entities.Player;
import model.exceptions.BadInventoryPositionException;
import model.exceptions.BadLocationException;
import model.exceptions.EntityIsDeadException;
import model.exceptions.StackSizeException;
import model.exceptions.WrongMaterialException;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockWorld_P3TestPlay.
 */
public class BlockWorld_P3TestPlay {

	
	/** The Constant STARTPLAY1. */
	final static String STARTPLAY1 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=0.0,y=66.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=0.0,y=0.0,z=1.0}\n"+
			"Health=20.0\n" + 
			"Food level=20.0\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			".#. ggg ddd\n" + 
			"... gP@ dgg\n" + 
			"... M.. g..";
	
	/** The Constant HALFPLAY1. */
	final static String HALFPLAY1 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=0.0,y=67.0,z=-1.0}\n" + 
			"Orientation=Location{world=World 5x5,x=0.0,y=0.0,z=1.0}\n"+
			"Health=19.0\n" + 
			"Food level=19.9\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			"... .M. gg.\n" + 
			"... .P. ggg\n" + 
			"... ... g.@"; 
	
	/** The Constant ENDPLAY1. */
	final static String ENDPLAY1 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=0.0,y=66.0,z=0.0}\n" +
			"Orientation=Location{world=World 5x5,x=0.0,y=0.0,z=1.0}\n"+
			"Health=19.0\n" + 
			"Food level=19.85\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			".#. ggg ddd\n" + 
			"... gP@ dgg\n" + 
			"... M.. g..";
	
	/** The Constant STARTPLAY2. */
	final static String STARTPLAY2 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=0.0,y=66.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=0.0,y=0.0,z=1.0}\n"+
			"Health=20.0\n" + 
			"Food level=20.0\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			"... ggg ddd\n" + 
			"L.. gP. dgg\n" + 
			"... M.. g..";

	/** The Constant HALF1PLAY2. */
	final static String HALF1PLAY2 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=0.0,y=66.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=-1.0,y=1.0,z=0.0}\n"+
			"Health=20.0\n" + 
			"Food level=18.0\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			"... ggg ddd\n" + 
			"F.. gP. dgg\n" + 
			"... M.. g..";
	
	/** The Constant HALF2PLAY2. */
	final static String HALF2PLAY2 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=-1.0,y=67.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=-1.0,y=1.0,z=0.0}\n"+
			"Health=20.0\n" + 
			"Food level=17.95\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[(BEEF,1)])\n" + 
			"... ... .gg\n" + 
			"... .P. Bg.\n" + 
			"... ... .M.";
	
	/** The Constant HALF3PLAY2. */
	final static String HALF3PLAY2 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=-1.0,y=67.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=-1.0,y=1.0,z=0.0}\n"+
			"Health=20.0\n" + 
			"Food level=20.0\n" + 
			"Inventory=(inHand=null,[(WOOD_SWORD,1)])\n" + 
			"... ... .gg\n" + 
			"... .P. Bg.\n" + 
			"... ... .M.";
	
	/** The Constant HALF4PLAY2. */
	final static String HALF4PLAY2 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=-1.0,y=67.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=0.0,y=-1.0,z=1.0}\n"+
			"Health=19.5\n" + 
			"Food level=19.9\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			"... ... .gg\n" + 
			"... .P. Bg.\n" + 
			"... ... .M.";
	
	/** The Constant ENDPLAY2. */
	final static String ENDPLAY2 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=-1.0,y=67.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=0.0,y=-1.0,z=1.0}\n"+
			"Health=19.5\n" + 
			"Food level=18.0\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			"... ... .gg\n" + 
			"... .P. Bg.\n" + 
			"... ... ...";

	/** The Constant STARTPLAY3. */
	final static String STARTPLAY3 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=0.0,y=66.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=0.0,y=0.0,z=1.0}\n"+
			"Health=20.0\n" + 
			"Food level=20.0\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			"... ggg ddd\n" + 
			"... gP. dgg\n" + 
			"... M.. g..";
	
	/** The Constant HALF1PLAY3. */
	final static String HALF1PLAY3 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=0.0,y=66.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=0.0,y=0.0,z=1.0}\n"+
			"Health=20.0\n" + 
			"Food level=19.9\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			"... ggg ddd\n" + 
			"... gP. dgg\n" + 
			"... MG. g..";
	
	/** The Constant HALF2PLAY3. */
	final static String HALF2PLAY3 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=0.0,y=66.0,z=1.0}\n" + 
			"Orientation=Location{world=World 5x5,x=0.0,y=0.0,z=1.0}\n"+
			"Health=20.0\n" + 
			"Food level=19.85\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[(GRASS,1)])\n" + 
			"... g.. dgg\n" + 
			"... MP. g..\n" +
			"... ... ...";

	/** The Constant HALF3PLAY3. */
	final static String HALF3PLAY3 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=0.0,y=66.0,z=1.0}\n" + 
			"Orientation=Location{world=World 5x5,x=-1.0,y=0.0,z=1.0}\n"+
			"Health=20.0\n" + 
			"Food level=18.85\n" + 
			"Inventory=(inHand=(GRASS,1),[(WOOD_SWORD,1)])\n" + 
			"... g.. dgg\n" + 
			"... MP. g..\n" +
			"... ggg ...";

	/** The Constant ENDPLAY3. */
	final static String ENDPLAY3 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=0.0,y=67.0,z=2.0}\n" + 
			"Orientation=Location{world=World 5x5,x=-1.0,y=0.0,z=1.0}\n"+
			"Health=20.0\n" + 
			"Food level=18.30\n" + 
			"Inventory=(inHand=(GRASS,1),[(WOOD_SWORD,1)])\n" + 
			"... ... M..\n" + 
			"... .P. ggg\n" +
			"XXX XXX XXX";

	/** The bw. */
	BlockWorld bw;
	
	/** The world 5 x 5. */
	World world5x5;
	
	/** The world 3 x 3. */
	World world3x3;
	
	
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
	}


	/**
	 * Test play 1.
	 *
	 * @throws RuntimeException the runtime exception
	 */
	@Test
	public void testPlay1() throws RuntimeException {
		//  - a??adir agua, lava
		//  - meterse en el charco de agua y ver que no pasa nada
		//  - meterse en el charco de lava y ver que hace pupita

		
		/* INICIO PARTIDA (mundo generado)
		 Location{world=World 5x5,x=0.0,y=66.0,z=0.0}
		... ggg ddd
		... gP. dgg
		... M.. g.. 

		despu??s de a??adir agua y lava
		 .#. ggg ddd 
		 ... gP@ dgg 
		 ... M.. g..
		
		*/
		Material.rng.setSeed(1L);
		world5x5 =  bw.createWorld(5, 5, "World 5x5", "Steve");
		//bw.showPlayerInfo(world5x5.getPlayer());
		Player ply = world5x5.getPlayer();
		
		//  - a??adir agua, lava
		Location plyloc = ply.getLocation();
		Location agua = new Location(plyloc);
		agua.setX(agua.getX()+1);  // poner agua a la derecha del jugador

		Location lava = new Location(plyloc);
		lava.setZ(lava.getZ()-1);  // poner lava arriba
		lava.setY(lava.getY()+1);  // subiendo una posici??n porque hay grass

		try {
			world5x5.addBlock(agua, BlockFactory.createBlock(Material.WATER));
			world5x5.addBlock(lava, BlockFactory.createBlock(Material.LAVA));
		} catch (BadLocationException e2) {
			fail("Excepci??n "+e2.getClass().getName()+" no esperada");
		} catch (WrongMaterialException e2) {
			fail("Excepci??n "+e2.getClass().getName()+" no esperada");
		}

		compareLines(STARTPLAY1,bw.showPlayerInfo(world5x5.getPlayer()));

		try {
			// chapuz??n en el agua
			bw.movePlayer(ply, 1, 0, 0);
			assertEquals("al agua patos",20.0,ply.getHealth(),0.001);
			// chapuz??n en lava (pupita)
			bw.movePlayer(ply,-1,1,-1);
			assertEquals("lava (pupita)",19.0,ply.getHealth(),0.001);

			//System.out.println(" test 1 (half):"+bw.showPlayerInfo(ply));

			compareLines(HALFPLAY1,bw.showPlayerInfo(world5x5.getPlayer()));

			// volvemos a posicion inicial
			bw.movePlayer(ply,0,-1,1);
			compareLines(ENDPLAY1,bw.showPlayerInfo(world5x5.getPlayer()));
		} catch (EntityIsDeadException e) {
			fail("Excepci??n "+e.getClass().getName()+" no esperada");
		} catch (BadLocationException e) {
			fail("Excepci??n "+e.getClass().getName()+" no esperada");
		}
	}

	/**
	 * Test play 2.
	 *
	 * @throws RuntimeException the runtime exception
	 */
	@Test
	public void testPlay2() throws RuntimeException {
		//  - matar animal y coger BEEF
		//  - hacer pupita al monstruo y aguantar el contraataque
		//  - rematar al monstruo

		/* INICIO PARTIDA (mundo generado)
		 Location{world=World 5x5,x=0.0,y=66.0,z=0.0}
		... ggg ddd
		... gP. dgg
		... M.. g.. 
		
		Despu??s de a??adir el animal
		... ggg ddd
		L.. gP. dgg
		... M.. g.. 
		*/
		Material.rng.setSeed(1L);
		world5x5 =  bw.createWorld(5, 5, "World 5x5", "Steve");
		Player ply = world5x5.getPlayer();
		
		//  - a??adir animal
		Location plyloc = ply.getLocation();
		Location animal = new Location(plyloc);
		animal.setX(animal.getX()-1);  // animal a la izquierda 
		animal.setY(animal.getY()+1);  // subiendo
		
		try {
			world5x5.addCreature(new Animal(animal, 2));
			compareLines(STARTPLAY2,bw.showPlayerInfo(world5x5.getPlayer()));
			
			// orientar el jugador hacia el animal
			bw.orientatePlayer(ply, -1, 1, 0);
			bw.useItem(ply, 20);   // muerto matao  
			compareLines(HALF1PLAY2,bw.showPlayerInfo(world5x5.getPlayer()));
			
			// cogemos la carne, que para eso lo hemos matao
			bw.movePlayer(ply, -1, 1, 0);
			compareLines(HALF2PLAY2,bw.showPlayerInfo(world5x5.getPlayer()));

			// a comerrrr
			bw.selectItem(ply, 0);
			bw.useItem(ply, 1);
			compareLines(HALF3PLAY2,bw.showPlayerInfo(world5x5.getPlayer()));
			
			// cogemos la espada, que si no va a ser complicao matar al bisho
			bw.selectItem(ply, 0);
			// reorientamos al jugador
			bw.orientatePlayer(ply, 0, -1, 1);
			
			// le damos un toque al bisho pa'ver si est?? vivo (y ??l responde)
			bw.useItem(ply, 1);
			compareLines(HALF4PLAY2,bw.showPlayerInfo(world5x5.getPlayer()));
			
			// rematamos, pero lo justo, sin sa??a
			bw.useItem(ply,19);
			compareLines(ENDPLAY2,bw.showPlayerInfo(world5x5.getPlayer()));
			
		} catch (BadLocationException | EntityIsDeadException e2) {
			fail("Excepci??n "+e2.getClass().getName()+" no esperada");
		} catch (BadInventoryPositionException e) {
			fail("Excepci??n "+e.getClass().getName()+" no esperada");
		}

		//System.out.println(" test 2:"+bw.showPlayerInfo(ply));

	}

	/**
	 * Test play 3.
	 *
	 * @throws RuntimeException the runtime exception
	 */
	@Test
	public void testPlay3() throws RuntimeException {
		//  - useItem contra un bloque (GRASS) y romperlo, y coger item (G)
		//  - useItem con el G cogido para a??adir nuevos bloques
		//  - intentar crear el nuevo bloque fuera del mundo

		/* INICIO PARTIDA
		 Location{world=World 5x5,x=0.0,y=66.0,z=0.0}
		... ggg ddd
		... gP. dgg
		... M.. g.. 
		*/
		Material.rng.setSeed(1L);
		world5x5 =  bw.createWorld(5, 5, "World 5x5", "Steve");
		Player ply = world5x5.getPlayer();
		
		compareLines(STARTPLAY3,bw.showPlayerInfo(world5x5.getPlayer()));

		try {
			// creamos un bloque y le ponemos un 'drops' para que el bloque suelte hierba
			Location locgrass = new Location(ply.getLocation());
			locgrass.setZ(locgrass.getZ()+1);
			SolidBlock sb = (SolidBlock)BlockFactory.createBlock(Material.GRASS);
			sb.setDrops(Material.GRASS,1);
			world5x5.addBlock(locgrass, sb);
				
			// orientamos al sur para cortar hierba con la espada de madera
			// bw.orientatePlayer(ply, 0, 0, 1);  // no hace falta orientar al jugador, ya debe estar orientado al sur
			bw.useItem(ply,1);  // romper el bloque de hierba
			compareLines(HALF1PLAY3,bw.showPlayerInfo(world5x5.getPlayer()));
			
			bw.movePlayer(ply,0,0,1); // pillamos la hierba (G)
			compareLines(HALF2PLAY3,bw.showPlayerInfo(world5x5.getPlayer()));
			
			bw.selectItem(ply, 0);  // seleccionamos la hierba para empezar a plantar
			bw.useItem(ply,7);      // plantamos tres bloques debajo (sur)  (ojo, consume (7+2+1)*0.1 = 1 comida)
			bw.orientatePlayer(ply,1,0,1);
			bw.useItem(ply,2);      // plantamos otro bloque
			bw.orientatePlayer(ply,-1,0,1);
			bw.useItem(ply,1);      // plantamos otro bloque
			compareLines(HALF3PLAY3,bw.showPlayerInfo(world5x5.getPlayer()));
			
			bw.movePlayer(ply,0,1,1); // nos movemos encima de la hierba reci??n plantada para llegar al l??mite del mundo
			bw.useItem(ply,5);  // intentamos plantar fuera del mundo, s??lo debe llamar a Player.useItemInHand y quitar comida
			compareLines(ENDPLAY3,bw.showPlayerInfo(world5x5.getPlayer()));
			
			
		} catch (EntityIsDeadException e) {
			fail("Excepci??n "+e.getClass().getName()+" no esperada");
		} catch (BadLocationException e) {
			fail("Excepci??n "+e.getClass().getName()+" no esperada");
		} catch (WrongMaterialException e) {
			fail("Excepci??n "+e.getClass().getName()+" no esperada");
		} catch (StackSizeException e) {
			fail("Excepci??n "+e.getClass().getName()+" no esperada");
		} catch (BadInventoryPositionException e) {
			fail("Excepci??n "+e.getClass().getName()+" no esperada");
		}
		
		
		//System.out.println(" test 3:"+bw.showPlayerInfo(ply));
	}	
	
	/**
	 * ***************************************************************.
	 *
	 * @param expected the expected
	 * @param result the result
	 */
	//FUNCION DE APOYO
	/* Se usa para comparar Strings que devuelven algunos m??todos. Evita sacar error por los espacios
	 * finales de cada l??nea y por los saltos de l??nea al final
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
                                    fail("linea "+i+": \""+exp[i]+"\" != \""+res[i]+"\"");
			}
         else {
            String myexp=exp[i].trim().replaceAll("world= ", "world=");
			   String myres=res[i].trim().replaceAll("world= ", "world=");
				assertEquals("linea "+i, myexp,myres);
			}
		}
	}
}

