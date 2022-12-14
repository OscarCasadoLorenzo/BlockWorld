package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

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
import model.score.CollectedItemsScore;
import model.score.MiningScore;
import model.score.PlayerMovementScore;
import model.score.Ranking;
import model.score.XPScore;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockWorld_P4TestPlay.
 */
public class BlockWorld_P4TestPlay {

	
	/** The Constant STARTPLAY1. */
	final static String STARTPLAY1 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=0.0,y=66.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=0.0,y=0.0,z=1.0}\n"+
			"Health=20.0\n" + 
			"Food level=20.0\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			".#. ggg ddd\n" + 
			"... gP@ dgg\n" + 
			"... M.. g..\n"+
			"Scores: [items: 0.0, blocks: 0.0, movements: 0.0]";
	
	/** The Constant HALFPLAY1. */
	final static String HALFPLAY1 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=0.0,y=67.0,z=-1.0}\n" + 
			"Orientation=Location{world=World 5x5,x=0.0,y=0.0,z=1.0}\n"+
			"Health=19.0\n" + 
			"Food level=19.9\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			"... .M. gg.\n" + 
			"... .P. ggg\n" + 
			"... ... g.@\n" +
			"Scores: [items: 0.0, blocks: 0.0, movements: 1.7320508075688772]";
	
	/** The Constant ENDPLAY1. */
	final static String ENDPLAY1 ="Name=Steve\n" + 
			"Location{world=World 5x5,x=0.0,y=66.0,z=0.0}\n" +
			"Orientation=Location{world=World 5x5,x=0.0,y=0.0,z=1.0}\n"+
			"Health=19.0\n" + 
			"Food level=19.85\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			".#. ggg ddd\n" + 
			"... gP@ dgg\n" + 
			"... M.. g..\n" +
			"Scores: [items: 0.0, blocks: 0.0, movements: 3.1462643699419726]";
			
	/** The Constant STARTPLAY2. */
	final static String STARTPLAY2 ="Name=Miriam\n" + 
			"Location{world=World 5x5,x=0.0,y=66.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=0.0,y=0.0,z=1.0}\n"+
			"Health=20.0\n" + 
			"Food level=20.0\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			"... ggg ddd\n" + 
			"L.. gP. dgg\n" + 
			"... M.. g..\n" +
			"Scores: [items: 0.0, blocks: 0.0, movements: 0.0]";
			
	/** The Constant HALF1PLAY2. */
	final static String HALF1PLAY2 ="Name=Miriam\n" + 
			"Location{world=World 5x5,x=0.0,y=66.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=-1.0,y=1.0,z=0.0}\n"+
			"Health=20.0\n" + 
			"Food level=18.0\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			"... ggg ddd\n" + 
			"F.. gP. dgg\n" + 
			"... M.. g..\n" +
			"Scores: [items: 0.0, blocks: 0.0, movements: 0.0]";
	
	/** The Constant HALF2PLAY2. */
	final static String HALF2PLAY2 ="Name=Miriam\n" + 
			"Location{world=World 5x5,x=-1.0,y=67.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=-1.0,y=1.0,z=0.0}\n"+
			"Health=20.0\n" + 
			"Food level=17.95\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[(BEEF,1)])\n" + 
			"... ... .gg\n" + 
			"... .P. Bg.\n" + 
			"... ... .M.\n" +
			"Scores: [items: 8.0, blocks: 0.0, movements: 0.0]";
	
	/** The Constant HALF3PLAY2. */
	final static String HALF3PLAY2 ="Name=Miriam\n" + 
			"Location{world=World 5x5,x=-1.0,y=67.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=-1.0,y=1.0,z=0.0}\n"+
			"Health=20.0\n" + 
			"Food level=20.0\n" + 
			"Inventory=(inHand=null,[(WOOD_SWORD,1)])\n" + 
			"... ... .gg\n" + 
			"... .P. Bg.\n" + 
			"... ... .M.\n" +
			"Scores: [items: 8.0, blocks: 0.0, movements: 0.0]";
	
	/** The Constant HALF4PLAY2. */
	final static String HALF4PLAY2 ="Name=Miriam\n" + 
			"Location{world=World 5x5,x=-1.0,y=67.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=0.0,y=-1.0,z=1.0}\n"+
			"Health=19.5\n" + 
			"Food level=19.9\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			"... ... .gg\n" + 
			"... .P. Bg.\n" + 
			"... ... .M.\n"
			+ "Scores: [items: 8.0, blocks: 0.0, movements: 0.0]";
	
	/** The Constant ENDPLAY2. */
	final static String ENDPLAY2 ="Name=Miriam\n" + 
			"Location{world=World 5x5,x=-1.0,y=67.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=0.0,y=-1.0,z=1.0}\n"+
			"Health=19.5\n" + 
			"Food level=18.0\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			"... ... .gg\n" + 
			"... .P. Bg.\n" + 
			"... ... ...\n"
			+ "Scores: [items: 8.0, blocks: 0.0, movements: 0.0]";

	/** The Constant STARTPLAY3. */
	final static String STARTPLAY3 ="Name=Carla\n" + 
			"Location{world=World 5x5,x=0.0,y=66.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=0.0,y=0.0,z=1.0}\n"+
			"Health=20.0\n" + 
			"Food level=20.0\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			"... ggg ddd\n" + 
			"... gP. dgg\n" + 
			"... M.. g..\n"
			+ "Scores: [items: 0.0, blocks: 0.0, movements: 0.0]";
	
	/** The Constant HALF1PLAY3. */
	final static String HALF1PLAY3 ="Name=Carla\n" + 
			"Location{world=World 5x5,x=0.0,y=66.0,z=0.0}\n" + 
			"Orientation=Location{world=World 5x5,x=0.0,y=0.0,z=1.0}\n"+
			"Health=20.0\n" + 
			"Food level=19.9\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[])\n" + 
			"... ggg ddd\n" + 
			"... gP. dgg\n" + 
			"... MG. g..\n"
			+ "Scores: [items: 0.0, blocks: 0.6, movements: 0.0]";
	
	/** The Constant HALF2PLAY3. */
	final static String HALF2PLAY3 ="Name=Carla\n" + 
			"Location{world=World 5x5,x=0.0,y=66.0,z=1.0}\n" + 
			"Orientation=Location{world=World 5x5,x=0.0,y=0.0,z=1.0}\n"+
			"Health=20.0\n" + 
			"Food level=19.85\n" + 
			"Inventory=(inHand=(WOOD_SWORD,1),[(GRASS,1)])\n" + 
			"... g.. dgg\n" + 
			"... MP. g..\n" +
			"... ... ...\n"
			+ "Scores: [items: 0.6, blocks: 0.6, movements: 0.0]";

	/** The Constant HALF3PLAY3. */
	final static String HALF3PLAY3 ="Name=Carla\n" + 
			"Location{world=World 5x5,x=0.0,y=66.0,z=1.0}\n" + 
			"Orientation=Location{world=World 5x5,x=-1.0,y=0.0,z=1.0}\n"+
			"Health=20.0\n" + 
			"Food level=18.85\n" + 
			"Inventory=(inHand=(GRASS,1),[(WOOD_SWORD,1)])\n" + 
			"... g.. dgg\n" + 
			"... MP. g..\n" +
			"... ggg ...\n"
			+ "Scores: [items: 0.6, blocks: 0.6, movements: 0.0]";

	/** The Constant ENDPLAY3. */
	final static String ENDPLAY3 ="Name=Carla\n" + 
			"Location{world=World 5x5,x=0.0,y=67.0,z=2.0}\n" + 
			"Orientation=Location{world=World 5x5,x=-1.0,y=0.0,z=1.0}\n"+
			"Health=20.0\n" + 
			"Food level=18.30\n" + 
			"Inventory=(inHand=(GRASS,1),[(WOOD_SWORD,1)])\n" + 
			"... ... M..\n" + 
			"... .P. ggg\n" +
			"XXX XXX XXX\n"
			+ "Scores: [items: 0.6, blocks: 0.6, movements: 1.4142135623730951]";
	
	/** The k RANKINGPLAYS. */
	final String kRANKINGPLAYS = "Collected items ranking = [Miriam:8.0, Carla:0.6, Steve:0.0]\n" + 
			"Mining ranking = [Carla:0.6, Steve:0.0]\n" + 
			"Movement ranking = [Miriam:0.0, Carla:1.4142135623730951, Steve:3.1462643699419726]\n" + 
			"Experience ranking = [Miriam:40.166666666666664, Steve:39.89875478998065, Carla:39.17140452079103]";
	
	/** The k RANKINGDANGEROUSPLANET. */
	final String kRANKINGDANGEROUSPLANET = "Collected items ranking = [Helen:49.1, Lucas:36.6, Robert:19.400000000000006, Sandra:9.2]\n" + 
			"Mining ranking = [Sandra:14.099999999999998, Lucas:5.3999999999999995, Robert:3.5, Helen:0.6]\n" + 
			"Movement ranking = [Robert:15.071067811865477, Lucas:17.414213562373096, Helen:20.242640687119287, Sandra:25.0]\n" + 
			"Experience ranking = [Helen:53.61421356237309, Lucas:52.40473785412435, Robert:51.85702260395515, Sandra:41.79999999999998]";
	
	/** The items ranking. */
	Ranking<CollectedItemsScore> itemsRanking;
	
	/** The mining ranking. */
	Ranking<MiningScore> miningRanking;
	
	/** The movement ranking. */
	Ranking<PlayerMovementScore> movementRanking;
	
	/** The xp ranking. */
	Ranking<XPScore> xpRanking;
	
	/** The xp score. */
	XPScore xpScore;	
	
	/** The item score. */
	CollectedItemsScore itemScore;
	
	/** The mining score. */
	MiningScore miningScore;
	
	/** The movement score. */
	PlayerMovementScore movementScore;
	
	/** The bw. */
	BlockWorld bw;
	
	/** The world 5 x 5. */
	World world5x5;
	
	/** The world 3 x 3. */
	World world3x3;
	
	/** The world 75 x 75. */
	World world75x75;
	
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
		itemsRanking = new Ranking<CollectedItemsScore>();
		miningRanking = new Ranking<MiningScore>();
		movementRanking = new Ranking<PlayerMovementScore>();
		xpRanking = new Ranking<XPScore>();
	}
	
	/**
	 * Test plays.
	 */
	@Test
	public void testPlays() {
		
		Player player = play1();
		xpScore = new XPScore(player);
		addScoresToRanking(bw.getItemsScore(),bw.getMiningScore(),bw.getMovementScore());
		
		player = play2();
		xpScore = new XPScore(player);
		addScoresToRanking(bw.getItemsScore(),bw.getMiningScore(),bw.getMovementScore());
		
		player = play3();
		xpScore = new XPScore(player);
		addScoresToRanking(bw.getItemsScore(),bw.getMiningScore(),bw.getMovementScore());
		
		//System.out.println(rankingToString ());
		compareRankings(kRANKINGPLAYS, rankingsToString());
		
	}

	
	/**
	 * Test rankig in dangerous planet.
	 *
	 * @throws RuntimeException the runtime exception
	 */
	@Test
	public void testRankigInDangerousPlanet() throws RuntimeException {
		
		Player player = playRobert();
		xpScore = new XPScore(player);
		addScoresToRanking(bw.getItemsScore(),bw.getMiningScore(),bw.getMovementScore());
		
		player = playSandra();
		xpScore = new XPScore(player);
		addScoresToRanking(bw.getItemsScore(),bw.getMiningScore(),bw.getMovementScore());
		
		player = playLucas();
		xpScore = new XPScore(player);
		addScoresToRanking(bw.getItemsScore(),bw.getMiningScore(),bw.getMovementScore());
		
		player = playHelen();
		xpScore = new XPScore(player);
		addScoresToRanking(bw.getItemsScore(),bw.getMiningScore(),bw.getMovementScore());
		
		compareRankings(kRANKINGDANGEROUSPLANET,rankingsToString ());
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
private void  compareLines(String expected, String result) {
	String exp[]=expected.split("\n");
	String res[]=result.split("\n");
	boolean iguales = true;
	if (exp.length!=res.length) 
		fail("Cadena esperada de tama??o ("+exp.length+") distinto a la resultante ("+res.length+")");
	for (int i=0; i<exp.length && iguales; i++) {
		
		// si la l??nea es Health=1.2345  o Food level=1.2345  
		// se extraen los n??meros y se comparan con 0.01 de margen
		
		if (i == exp.length-1) { //Analiza la ??ltima l??nea de los Scores
			String sexp = exp[i].substring(7);
			String sres = res[i].substring(7);
			compareRankings(sexp,sres);
		}
		else {
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
	
/**
 * Compare rankings.
 *
 * @param expected the expected
 * @param result the result
 */
/* Se usa para comparar los valores impresos de los rankings con una precisi??n 
 * de 0.01
 * [Sara:1.4142135623730951, Julia:14.866068747318506]"
 */
private void compareRankings(String expected, String result) {
	String exp[]=expected.split("\n");
	String res[]=result.split("\n");
	boolean iguales = true;
	for (int i=0; i<exp.length && iguales; i++) {
		
		String exp1[]=exp[i].split(", ");
		String res1[]=res[i].split(", ");
		if (exp1.length!=res1.length) 
			fail("Error: cantidad de jugadores distintos en el ranking "+res[i]);
		for (int k=0; k<exp1.length; k++) { //Para cada Score
			if (k==exp1.length-1) //Es el ??ltimo Score
				compareScores(exp1[k].substring(0, exp1[k].indexOf(']')).trim(),res1[k].substring(0, res1[k].indexOf(']')).trim());
			else 
				compareScores(exp1[k].trim(),res1[k].trim());
			
		}
	}
}

/**
 * Compare scores.
 *
 * @param expected the expected
 * @param result the result
 */
/* Para las salidas Score.toString() compara los valores impresos
 * de los Scores hasta una precisi??n de 0.01
 * 
 */
private void compareScores(String expected, String result ) {
	String ex[]= expected.split(":");
	String re[]= result.split(":");
	if (ex.length!=re.length) fail("Lineas distintas");
	if (ex.length==2) {
		if (ex[0].trim().equals(re[0].trim())) {
			double ed = Double.parseDouble(ex[1]);
			double rd = Double.parseDouble(re[1]);
	
			assertEquals(ex[0],ed,rd,0.01);
		}
		else fail("Nombres jugadores distintos: esperado=<"+ex[0].trim()+"> obtenido=<"+re[0].trim()+">");
	}
	else
		assertEquals(expected.trim(),result.trim());		
}

/**
 * Standard IO 2 file.
 *
 * @param fileName the file name
 * @return the prints the stream
 */
/* Funci??n que cambia la salida standard (out) a un fichero*/
public static PrintStream standardIO2File(String fileName){
    if(fileName.equals("")){//Si viene vac??o usamos este por defecto
        fileName="test/files/testPlayFileStandard.txt";
    }
    PrintStream ps=null;
    try {
        //Creamos un printstream sobre el archivo permitiendo a??adir al
        //final para no sobreescribir.
        ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File(fileName),false)),true);
        //Redirigimos entrada 
        System.setOut(ps);
       // System.setErr(ps);
    } catch (FileNotFoundException ex) {
        System.err.println("Se ha producido una excepci??n FileNotFoundException");
    }
    return ps;
}



/**
 * Adds the scores to ranking.
 *
 * @param is the is
 * @param ms the ms
 * @param pms the pms
 */
/* A??ade los distintos valores de los Scores a los Ranking*/
private void addScoresToRanking (CollectedItemsScore is, MiningScore ms, PlayerMovementScore pms) {
  xpScore.addScore(is); 
  xpScore.addScore(ms); 
  xpScore.addScore(pms);
  itemsRanking.addScore(is);
  miningRanking.addScore(ms);
  movementRanking.addScore(pms);
  xpRanking.addScore(xpScore);
}

/**
 * Rankings to string.
 *
 * @return the string
 */
/* Construye la salida de los distintos Rankigs a String*/
private String rankingsToString () {
	StringBuilder ps = new StringBuilder();
	ps.append("Collected items ranking = "+itemsRanking.getSortedRanking()+"\n");
	ps.append("Mining ranking = "+miningRanking.getSortedRanking()+"\n");
	ps.append("Movement ranking = "+movementRanking.getSortedRanking()+"\n");
	ps.append("Experience ranking = "+xpRanking.getSortedRanking());
	return ps.toString();
}


/**
 * ****************************************************************************
 *                   GAMES
 * ***************************************************************************.
 *
 * @return the player
 * @throws RuntimeException the runtime exception
 */

private Player play1() throws RuntimeException {
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
	world5x5 =  bw.createWorld(5, 5, "World 5x5","Steve");
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
		compareLines(HALFPLAY1,bw.showPlayerInfo(world5x5.getPlayer()));

		// volvemos a posicion inicial
		bw.movePlayer(ply,0,-1,1);
		
		compareLines(ENDPLAY1,bw.showPlayerInfo(world5x5.getPlayer()));
		
		
	} catch (EntityIsDeadException e) {
		fail("Excepci??n "+e.getClass().getName()+" no esperada");
	} catch (BadLocationException e) {
		fail("Excepci??n "+e.getClass().getName()+" no esperada");
	}
	return ply;
}


