package model;

import model.exceptions.WrongMaterialException;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class Block.
 */
public abstract class Block {
	
	/** The type. */
	private Material type;
	
	/**
	 * Instantiates a new block.
	 *
	 * @param type the type
	 * @throws WrongMaterialException the wrong material exception
	 */
	public Block(Material type) throws WrongMaterialException {
		if(!type.isBlock())
			throw new WrongMaterialException(type);
		
		this.type = type;
	}
	
	/**
	 * Instantiates a new block.
	 *
	 * @param block the block
	 */
	protected Block(Block block) {
		this.type = block.type;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public Material getType() {return this.type;}
	
	/**
	 * Clone.
	 *
	 * @return the block
	 */
	public abstract Block clone();
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Block other = (Block) obj;
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
		String out = "[" + type + "]";
		return out;
	}
}
