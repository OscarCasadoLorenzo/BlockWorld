package model;

import model.exceptions.WrongMaterialException;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class LiquidBlock.
 */
public class LiquidBlock extends Block{
	
	/** The damage. */
	private double damage;
	
	/**
	 * Instantiates a new liquid block.
	 *
	 * @param type the type
	 * @throws WrongMaterialException the wrong material exception
	 */
	public LiquidBlock(Material type) throws WrongMaterialException {
		super(type);
		
		if(!type.isLiquid())
			throw new WrongMaterialException(type);
		this.damage = type.getValue();
	}
	
	/**
	 * Instantiates a new liquid block.
	 *
	 * @param lb the lb
	 */
	protected LiquidBlock(LiquidBlock lb) {
		super(lb);
		this.damage = lb.getDamage();
	}
	
	/**
	 * Clone.
	 *
	 * @return the block
	 */
	public Block clone() {
		return new LiquidBlock(this);
	}
	
	/**
	 * Gets the damage.
	 *
	 * @return the damage
	 */
	public double getDamage() {
		return this.damage;
	}
}
