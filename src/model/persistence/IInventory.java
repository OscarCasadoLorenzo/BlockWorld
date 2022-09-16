package model.persistence;

import model.*;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Interface IInventory.
 */
public interface IInventory {
		
	/**
	 * Gets the item.
	 *
	 * @param pos the pos
	 * @return the item
	 */
	public ItemStack getItem(int pos);
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize();
	
	/**
	 * In hand item.
	 *
	 * @return the item stack
	 */
	public ItemStack inHandItem();
}
