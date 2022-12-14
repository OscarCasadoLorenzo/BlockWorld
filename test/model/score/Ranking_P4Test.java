package model.score;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Block;
import model.BlockFactory;
import model.BlockWorld;
import model.ItemStack;
import model.Location;
import model.Material;
import model.World;
import model.entities.Player;
import model.exceptions.score.EmptyRankingException;

// TODO: Auto-generated Javadoc
/**
 * The Class Ranking_P4Test.
 */
public class Ranking_P4Test {
	
	/** The k RANKING 1. */
	final String kRANKING1 = "Collected items ranking = [Julia:25.0]\n" + 
			"Mining ranking = [Julia:1.5]\n" + 
			"Movement ranking = [Julia:14.866068747318506]\n" + 
			"Experience ranking = [Julia:53.7886895824395]";
	
	/** The k RANKING 2. */
	final String kRANKING2 = "Collected items ranking = [Julia:25.0, Sara:0.5]\n" + 
			"Mining ranking = [Sara:5.0, Julia:1.5]\n" + 
			"Movement ranking = [Sara:1.4142135623730951, Julia:14.866068747318506]\n" + 
			"Experience ranking = [Julia:53.7886895824395, Sara:42.30473785412436]";
	
	/** The k RANKING 3. */
	final String kRANKING3 = "Collected items ranking = [Julia:45.0, Sara:2.0, Frank:1.7]\n" + 
			"Mining ranking = [Frank:7.0, Sara:6.199999999999999, Julia:1.6]\n" + 
			"Movement ranking = [Sara:68.45151661075057, Frank:189.4168731180294, Julia:309.33471132273297]\n" + 
			"Experience ranking = [Julia:158.6449037742443, Frank:106.0389577060098, Sara:65.55050553691686]";
	
	/** The items ranking. */
	Ranking<CollectedItemsScore> itemsRanking;
	
	/** The mining ranking. */
	Ranking<MiningScore> miningRanking;
	
	/** The movement ranking. */
	Ranking<PlayerMovementScore> movementRanking;
	
	/** The xp ranking. */
	Ranking<XPScore> xpRanking;
	
	/** The game. */
	BlockWorld game;
	
	/** The world. */
	World world;
	
	/** The xp score. */
	XPScore xpScore;
	
	/** The p sara. */
	Player pJulia, pFrank, pSara;
	
	/** The item score. */
	CollectedItemsScore itemScore;
	
	/** The mining score. */
	MiningScore miningScore;
	
