package model.entities;

import model.*;
import model.exceptions.StackSizeException;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class Animal.
 */
public class Animal extends Creature{
	
	/** The symbol. */
	private char symbol = 'L';
	
	/**
	 * Instantiates a new animal.
	 *
	 * @param loc the loc
	 * @param health the health
	 */
	public Animal(Location loc, double health) {
		super(loc, health);
	}
	
	/**
	 * Gets the drops.
	 *
	 * @return the drops
	 */
	public ItemStack getDrops(){
		ItemStack item = null;
		try {
			item = new ItemStack(Material.BEEF,1);
		} catch (StackSizeException e) {
			e.printStackTrace();
		}
		return item;
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() {
		return this.symbol;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Animal [location=" + getLocation() + ", health="  + getHealth() + "]";
	}
	
	
}
