package model.persistence;

import model.*;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Interface IPlayer.
 */
public interface IPlayer {

	/**
	 * Gets the health.
	 *
	 * @return the health
	 */
	public double getHealth();
	
	/**
	 * Gets the inventory.
	 *
	 * @return the inventory
	 */
	public IInventory getInventory();
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public Location getLocation();
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();
}
