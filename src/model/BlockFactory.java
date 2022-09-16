package model;

import model.exceptions.WrongMaterialException;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * A factory for creating Block objects.
 */
public class BlockFactory {

	/**
	 * Creates a new Block object.
	 *
	 * @param type the type
	 * @return the block
	 * @throws WrongMaterialException the wrong material exception
	 */
	public static Block createBlock(Material type) throws WrongMaterialException{
		
		Block block = null;
		if(type.isLiquid()) 
			block = new LiquidBlock(type);
			
		else block = new SolidBlock(type);
		
		return block;
	}
}
