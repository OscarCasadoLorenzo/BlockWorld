package model;

import model.exceptions.StackSizeException;
import model.exceptions.WrongMaterialException;

/**
 *	@author Oscar Casado Lorenzo
 */
/**
 * The Class SolidBlock.
 */
public class SolidBlock extends Block{
	
	/** The drops. */
	private ItemStack drops;
	
	/**
	 * Instantiates a new solid block.
	 *
	 * @param type the type
	 * @throws WrongMaterialException the wrong material exception
	 */
	public SolidBlock(Material type) throws WrongMaterialException{
		super(type);
		
		if(!type.isBlock() || type.isLiquid())
			throw new WrongMaterialException(type);
		
		this.drops = null;
	}
	
	/**
	 * Instantiates a new solid block.
	 *
	 * @param sb the sb
	 */
	protected SolidBlock(SolidBlock sb) {
		super(sb);
		this.drops = sb.getDrops();
	}
	
	/**
	 * Clone.
	 *
	 * @return the block
	 */
	public Block clone() {
		return new SolidBlock(this);
	}
	
	/**
	 * Breaks.
	 *
	 * @param damage the damage
	 * @return true, if successful
	 */
	public boolean breaks(double damage) {
		if(damage >= this.getType().getValue())
			return true;
		else return false;
	}
	
	/**
	 * Gets the drops.
	 *
	 * @return the drops
	 */
	public ItemStack getDrops() {return this.drops;}
	
	/**
	 * Sets the drops.
	 *
	 * @param type the type
	 * @param amount the amount
	 * @throws StackSizeException the stack size exception
	 */
	public void setDrops(Material type, int amount) throws StackSizeException{
		if(!this.getType().equals(Material.CHEST) && amount != 1)
			throw new StackSizeException();
		else if(amount > ItemStack.MAX_STACK_SIZE)
			throw new StackSizeException();
		
		this.drops = new ItemStack(type, amount);
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((drops == null) ? 0 : drops.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SolidBlock other = (SolidBlock) obj;
		if (drops == null) {
			if (other.drops != null)
				return false;
		} else if (!drops.equals(other.drops))
			return false;
		return true;
	}
	
	
}