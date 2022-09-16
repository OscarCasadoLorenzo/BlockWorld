package model.persistence;

import model.Inventory;
import model.ItemStack;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class InventoryAdapter.
 */
public class InventoryAdapter implements IInventory{
	
	/** The inventory. */
	private Inventory inventory;

	/**
	 * Instantiates a new inventory adapter.
	 *
	 * @param inv the inv
	 */
	public InventoryAdapter(Inventory inv) {
		this.inventory = inv;
	}
	
	/**
	 * Gets the item.
	 *
	 * @param pos the pos
	 * @return the item
	 */
	@Override
	public ItemStack getItem(int pos) {
		return this.inventory.getItem(pos);
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	@Override
	public int getSize() {
		return this.inventory.getSize();
	}

	/**
	 * In hand item.
	 *
	 * @return the item stack
	 */
	@Override
	public ItemStack inHandItem() {
		return this.inventory.getItemInHand();
	}
	
}
