package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.entities.*;
import model.exceptions.*;
import model.persistence.PlayerAdapter;
import model.score.*;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class BlockWorld.
 */
public class BlockWorld {

	/** The world. */
	private World world;
	
	/** The instance. */
	private static BlockWorld instance;
	
	/** The items score. */
	private CollectedItemsScore itemsScore;
	
	/** The mining score. */
	private MiningScore miningScore;
	
	/** The movement score. */
	private PlayerMovementScore movementScore;
	
	/**
	 * Instantiates a new block world.
	 */
	private BlockWorld() {
		this.world = null;
		
		this.itemsScore = null;
		this.miningScore = null;
		this.movementScore = null;
	}
	
	/**
	 * Gets the single instance of BlockWorld.
	 *
	 * @return single instance of BlockWorld
	 */
	public static BlockWorld getInstance() {
		if(instance == null)
			instance = new BlockWorld();
		return instance;
	}
	
	/**
	 * Creates the world.
	 *
	 * @param seed the seed
	 * @param size the size
	 * @param name the name
	 * @param playerName the player name
	 * @return the world
	 */
	public World createWorld(long seed, int size, String name, String playerName) {
		this.world = new World(seed, size, name, playerName);
		
		this.itemsScore = new CollectedItemsScore(playerName);
		this.miningScore = new MiningScore(playerName);
		this.movementScore = new PlayerMovementScore(playerName);
		
		return this.world;
	}
	
	/**
	 * Show player info.
	 *
	 * @param player the player
	 * @return the string
	 */
	public String showPlayerInfo(Player player) {
		String out = "";
		
		try {
			 out = player.toString() + world.getNeighbourhoodString(player.getLocation());
			 out += "Scores: [items: " + this.itemsScore.getScoring() + 
					", blocks: " + this.miningScore.getScoring() + 
					", movements: " + this.movementScore.getScoring() + "]\n";
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return out;
	}
	
	/**
	 * Move player.
	 *
	 * @param player the player
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @throws EntityIsDeadException the entity is dead exception
	 * @throws BadLocationException the bad location exception
	 */
	public void movePlayer(Player player, int x, int y, int z) throws EntityIsDeadException, BadLocationException{

			Location destiny = new Location(this.world, x, y, z);

			player.move((int)destiny.getX(), (int)destiny.getY(), (int)destiny.getZ());
			//PUNTUACIÓN POR MOVIMIENTO
			this.movementScore.score(player.getLocation());
			
			//Si hay un objeto en dicha posición lo coge y lo elimina
			if(this.world.getItemsAt(player.getLocation()) != null) {
				player.addItemsToInventory(this.world.getItemsAt(player.getLocation()));
				
				//PUNTUACION POR ITEMS RECOGIDOS
				this.itemsScore.score(this.world.getItemsAt(player.getLocation()));
				this.world.removeItemsAt(player.getLocation());
			}
			
			
	}
	
	/**
	 * Select item.
	 *
	 * @param player the player
	 * @param slot the slot
	 * @throws BadInventoryPositionException the bad inventory position exception
	 */
	public void selectItem(Player player, int slot) throws BadInventoryPositionException{
		try {
			player.selectItem(slot);
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Use item.
	 *
	 * @param player the player
	 * @param times the times
	 * @throws EntityIsDeadException the entity is dead exception
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public void useItem(Player player, int times) throws EntityIsDeadException, IllegalArgumentException{
		try {
			boolean thereIsBlock = false;
			Block posibleBlock = world.getBlockAt(player.getOrientation());
		
			if(posibleBlock != null && posibleBlock instanceof SolidBlock) 
				thereIsBlock = true;				
			
			player.useItemInHand(times);
			
			if(thereIsBlock && world.getBlockAt(player.getOrientation()) == null)
				this.miningScore.score(posibleBlock);
			
		}catch(WrongMaterialException | StackSizeException | BadLocationException e) {
			e.getMessage();
		}		
	}
	
	/**
	 * Gets the items score.
	 *
	 * @return the items score
	 */
	public CollectedItemsScore getItemsScore() {
		return this.itemsScore;
	}
	
	/**
	 * Gets the mining score.
	 *
	 * @return the mining score
	 */
	public MiningScore getMiningScore() {
		return this.miningScore;
	}
	
	/**
	 * Gets the movement score.
	 *
	 * @return the movement score
	 */
	public PlayerMovementScore getMovementScore() {
		return this.movementScore;
	}
	
	/**
	 * Orientate player.
	 *
	 * @param p the p
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @throws EntityIsDeadException the entity is dead exception
	 * @throws BadLocationException the bad location exception
	 */
	public void orientatePlayer(Player p, int x, int y, int z) throws EntityIsDeadException, BadLocationException {
		p.orientate(x, y, z);
	}
	
	/**
	 * Play.
	 *
	 * @param sc the sc
	 */
	private void play(Scanner sc) {
		Long seed = sc.nextLong();
		int size = sc.nextInt();
		String playerName = sc.next();
		String worldname = sc.nextLine().substring(1);


		this.createWorld(seed, size, worldname, playerName);
		
		String linea = "";
		String rubber = "";
		
		//Mientras que el fichero contenga información
		while(sc.hasNext()) {
			//... y el jugador no esté muerto
			if(world.getPlayer().isDead() == true) {
				break;
			}
			
			linea = sc.next();
			
			//ORDEN: move
			try {
				if(linea.equals("move")) {
					//Extraemos los datos para llamar a la función
					int dx = sc.nextInt();
					int dy = sc.nextInt();
					int dz = sc.nextInt();
					this.movePlayer(this.world.getPlayer(), dx, dy, dz);
				}
			}catch(EntityIsDeadException | BadLocationException e) {
				System.err.println(e.getMessage());
			}
			
			//ORDEN: orientate
			try {
				if(linea.equals("orientate")){
					//Extraemos los datos para llamar a la función
					int dx = sc.nextInt();
					int dy = sc.nextInt();
					int dz = sc.nextInt();
					this.orientatePlayer(this.world.getPlayer(), dx, dy, dz);
				}
			}catch(BadLocationException | EntityIsDeadException e) {
				System.err.println(e.getMessage());
			}
			
			//ORDEN: useItem
			try {
				if(linea.equals("useItem")) {
					//Extraemos los datos para llamar a la función
					int veces = sc.nextInt();
					this.useItem(this.world.getPlayer(), veces);
				}
			}catch(EntityIsDeadException | IllegalArgumentException e) {
				System.err.println(e.getMessage());
			}catch(NullPointerException e) {
				rubber = "";
			}
			
			//ORDEN: showPlayerInfo
			if(linea.equals("show")) {
				System.out.println(this.showPlayerInfo(this.world.getPlayer()));
			}
			
			try {
				//ORDEN: selectItem
				if(linea.equals("selectItem")) {
					//Extraemos los datos para llamar a la función
					int posi = sc.nextInt();
					this.world.getPlayer().selectItem(posi);
				}
				else {
					rubber = sc.nextLine();
				}
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Play file.
	 *
	 * @param nameFile the name file
	 * @throws FileNotFoundException the file not found exception
	 */
	public void playFile(String nameFile) throws FileNotFoundException {
		File file = new File(nameFile);
		Scanner command = new Scanner(file);
		play(command);
		command.close();
	}
	
	/**
	 * Play from console.
	 */
	public void playFromConsole() {
		Scanner console = new Scanner(System.in);
		play(console);
		console.close();
	}
}
