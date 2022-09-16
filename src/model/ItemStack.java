package model;

import model.exceptions.*;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class ItemStack.
 */
public class ItemStack {
	
	/** The amount. */
	private int amount;
	
	/** The type. */
	private Material type;
	
	/** The Constant MAX_STACK_SIZE. */
	/* En el UML este atributo se muestra subrayado (static) y junto
	 * a la clave <<const>> lo que indica que dicho valor ha de ser inmutable
	 * para lograr esto tenemos que tener en cuenta lo siguiente:
	 * ' Cuando usamos “static final” se dice que creamos una constante de clase, 
	 *   un atributo común a todos los objetos de esa clase, que además solo ocupa
	 *   una única posición de memoria por muchos objetos que creemos de esa clase.'
	 */
	public static final int MAX_STACK_SIZE = 64;
	
	/**
	 * Instantiates a new item stack.
	 *
	 * @param type the type
	 * @param amount the amount
	 * @throws StackSizeException the stack size exception
	 */
	public ItemStack(Material type, int amount) throws StackSizeException{
		if( (type.isTool() || type.isWeapon()) && amount != 1 )
			throw new StackSizeException();
		
		this.type = type;
		
		setAmount(amount);
	}

	/**
	 * Instantiates a new item stack.
	 *
	 * @param item the item
	 */
	public ItemStack(ItemStack item) {
		this.amount = item.amount;
		this.type = item.type;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public Material getType() {return this.type;}
	
	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public int getAmount() {return this.amount;}
	
	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 * @throws StackSizeException the stack size exception
	 */
	public void setAmount(int amount) throws StackSizeException{
		if(amount < 1 || amount > MAX_STACK_SIZE )
			throw new StackSizeException();
		if((this.type.isTool() || this.type.isWeapon()) && amount != 1)
			throw new StackSizeException();
		
		this.amount = amount;
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
		result = prime * result + amount;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ItemStack other = (ItemStack) obj;
		if (amount != other.amount)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "(" + this.type + ","+ this.amount + ")";
	}
}
