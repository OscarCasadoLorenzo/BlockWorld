package model.persistence;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import model.*;
import model.entities.*;
import model.exceptions.*;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class WorldAdapter.
 */
public class WorldAdapter implements IWorld{
	
	/** The player. */
	private IPlayer player;
	
	/** The world. */
	private World world;
	
	/**
	 * Instantiates a new world adapter.
	 *
	 * @param w the w
	 */
	public WorldAdapter(World w) {
		super();
		this.player = new PlayerAdapter(w.getPlayer());
		this.world = w;
	}
	
	/**
	 * Gets the negative limit.
	 *
	 * @return the negative limit
	 */
	public int getNegativeLimit() {
		if(this.world.getSize() % 2 == 0) {
			return -((this.world.getSize()/2)-1);
		}
		else {
			return -(this.world.getSize()/2);
		}
	}
	
	/**
	 * Gets the positive limit.
	 *
	 * @return the positive limit
	 */
	public int getPositiveLimit() {
		return this.world.getSize()/2;
	}


	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	@Override
	public IPlayer getPlayer() {
		return this.player;
	}

	/**
	 * Gets the map block.
	 *
	 * @param loc the loc
	 * @return the map block
	 */
	public NavigableMap<Location,Block> getMapBlock(Location loc){

		NavigableMap<Location,Block> mapBlock =new TreeMap<Location,Block>();
		
		Block bloque = null;
		
		for (int x=0; x<16; x++) 
			for(int y=0; y<16; y++) 
				for(int z=0; z<16; z++) {
					
					Location posAbs =new Location(this.world, loc.getX() + x, loc.getY() + y, loc.getZ() + z);
					Location posRel = new Location(this.world,x,y,z);
					
					//Si la posición está dentro de los limites del mundo...
					if(Location.check(posAbs)==true) {
							
							//...y si da el caso añadimos el bloque a la lista
							try {
								bloque=this.world.getBlockAt(posAbs);
							} catch (BadLocationException e) {
								e.printStackTrace();
							}
							
							//...sino lo hay colocamos aire en el mapa
							if(bloque!=null) {
								mapBlock.put(posRel, bloque);
								
							}
							
							//...comprobamos si hay un bloque en dicha posicion
							else {
								try {
									mapBlock.put(posRel,new LiquidBlock(Material.AIR));
								} catch (WrongMaterialException e) {
									e.printStackTrace();
								}
							}
					}
					else {
						mapBlock.put(posRel,null);
					}
				}
			
		return mapBlock;
	}
	
	/**
	 * Gets the creatures.
	 *
	 * @param loc the loc
	 * @return the creatures
	 */
	@Override
	public List<Creature> getCreatures(Location loc) {

		List<Creature> listCreatures = new ArrayList<Creature>();
		
		Creature creature = null;
		
		for (int x=0; x<16; x++) 
			for(int y=0; y<16; y++) 
				for(int z=0; z<16; z++) {
					
					Location coord = new Location(this.world, x, y, z);
					coord.add(loc);
					
					try {
						creature = this.world.getCreatureAt(coord);
					}
					catch (Exception e){
						e.printStackTrace();
					}
					
					if(creature != null)
						listCreatures.add(creature);
				}
			
		return listCreatures;
	}

	/**
	 * Gets the items.
	 *
	 * @param loc the loc
	 * @return the items
	 */
	@Override
	public Map<Location, ItemStack> getItems(Location loc) {
		Map<Location, ItemStack> mapItems = new HashMap<>();
		
		ItemStack item = null;
		
		for (int x=0; x<16; x++) 
			for(int y=0; y<16; y++) 
				for(int z=0; z<16; z++) {
					
					Location coord = new Location(this.world, x, y, z);
					coord.add(loc);
					
					try {
						item = this.world.getItemsAt(coord);
					}
					catch (Exception e){
						e.printStackTrace();
					}
					
					if(item != null)
						mapItems.put(coord, item);	
				}
			
		return mapItems;
	}
}
