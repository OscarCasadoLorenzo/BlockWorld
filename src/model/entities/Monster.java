package model.entities;

import model.*;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class Monster.
 */
public class Monster extends Creature{
	
	/** The symbol. */
	private char symbol = 'M';
	
	/**
	 * Instantiates a new monster.
	 *
	 * @param loc the loc
	 * @param health the health
	 */
	public Monster(Location loc, double health) {
		super(loc, health);
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
		return "Monster [location=" + getLocation() + ", health="  + getHealth() + "]";
	}
	
	
}