	/** The movement score. */
	PlayerMovementScore movementScore;
	
	
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
		itemsRanking = new Ranking<CollectedItemsScore>();
		miningRanking = new Ranking<MiningScore>();
		movementRanking = new Ranking<PlayerMovementScore>();
		xpRanking = new Ranking<XPScore>();
		
		
		game = BlockWorld.getInstance();
		world = new World(3,10,"A Little World", "Joan");
		pJulia = new Player("Julia",world);
		pSara = new Player("Sara",world);	
		pFrank = new Player("Frank",world);
		itemScore = null; miningScore = null; movementScore = null; xpScore = null;
	} 

	
	/**
	 * Test add score 1.
	 */
	/* Se a??ade inicialmente a PlayerMovementScore la ubicaci??n  inicial de Julia.
	 * Se crea para Julia los 4 tipos de Scores y se a??aden a los distintos Ranking. Se 
	 * comprueba que la salida de los ranking (construida en el m??todo auxiliar makeOutputToString())
	 * coincide con kRANKING1.
	 * Realiza lo mismo ahora para Sara, creando los 4 tipo de Scores a partir de los siguientes elementos:
	 * ItemStack(Material.IRON_PICKAXE,1)
	 * Block(Material.OBSIDIAN)
	 * Location(world,0,67,1)
	 * y a??adi??ndolos a los distintos Ranking anteriores.
	 * Comprobar que la salida con makeOutputToString(), coincide con kRANKIG2
	 */
	@Test
	public void testAddScore1() {
		try {
			addScoresGame(pJulia,"Julia",null,null,new Location(pJulia.getLocation()),true);	
			addScoresGame(pJulia,"Julia",new ItemStack(Material.BREAD,5),BlockFactory.createBlock(Material.GRANITE),new Location(world,-3,80,4),false);
			addScoresToRanking();
			compareRankings(kRANKING1, rankingsToString());
					
			addScoresGame(pSara,"Sara",null,null,new Location(pSara.getLocation()),true);
			addScoresGame(pSara,"Sara",new ItemStack(Material.IRON_PICKAXE,1),BlockFactory.createBlock(Material.OBSIDIAN),new Location(world,0,67,1),false);
			addScoresToRanking();
			System.out.println(rankingsToString());
			compareRankings(kRANKING2, rankingsToString());
		} catch (Exception e) {
			fail ("Error, no debi?? lanzar la excepcion "+e.getClass().getName());
		}
	}

	/**
	 * Test add score 2.
	 */
	/* Partiendo, por ejemplo del testAddScore1(), puedes a??adir nuevos elementos a los distintos Scores de
	 * los distintos Player. A??adir un nuevo player, pFrank, crear Scores para ??l y comprobar en pantalla
	 * qu?? salida obtienes (con System.out.println(makeOutputToString()); 
	 * Para evitar que te quede un test muy largo, puedes usar los m??todos de ayuda que hay al final
	 * de este test suite, para crear/a??adir nuevos valores a los distintos scores, y para a??adir ??stos
	 * a los distintos Ranking.
	 */
	@Test
	public void testAddScore2() {
		try {
			addScoresGame(pSara,"Sara",null,null,new Location(pSara.getLocation()),true);
			addScoresGame(pSara,"Sara",new ItemStack(Material.IRON_PICKAXE,1),BlockFactory.createBlock(Material.OBSIDIAN),new Location(world,0,67,1),false);
			addScoresGame(pSara,"Sara",new ItemStack(Material.GRANITE,1),BlockFactory.createBlock(Material.GRASS),null,false);
			addScoresGame(pSara,"Sara",null,BlockFactory.createBlock(Material.GRASS),new Location(world,2,0,2),false);
			
			addScoresToRanking();		
			
			addScoresGame(pFrank,"Frank",null,null,new Location(pFrank.getLocation()),true);
			addScoresGame(pFrank,"Frank",new ItemStack(Material.IRON_PICKAXE,1),BlockFactory.createBlock(Material.OBSIDIAN),new Location(world,0,67,1),false);
			addScoresGame(pFrank,"Frank",new ItemStack(Material.IRON_SHOVEL,1),BlockFactory.createBlock(Material.SAND),new Location(world,0,255,0),false);
			addScoresGame(pFrank,"Frank",new ItemStack(Material.WATER_BUCKET,1),BlockFactory.createBlock(Material.STONE),null,false);
			
			addScoresToRanking();
			
			addScoresGame(pJulia,"Julia",null,null,new Location(pJulia.getLocation()),true);
			addScoresGame(pJulia,"Julia",new ItemStack(Material.BREAD,5),BlockFactory.createBlock(Material.GRANITE),new Location(world,-3,80,4),false);
			addScoresGame(pJulia,"Julia",new ItemStack(Material.APPLE,5), null, new Location(world,2,20,-1),false);
			addScoresGame(pJulia,"Julia",null, BlockFactory.createBlock(Material.CHEST), new Location(world,-3,254,-1),false);
			addScoresToRanking();
			
			//System.out.println(makeOutputToString());
			
			compareRankings(kRANKING3, rankingsToString());
			
		} catch (Exception e) {
			fail ("Error, no debi?? lanzar la excepcion "+e.getClass().getName());
		}
	}

	/**
	 * Test get winner exception.
	 *
	 * @throws EmptyRankingException the empty ranking exception
	 */
	/* Comprobaci??n de la excepci??n EmptyRankingException con getWinner()
	 * cuando no hay valores en los rankings.
	 */
	@Test(expected=EmptyRankingException.class)
	public void testGetWinnerException() throws EmptyRankingException {
		try {
			itemsRanking.getWinner();
		} catch (EmptyRankingException e) {
			try {
				miningRanking.getWinner();
			} catch (EmptyRankingException e1) {
				try {
					movementRanking.getWinner();
				} catch (EmptyRankingException e2) {
					xpRanking.getWinner();
				}
			}
		}
	}

	

	
	
	/**
	 * ***************************************************************.
	 *
	 * @param pl the pl
	 * @param name the name
	 * @param is the is
	 * @param b the b
	 * @param loc the loc
	 * @param newScores the new scores
	 */
	//FUNCIONES DE APOYO
	/* Permite, para el Player 'pl', incrementar los distintos Scores si newScores es false, o
	 * iniciar los distintos Scores para el Player 'pl' si newScores es true. Si un determinado score no 
	 * queremos incrementarlo, se pasa null por par??metro 
	 */
	private void addScoresGame (Player pl, String name, ItemStack is, Block b, Location loc, boolean newScores) {
		if (newScores) {
			xpScore = new XPScore(pl);
			itemScore = new CollectedItemsScore(name);
			miningScore = new MiningScore(name);
			movementScore = new PlayerMovementScore(name);
		}
		try {
			//Anadimos el valor del ItemStack al CollectedItemScore 
			if (is!=null) {
				itemScore.score(is);
			}
			//A??adimos un Block al Score 
			if (b!=null) {
				miningScore.score(b);
			}
		}catch (Exception e) {
			fail ("Error, no debi?? lanzar la excepcion "+e.getClass().getName());
			}
		//A??adimos un PlayerMovementScore al marcador del Player pl
		if (loc!=null) {
			movementScore.score(new Location(loc));
			
		}
	}
	
	
	/**
	 * Adds the scores to ranking.
	 */
	/* A??ade los distintos valores de los Scores a los Ranking*/
	private void addScoresToRanking () {
		xpScore.addScore(itemScore); 
		xpScore.addScore(miningScore); 
		xpScore.addScore(movementScore);
		itemsRanking.addScore(itemScore);
		miningRanking.addScore(miningScore);
		movementRanking.addScore(movementScore);
		xpRanking.addScore(xpScore);
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
	 * Rankings to string.
	 *
	 * @return the string
	 */
	/* Construye la salida como String, con los distintos Ranking */
	private String rankingsToString () {
		StringBuilder ps = new StringBuilder();
		ps.append("Collected items ranking = "+itemsRanking.getSortedRanking()+"\n");
		ps.append("Mining ranking = "+miningRanking.getSortedRanking()+"\n");
		ps.append("Movement ranking = "+movementRanking.getSortedRanking()+"\n");
		ps.append("Experience ranking = "+xpRanking.getSortedRanking());
		return ps.toString();
	}
}