/**
 * Play 2.
 *
 * @return the player
 * @throws RuntimeException the runtime exception
 */
private Player play2() throws RuntimeException {
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
	world5x5 =  bw.createWorld(5, 5, "World 5x5","Miriam");
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
	return ply;
	//System.out.println(" test 2:"+bw.showPlayerInfo(ply));

}


/**
 * Play 3.
 *
 * @return the player
 * @throws RuntimeException the runtime exception
 */
private Player play3() throws RuntimeException {
	
	//  - useItem  con bloque (GRASS) y romperlo, y coger item (G)
	//  - useItem con el G cogido para a??adir un nuevo bloque
	//  - intentar crear el nuevo bloque fuera del mundo

	/* INICIO PARTIDA
	 Location{world=World 5x5,x=0.0,y=66.0,z=0.0}
	... ggg ddd
	... gP. dgg
	... M.. g.. 
	*/
	Material.rng.setSeed(1L);
	world5x5 =  bw.createWorld(5, 5, "World 5x5","Carla");
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
		bw.useItem(ply,2);      // plantamos un bloque
		bw.orientatePlayer(ply,-1,0,1);
		bw.useItem(ply,1);      // plantamos un bloque
		
		compareLines(HALF3PLAY3,bw.showPlayerInfo(world5x5.getPlayer()));
		
		bw.movePlayer(ply,0,1,1); // nos movemos encima de la hierba para llegar al l??mite del mundo
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
	return ply;
	//System.out.println(" test 3:"+bw.showPlayerInfo(ply));
}	


