package model.persistence;

import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

import model.entities.*;
import model.*;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Interface IWorld.
 */
public interface IWorld {

	/**
	 * Gets the negative limit.
	 *
	 * @return the negative limit
	 */
	public int getNegativeLimit();
	
	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public IPlayer getPlayer();
	
	/**
	 * Gets the positive limit.
	 *
	 * @return the positive limit
	 */
	public int getPositiveLimit();
	
	/**
	 * Gets the map block.
	 *
	 * @param loc the loc
	 * @return the map block
	 */
	public NavigableMap<Location,Block> getMapBlock(Location loc);
	
	/**
	 * Gets the creatures.
	 *
	 * @param loc the loc
	 * @return the creatures
	 */
	public List<Creature> getCreatures(Location loc);
	
	/**
	 * Gets the items.
	 *
	 * @param loc the loc
	 * @return the items
	 */
	public Map<Location, ItemStack> getItems(Location loc);
}
