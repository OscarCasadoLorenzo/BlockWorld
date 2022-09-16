package model.persistence;

import model.Location;
import model.entities.*;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class PlayerAdapter.
 */
public class PlayerAdapter implements IPlayer{
	
	/** The inventory. */
	private IInventory inventory;
	
	/** The player. */
	private Player player;
	
	
	/**
	 * Instantiates a new player adapter.
	 *
	 * @param player the player
	 */
	public PlayerAdapter(Player player) {
		this.player = player;
		this.inventory = new InventoryAdapter(player.getInventory());
	}
	
	/**
	 * Gets the health.
	 *
	 * @return the health
	 */
	@Override
	public double getHealth() {
		return this.player.getHealth();
	}
	
	/**
	 * Gets the inventory.
	 *
	 * @return the inventory
	 */
	@Override
	public IInventory getInventory() {
		return this.inventory;
	}
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	@Override
	public Location getLocation() {
		return this.player.getLocation();
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Override
	public String getName() {
		return this.player.getName();
	}
		
	
}