/**
 * Play robert.
 *
 * @return the player
 */
/* INFORMACI??N SOBRE EL MUNDO "A DANGEROUS WORLD" DE 75x75
 * SUPERFICIES REPRESENTADAS [x,62,z] [x,61,z] para  (-10 <= x <= 10) (-10 <= z <= 10)
 * LAS LETRAS DE LOS BLOQUES EN MAYUSCULAS REPRESENTAN BLOQUES CON ITEMS: 
 * 	'G' es un bloque GRASS con 1 Item GRASS
 *  'g' es un bloque GRASS sin item
[x,62,z]
@@@A@GGGgggggGDDddDDd
@@@F@@gGgggGGGGddDddD
@@@@@@@GgG@GgGggDDDdD
@@@@@@@GGg@@@@@GGgddD
@@@@@@@@AGG@@@MGgGDDd
@@@@@@@@@@@@@@LM@gdDD
@@@@@@@@@@@@@@@@@@GDD
@@@@@@@@@@B@@@@@M@ggD
@@@@@@@@@>@@@@@B>LGGD
@@@@@@@@@@@@LF@@@GGgG
@@@@@@@@@@PM@@@@MgGGG
@@@@@@@@M@B@@@@@@@@gg
@@@@@@@@@@@@@@@@@@@gG
@@@@@@@@@@@@@@@@@@@@G
@@@@@@@@@@@@@@@M@@@gG
@@@@@@@@@@@@@@@@@@Wgg
@@@@@@@@@@@@@@@@@@GgG
@@@@@@@@@@@@@@@@@ggGg
@@@@@@@@@@@@@@@AGggGd
@@@@@@@@@@@@@@@@gGGgD
@@@@@@@@@@@@@@@@gGGDd

[x,61,z]
@@@ggDdddDdDdDDDDDDDD
@@@GGGDdDDDdDdDddDddD
@@@@@ggDDDGDDDDdddDdd
@@@@@@gDddGGgGGDdddDd
@@@@@@GGGddgGGgdDdDdd
@@@@@@@@gggggGGGGdddD
@@@@@B@@GggGgGggGGdDd
@@@@B@M@ggggggGgggddD
@@@@@@@@gGgGgggGggdDD
@@@@@@@@ggGgGGGgGdddD
@@@@@@@@@GGggGgggDdDD
@@@@@@@@gGGGgGGgGgGDd
@@@@@@@@FBggGGggGGgdd
@@@@@@@W@@@@@WGgGggGd
@@@@@@@@@@@@@@@GgggDd
@@@@@@@@@@@@@@A@GgGDD
@@@@@@@@@@@@@@@gGgddd
@@@@@@@@@@@@@@GggDddd
@@@@@@@@@@@@@A@GdDDDD
@@@@@@@@@@@@@@GGDdDdd
@@@@@@@@@@@@@@GGdDDdd
*/
private Player playRobert() {
	world75x75 =  bw.createWorld(5, 75, "A Dangerous World","Robert");
	Player ply = world75x75.getPlayer();

	try {
		bw.movePlayer(ply,0,0,1); //Coge un (BREAD,3)-[x=0.0,y=62.0,z=1.0]
		bw.movePlayer(ply,1, 0, 1);
		bw.movePlayer(ply,1, 0, 1);
		bw.movePlayer(ply,1, 0, 1);
		bw.movePlayer(ply,1, 0, 0); 
		bw.orientatePlayer(ply, 1, 0, 0); //Se orienta frente al Monster [x=5.0,y=62.0,z=4.0, health=15.0]
		bw.useItem(ply, 5);
		bw.useItem(ply, 5);
		bw.useItem(ply, 5);//Mata al Monster
		bw.movePlayer(ply,1, 0, 0);
		bw.movePlayer(ply,1, 0, 0);
		bw.movePlayer(ply,1, 0, 0); 
		bw.movePlayer(ply, 1, 0, 0);
		bw.useItem(ply, 1); //Destruye el GRASS
		bw.orientatePlayer(ply, 0, 0, 1);
		bw.useItem(ply, 1); 
		bw.movePlayer(ply,0, 0, 1); //Coge el (WATER_BUCKET,2)[x=8.0,y=62.0,z=5.0]
		bw.selectItem(ply, 0); //Pone en la mano el (BREAD,3)
		bw.useItem(ply, 3);
		bw.selectItem(ply, 0); //Pone en la mano el WOOD_SWORD
		bw.useItem(ply, 2); // Destruye [GRASS] con Drop [x=8.0,y=62.0,z=6.0] *** (GRASS,1)
		bw.movePlayer(ply,0,0,1); //Coge el GRASS
		bw.orientatePlayer(ply, 1, 0, 1); 
		bw.useItem(ply, 1); //Destruye [GRASS][x=9.0,y=62.0,z=7.0] *** (GRASS,1)
		bw.movePlayer(ply, 1, 0, 1); //Coge el GRASS
		bw.orientatePlayer(ply, 0, 0, 1);
		bw.useItem(ply, 1); //Destruye [GRASS][x=9.0,y=62.0,z=8.0] *** (GRASS,1)
		bw.movePlayer(ply, 0, 0, 1); //Coge el GRASS
		bw.orientatePlayer(ply, -1, 0, 1);
		bw.useItem(ply, 1); //Destruye [GRASS][x=8.0,y=62.0,z=9.0] *** (GRASS,1)
		bw.movePlayer(ply, -1, 0, 1); //Coge el GRASS
		bw.orientatePlayer(ply, 1, 0, 1);
		bw.useItem(ply, 1); //Destruye [DIRT][x=9.0,y=62.0,z=10.0] *** (DIRT,1)
		bw.orientatePlayer(ply, 1, 0, 1); //Lo coge

	} catch (EntityIsDeadException e) {
		fail("Excepci??n "+e.getClass().getName()+" no esperada");
	} catch (BadLocationException e) {
		fail("Excepci??n "+e.getClass().getName()+" no esperada");
	} catch (BadInventoryPositionException e) {
		fail("Excepci??n "+e.getClass().getName()+" no esperada");
	}
	return ply;
}

	/**
	 * Play sandra.
	 *
	 * @return the player
	 */
	private Player playSandra() {
		world75x75 =  bw.createWorld(5, 75, "A Dangerous World","Sandra");
		Player ply = world75x75.getPlayer();
		/* Se mueve hacia abajo y va rompiendo bloques, movi??ndose y recogiendo items*/
		try {
			bw.orientatePlayer(ply, 0, -1, 0);
			bw.useItem(ply,5);
			bw.movePlayer(ply, 0, -1, 0); //Se mueve una posici??n abajo
			bw.orientatePlayer(ply, 1, 0, 0);
			/* Se mueve 3 posiciones al ESTE rompiendo bloques */
			for (int i =0; i<3; i++) {
				bw.useItem(ply,5);
				bw.movePlayer(ply, 1, 0, 0);
			}
			/* Se mueve 10 posiciones hacia el NORTE rompiendo bloques y recogiendo items */
			bw.orientatePlayer(ply, 0, 0, -1);
			for (int i =0; i<10; i++) {
				bw.useItem(ply,5);
				bw.movePlayer(ply, 0, 0, -1);
			}
			/* Se mueve 6 posiciones hacia el ESTE rompiendo bloques y recogiendo items */
			bw.orientatePlayer(ply, 1, 0, 0);
			for (int i =0; i<6; i++) {
				bw.useItem(ply,5);
				bw.movePlayer(ply, 1, 0, 0);
			}
			/* Se mueve 20 posiciones hacia el SUR rompiendo bloques y recogiendo items */
			bw.orientatePlayer(ply, 0, 0, 1);
			for (int i =0; i<6; i++) {
				bw.useItem(ply,5);
				bw.movePlayer(ply, 0, 0, 1);
			}
			
		} catch (EntityIsDeadException e) {
			fail("Excepci??n "+e.getClass().getName()+" no esperada");
		} catch (BadLocationException e) {
			fail("Excepci??n "+e.getClass().getName()+" no esperada");
		} 
		return ply;
	}
	
	/**
	 * Play lucas.
	 *
	 * @return the player
	 */
	private Player playLucas() {
		//Material.rng.setSeed(1L);
		world75x75 =  bw.createWorld(5, 75, "A Dangerous World","Lucas");
		Player ply = world75x75.getPlayer();
		
		try {
			
			bw.orientatePlayer(ply, 0, -1, 0); //Se orienta hacia abajo que hay un item GRASS con item
			bw.useItem(ply,1 );
			bw.movePlayer(ply, 0, -1, 0);//Coge el item GRASS-[x=0.0,y=61.0,z=0.0]
		
			bw.orientatePlayer(ply, -1, 0, 0); //Se orienta hacia el oeste donde hay otro bloque GRASS con Item
			bw.useItem(ply, 2); 
			bw.movePlayer(ply,-1, 0, 0); //Coge el item GRASS [x=-1.0,y=61.0,z=0.0]
			
			bw.orientatePlayer(ply, 0, 0, 1); 
			bw.useItem(ply, 2); 
			bw.movePlayer(ply,0, 0, 1); //Coge otro item GRASS [x=-1.0,y=61.0,z=1.0]
			
			bw.movePlayer(ply,0,0,1); //Coge un item BREAD [-1,61,2]
			bw.orientatePlayer(ply, -1, 0, 0);
			bw.movePlayer(ply, -1, 0, 0); //Coge un item BEEF
			bw.orientatePlayer(ply, -1, 0, 1);
			bw.movePlayer(ply, -1, 0, 1); //Coge un WATER_BUCKET
			bw.orientatePlayer(ply, 1, 0, 0);
			//Se desplaza hacia el Este rompiendo bloques y cogiendo items
			for (int i=0; i<12; i++) { 	
				bw.useItem(ply, 5);
				bw.movePlayer(ply, 1, 0, 0);
			}

		} catch (EntityIsDeadException e) {
			fail("Excepci??n "+e.getClass().getName()+" no esperada");
		} catch (BadLocationException e) {
			fail("Excepci??n "+e.getClass().getName()+" no esperada");
		} 
		return (ply);
	}
	
	/**
	 * Play helen.
	 *
	 * @return the player
	 */
	private Player playHelen() {
		//Material.rng.setSeed(1L);
		world75x75 =  bw.createWorld(5, 75, "A Dangerous World","Helen");
		Player ply = world75x75.getPlayer();

		try {
			
			bw.movePlayer(ply, 0, 0, 1); //Se mueve una posici??n al sur donde hay (BREAD,3) y lo mete en su inventario
			bw.movePlayer(ply, -1, 0, 0); //Se mueve una posici??n al oeste
			bw.orientatePlayer(ply, -1, 0, 0); //Se orienta hacia el oeste y tiene enfrente un Monster [x=-2.0,y=62.0,z=1.0, health=1.0]
			bw.useItem(ply, 2); // Lo mata  Food level=19.7 Inventory=(inHand=(WOOD_SWORD,1),[(BREAD,3)])
			bw.movePlayer(ply, -1, 0, 0); //Se mueve donde estaba el monster
			bw.movePlayer(ply, 1, 0, -1); // Se mueve al nordeste
			bw.movePlayer(ply, 1, 0, 0); //Est?? justo donde estaba al principio. Food level=19.55 Inventory=(inHand=(WOOD_SWORD,1),[(BREAD,3)])
			bw.orientatePlayer(ply, 1, 0, 0);  //Orientamos el player hacia el monstruo que hay hacia el este. Monster [x=1.0,y=62.0,z=0.0, health=11.0]
			bw.useItem(ply, 10); // Le ataca y le deja 1 pto. de vida. Contraataca y deja al player con 15 ptos (20 - 0.5 *10)
			bw.useItem(ply, 1); // Lo mata. Health=15.0 Food level=18.45 
			for (int i=0; i<5; i++) bw.movePlayer(ply, 1, 0, 0);
			 //Frente al player hay un monster Monster [x=6.0,y=62.0,z=0.0, health=12.0]
			bw.useItem(ply, 4); //Ataca y le contraatacan 
			bw.useItem(ply, 4); 
			bw.useItem(ply, 3);
			bw.useItem(ply, 5);  //Health=9.5 Food level=16.60
			bw.movePlayer(ply, 1, 0, 0);  //movemos el player hacia el este (donde estaba el monstruo)
			bw.orientatePlayer(ply, 1, 0, -1); //Orientamos a player hacia un bloque GRASS con 1 item
			bw.useItem(ply, 2); //Rompe el bloque y deja un item de GRASS
			bw.movePlayer(ply, 1, 0, -1); //Lo recoge. Health=9.5 Food level=16.30 Inventory=(inHand=(WOOD_SWORD,1),[(BREAD,3), (GRASS,1)])
			bw.movePlayer(ply, -1, 0, 0);
			bw.movePlayer(ply, 0, 0, -1); //hay un IRON_SHOVEL y lo recoge (IRON_SHOVEL,1)[x=6.0,y=62.0,z=-2.0]
			bw.selectItem(ply, 2); //Lo pone en la mano
			bw.orientatePlayer(ply, 1, 0, 0);
			bw.useItem(ply, 65); //Enfrente tiene un Animal [x=7.0,y=62.0,z=-2.0, health=13.0] y usa el item sobre ??l y lo mata. Deja 1 BEEF
								//Health=9.5 Food level=9.70 Inventory=(inHand=(IRON_SHOVEL,1),[(BREAD,3), (GRASS,1), (WOOD_SWORD,1)])
			bw.movePlayer(ply, 1, 0, 0); //Se mueve donde estaba el animal y recoge el BEEF
					// Health=9.5 Food level=9.65 Inventory=(inHand=(IRON_SHOVEL,1),[(BREAD,3), (GRASS,1), (WOOD_SWORD,1), (BEEF,1)])
			bw.movePlayer(ply, -1, 0, 0);
			bw.movePlayer(ply,-1, 0, 0); //Hay un BREAD y lo recoge (BREAD,1)[x=5.0,y=62.0,z=-2.0]
			        // Health=9.5 Food level=9.55 Inventory=(inHand=(IRON_SHOVEL,1),[(BREAD,3), (GRASS,1), (WOOD_SWORD,1), (BEEF,1), (BREAD,1)])
			bw.selectItem(ply, 4); //Toma el BREAD que acaba de recoger
			bw.useItem(ply, 5); //Se lo come
			bw.orientatePlayer(ply, 1, 0, -1); //Se orienta al nordeste donde hay un Monster [x=6.0,y=62.0,z=-3.0, health=9.0]
			bw.useItem(ply, 5);  //No ocurre nada pues no lleva nada en la mano
			
			bw.selectItem(ply, 2); //Toma el WOOD_SWORD. Health=9.5 Food level=14.8  Inventory=(inHand=(WOOD_SWORD,1),[(BREAD,3), (BEEF,1), (IRON_SHOVEL,1)])
			         // Health=9.5 Food level=14.55 Inventory=(inHand=(GRASS,1),[(BREAD,3), (WOOD_SWORD,1), (BEEF,1), (IRON_SHOVEL,1)])
			for (int i=0; i<5; i++)  bw.useItem(ply, 2);
			 //Lo mata pero el monstruo le ha inflingido 4 ptos. de salud al player (8*0.5)
			 //Health=5.5 Food level=13.55 Inventory=(inHand=(WOOD_SWORD,1),[(BREAD,3), (GRASS,1), (BEEF,1), (IRON_SHOVEL,1)])
			bw.movePlayer(ply, 0, 0, -1); //Se mueve hacia el norte
			bw.movePlayer(ply, 0, 0, -1);
			bw.useItem(ply, 5); //No pasa nada donde est?? orientado solo hay agua
			bw.orientatePlayer(ply, -1, 0, -1); //Hay un Animal [x=4.0,y=62.0,z=-5.0, health=16.0]
			
			bw.useItem(ply, 10);  //Ataca al animal pero no lo mata
			bw.orientatePlayer(ply, 0, 0, -1); //Hay un Monster [x=5.0,y=62.0,z=-5.0, health=16.0] 
			bw.useItem(ply, 8);
			bw.useItem(ply, 8);  //Lo mata
			
			bw.selectItem(ply, 1);  //Toma (GRASS, 1)
			bw.useItem(ply, 1);   //Intenta poner el bloque de GRASS donde estaba el Monster, pero no puede porque hay un bloque de agua
			bw.orientatePlayer(ply, -1, 0, -1); //Se orienta hacia el animal
			bw.useItem(ply, 60);   //Lo mata con el bloque de GRASS
			
			bw.selectItem(ply, 0); //Toma (BREAD, 3)
			bw.useItem(ply, 5);     //Se lo come
			
			bw.movePlayer(ply, -1, 0, -1);  //Se mueve hacia donde estaba el animal y recoge el BEEF 
			
			bw.orientatePlayer(ply, 0, 0, -1); //Se orienta hacia donde esta un Monster [x=4.0,y=62.0,z=-6.0, health=5.0]
			
			bw.selectItem(ply, 3);  //Toma el IRON_SHOVEL
			bw.useItem(ply, 4);  //No mata al monster pero el player muere	
		
		} catch (EntityIsDeadException e) {
			fail("Excepci??n "+e.getClass().getName()+" no esperada");
		} catch (BadLocationException e) {
			fail("Excepci??n "+e.getClass().getName()+" no esperada");
		} catch (BadInventoryPositionException e) {
			fail("Excepci??n "+e.getClass().getName()+" no esperada");
		}
		return (ply);
	}

}

