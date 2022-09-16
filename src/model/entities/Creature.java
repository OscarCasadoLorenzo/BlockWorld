package model.entities;

import model.*;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class Creature.
 */
public abstract class Creature extends LivingEntity{
	
	/**
	 * Instantiates a new creature.
	 *
	 * @param loc the loc
	 * @param health the health
	 */
	public Creature(Location loc, double health) {
		super(loc, health);
	}
}
