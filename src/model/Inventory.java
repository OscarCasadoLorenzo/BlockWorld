package model;

import model.exceptions.*;
import java.util.ArrayList;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class Inventory.
 */
public class Inventory {
	
	/** The items. */
	private ArrayList<ItemStack> items;
	
	/** The in hand. */
	private ItemStack inHand;
	
	/**
	 * Instantiates a new inventory.
	 */
	public Inventory() {
		this.inHand = null;
		//El vector ha de estar inicialmnete vacio
		this.items = new ArrayList<ItemStack>(); 
	}
	
	/**
	 * Instantiates a new inventory.
	 *
	 * @param i the i
	 */
	public Inventory(Inventory i) {
		this.items = new ArrayList<ItemStack>(); 
		
		for (ItemStack itemStack : i.items) {
			this.items.add(itemStack);
		}
		
		this.inHand = i.inHand;
	}
	
	/**
	 * Gets the item in hand.
	 *
	 * @return the item in hand
	 */
	public ItemStack getItemInHand() {return this.inHand;}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {return this.items.size();}
	
	/**
	 * Gets the item.
	 *
	 * @param slot the slot
	 * @return the item
	 */
	public ItemStack getItem(int slot){
		if(slot >= this.items.size() || slot < 0)
			return null;
		return this.items.get(slot);
	}
	
	
	/**
	 * Adds the item.
	 *
	 * @param item the item
	 * @return the int
	 */
	public int addItem(ItemStack item) {
		this.items.add(item);
		return item.getAmount();
	}
	
	/**
	 * Clear.
	 */
	public void clear() {
		//Vaciar el inventario
		this.items.clear();
		//Eliminamos el item que llevamos en la mano
		this.inHand = null;
	}
	
	/**
	 * Clear.
	 *
	 * @param slot the slot
	 * @throws BadInventoryPositionException the bad inventory position exception
	 */
	public void clear (int slot) throws BadInventoryPositionException{
		if(slot >= this.items.size())
			throw new BadInventoryPositionException(slot);
			
		this.items.remove(slot);
	}
	
	/**
	 * First.
	 *
	 * @param type the type
	 * @return the int
	 */
	public int first(Material type) {
		int slot = -1;
		
		//Recorremos el arrayList
		for(int i = 0; i < getSize() && slot == -1; i++)
			if(items.get(i).getType().equals(type))
				slot = i;
		
		return slot;
	}
	
	/**
	 * Sets the item.
	 *
	 * @param slot the slot
	 * @param item the item
	 * @throws BadInventoryPositionException the bad inventory position exception
	 */
	public void setItem (int slot, ItemStack item) throws BadInventoryPositionException{
		//Hay que comprobar si el slot existe
		if(slot >= 0  && slot < getSize())
			this.items.set(slot, item);
		else throw new BadInventoryPositionException(slot);	
	}
	
	/**
	 * Sets the item in hand.
	 *
	 * @param items the new item in hand
	 */
	public void setItemInHand(ItemStack items) {
		this.inHand = items;
	}
	
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inHand == null) ? 0 : inHand.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		if (inHand == null) {
			if (other.inHand != null)
				return false;
		} else if (!inHand.equals(other.inHand))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String out = "(inHand=";
		if(this.inHand == null)
			out += "null"  + ",[";
		else out += inHand.toString() + ",[";
		
		
		//Imprimimos el arrayList de 'items'
		for(int i = 0; i < getSize(); i++) {
			if(i == getSize() - 1)	
				out += this.items.get(i).toString();
			//Si no se trata de la ultima posicion añadimos una ','
			else out += this.items.get(i).toString() + ", ";	
		}
		
		out += "])";
		return out;
	}
}
