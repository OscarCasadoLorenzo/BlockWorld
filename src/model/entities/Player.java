package model.entities;

import java.util.Set;
import model.*;
import model.exceptions.BadInventoryPositionException;
import model.exceptions.BadLocationException;
import model.exceptions.EntityIsDeadException;
import model.exceptions.StackSizeException;
import model.exceptions.WrongMaterialException;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class Player.
 */
public class Player extends LivingEntity{
	
	/** The symbol. */
	//<Atributos>
	private char symbol = 'P';
	
	/** The name. */
	private String name;
	
	/** The Constant MAX_FOODLEVEL. */
	public static final double MAX_FOODLEVEL = 20;
	
	/** The food level. */
	private double foodLevel;
	
	/** The inventory. */
	private Inventory inventory;
	
	/** The orientation. */
	private Location orientation;
	
	/**
	 * Instantiates a new player.
	 *
	 * @param name the name
	 * @param world the world
	 */
	public Player(String name, World world) {
		super(new Location(world, 0, 0, 0), MAX_HEALTH);
		try {
			 this.location.setY( this.getLocation().getWorld().getHighestLocationAt(this.location).getY() + 1);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		this.name = name;
		this.foodLevel = MAX_FOODLEVEL;
		this.orientation = new Location(world, 0,0,1);
		
		this.inventory = new Inventory();
		try {
			this.inventory.setItemInHand(new ItemStack (Material.WOOD_SWORD,1));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Gets the food level.
	 *
	 * @return the food level
	 */
	public double getFoodLevel() {return this.foodLevel;}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {return this.name;}
	
	/**
	 * Gets the inventory size.
	 *
	 * @return the inventory size
	 */
	public int getInventorySize() {return this.inventory.getSize();}
	
	/**
	 * Gets the inventory.
	 *
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return new Inventory(this.inventory);
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() {return this.symbol;}
	
	/**
	 * Gets the orientation.
	 *
	 * @return the orientation
	 */
	//Lo implementamos de manera relativa
	public Location getOrientation() {
		Location absoluteOrientation = new Location(this.location);
		absoluteOrientation.add(this.orientation);
		return absoluteOrientation;
	}
	
	/**
	 * Orientate.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the location
	 * @throws EntityIsDeadException the entity is dead exception
	 * @throws BadLocationException the bad location exception
	 */
	public Location orientate(int x, int y, int z) throws EntityIsDeadException, BadLocationException {
		if(this.isDead())
			throw new EntityIsDeadException();
		
		Location looking = new Location(this.location.getWorld(), x, y, z);
		
		if((x == 0 && y == 0 && z == 0) || !((x >= -1) && (x <= +1) && (y >= -1) && (y <= +1) && (z >= -1) && (z <= +1)))
			throw new BadLocationException("No se puede orientar a un jugador muerto.");
		
		this.orientation = new Location(looking);
		looking.add(this.location);
		return looking;
	}
	
	/**
	 * Sets the food level.
	 *
	 * @param foodLevel the new food level
	 */
	public void setFoodLevel(double foodLevel) {
		if(foodLevel > MAX_FOODLEVEL)
			this.foodLevel = MAX_FOODLEVEL;
		else this.foodLevel = foodLevel;
	}
	
	/**
	 * Decrease food level.
	 *
	 * @param damage the damage
	 */
	public void decreaseFoodLevel(double damage) {
		//Si la cantidad de alimento desciende de 0
		if((this.foodLevel - damage) < 0) {
			//Nos quita vida
			damage(damage - this.foodLevel );
			this.foodLevel = 0;
		}
			
		else this.foodLevel -= damage;
	}

	/**
	 * Increase food level.
	 *
	 * @param heal the heal
	 */
	public void increaseFoodLevel(double heal) {
		//Si la cantidad de alimento asciende del maximo
		if((this.foodLevel + heal) > MAX_FOODLEVEL) {
			//Nos da vida, lo que reste de haber llegado al maxFoodLevel
			setHealth(this.getHealth() + (heal - (MAX_FOODLEVEL - this.foodLevel))); 
			this.foodLevel = MAX_FOODLEVEL;
		}
			
		else this.foodLevel += heal;
	}


	
	/**
	 * Move.
	 *
	 * @param dx the dx
	 * @param dy the dy
	 * @param dz the dz
	 * @return the location
	 * @throws EntityIsDeadException the entity is dead exception
	 * @throws BadLocationException the bad location exception
	 */
	public Location move(int dx, int dy, int dz) throws EntityIsDeadException, BadLocationException{
		if(this.isDead())
			throw new EntityIsDeadException();
		
		Location destiny = new Location(this.location.getWorld(), dx, dy, dz);
		destiny.add(this.location);
		
		Set<Location> vecindario = this.location.getNeighborhood();
		
		if(!vecindario.contains(destiny) || !destiny.isFree())
			throw new BadLocationException("Posicion no adyacente.");
		
		if(this.location.getWorld().getBlockAt(destiny) != null){
			try {
				Block liquid= BlockFactory.createBlock(this.location.getWorld().getBlockAt(destiny).getType());
				if(liquid instanceof LiquidBlock)
					damage(((LiquidBlock) liquid).getDamage());
			}catch(WrongMaterialException w){
				w.printStackTrace();
			}
			
			
		}
		
		this.location = destiny;
		decreaseFoodLevel(0.05);
		
		return this.location;
	}
	
	/**
	 * Use item in hand.
	 *
	 * @param times the times
	 * @return the item stack
	 * @throws EntityIsDeadException the entity is dead exception
	 * @throws BadLocationException the bad location exception
	 * @throws WrongMaterialException the wrong material exception
	 * @throws StackSizeException the stack size exception
	 */
	public ItemStack useItemInHand(int times) throws EntityIsDeadException, BadLocationException, WrongMaterialException, StackSizeException{
		if(times <= 0)
			throw new IllegalArgumentException();
			
		if(this.isDead())
			throw new EntityIsDeadException();
		
		
		if( inventory.getItemInHand() != null) {	//Si llevamos algo en la mano...
			//... y es comida...
			if(inventory.getItemInHand().getType().isEdible()) { 	
				
				//REAJUSTAMOS LA CANTIDAD DE ITEMS
				
				//...si gastamos el item
				if(times >= inventory.getItemInHand().getAmount() ) {	
					times = inventory.getItemInHand().getAmount();
					increaseFoodLevel(times*inventory.getItemInHand().getType().getValue());
					this.inventory.setItemInHand(null);
				}
				//...si aun nos queda cantidad del item
				else {	
					increaseFoodLevel(times*inventory.getItemInHand().getType().getValue());
					try {
						this.inventory.getItemInHand().setAmount(inventory.getItemInHand().getAmount() - times);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			//...y es un bloque, arma o herramienta...
			else {
				//La propia orden ya produce un decremento de alimento
				decreaseFoodLevel(0.1*times);
				
				double damage;
				if(this.inventory.getItemInHand().getType().isBlock())
					damage = 0.1;
				else damage = this.inventory.getItemInHand().getType().getValue();
				
				//APLICADO SOBRE UNA CRIATURA
				if(this.location.getWorld().getCreatureAt(getOrientation()) != null) {
					Creature creature = this.location.getWorld().getCreatureAt(getOrientation());
					
					//Le metemos la ostia
					creature.damage(damage*times);
					
					//Comprobamos si sigue viva
					if(!creature.isDead()) {
						//..si es un monstruo nos daña
						if(creature instanceof Monster && !creature.isDead())
							this.damage(0.5*times);
					}
					else {							
						this.location.getWorld().killCreature(getOrientation());	
						if(creature instanceof Animal)
							this.location.getWorld().addItems(getOrientation(), new ItemStack(Material.BEEF, 1));
					}
				}
				
				//APLICADO SOBRE UN BLOQUE
				else if(this.location.getWorld().getBlockAt(getOrientation()) != null) {
					Block block = BlockFactory.createBlock(this.location.getWorld().getBlockAt(getOrientation()).getType());
					
					
					if(block instanceof SolidBlock) {
						if(((SolidBlock) block).breaks(damage*times)) {
							ItemStack drops = ((SolidBlock) block).getDrops();							
							
							this.location.getWorld().destroyBlockAt(getOrientation());
							if(drops != null)
								this.location.getWorld().addItems(getOrientation(), drops);
						}
					}
					
				}
				
				//APLICADO SOBRE UNA POSICION VACIA
				else if(this.location.getWorld().getItemsAt(getOrientation()) == null && 
						this.inventory.getItemInHand().getType().isBlock()) {
					
					Block toPut = BlockFactory.createBlock(inventory.getItemInHand().getType());
					this.location.getWorld().addBlock(getOrientation(), toPut);

				}
				
				//APLICADO SOBRE UNA POSICION CON ITEMS NO HACE NADA
			}
		}
		
		return this.inventory.getItemInHand();
	}
	
	/**
	 * Select item.
	 *
	 * @param slot the slot
	 * @throws BadInventoryPositionException the bad inventory position exception
	 */
	public void selectItem(int slot) throws BadInventoryPositionException {
			if(this.inventory.getItem(slot) == null)
				throw new BadInventoryPositionException(slot);
			
			//Si tiene un item en la mano lo intercambia
			if(this.inventory.getItemInHand() != null) {
				//Deja el item que va a coger de la mochila en el suelo
				ItemStack dejaSuelo = this.inventory.getItem(slot);
				
				//Mete lo de la mano a la mochila
				this.inventory.setItem(slot, this.inventory.getItemInHand());
				//Recoge lo del suelo y se lo queda en la mano
				this.inventory.setItemInHand(dejaSuelo);
			}
			else {
				this.inventory.setItemInHand(this.inventory.getItem(slot));
				this.inventory.clear(slot);
			}
			
	}
	
	/**
	 * Adds the items to inventory.
	 *
	 * @param items the items
	 */
	public void addItemsToInventory(ItemStack items) {
		this.inventory.addItem(items);
	}
	

	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(foodLevel);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
		result = prime * result + symbol;
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (Double.doubleToLongBits(foodLevel) != Double.doubleToLongBits(other.foodLevel))
			return false;
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orientation == null) {
			if (other.orientation != null)
				return false;
		} else if (!orientation.equals(other.orientation))
			return false;
		if (symbol != other.symbol)
			return false;
		return true;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String out = "Name="+this.name+"\n"
					+this.location.toString()+"\n"
					+"Orientation="+this.orientation.toString()+"\n"
					+"Health="+this.getHealth()+"\n"
					+"Food level="+this.foodLevel+"\n"
					+"Inventory="+this.inventory.toString()+"\n";
		return out;
	}
}
