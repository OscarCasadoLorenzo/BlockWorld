package model;

import java.util.Random;

/**
 *	@author Oscar Casado Lorenzo
 */


/**
 * The Enum Material.
 */
/*
 * TODO
 * Hacer el atributo random
 */
public enum Material {
	/* Para crear un enumerado normal bastaría con listar
	 * los elementos que deseemos: BEDROCK, CHEST, SAND, etc;
	 * Sin embargo, si queremos que dichos elementos posean una serie 
	 * atributos debemos declararlos como privados.
	 * De esta forma, por cada uno de ellos debemos llamar a un contructor
	 * que es necesario implementar encargado de asignar los valores a 
	 * estos atributos de forma interna.
	 */
	
	//<TERRENOS>
	/** The bedrock. */
	//Significado de value: dureza del material.
	BEDROCK (-1, '*'),
	
	/** The chest. */
	CHEST (0.1, 'C'),
	
	/** The sand. */
	SAND (0.5, 'n'),
	
	/** The dirt. */
	DIRT (0.5, 'd'),
	
	/** The grass. */
	GRASS (0.6, 'g'),
	
	/** The stone. */
	STONE (1.5, 's'),
	
	/** The granite. */
	GRANITE (1.5, 'r'),
	
	/** The obsidian. */
	OBSIDIAN (5, 'o'),
	
	//<COMIDAS>
	/** The water bucket. */
	//Significado de value: punto de comida/salud que ganas al consumirla.
	WATER_BUCKET (1, 'W'),
	
	/** The apple. */
	APPLE (4, 'A'),
	
	/** The bread. */
	BREAD (5, 'B'),
	
	/** The beef. */
	BEEF (8, 'F'),
	
	//<HERRAMIENTAS>
	/** The iron shovel. */
	//Significado de value: dureza de la herramienta.
	IRON_SHOVEL (0.2, '>'),
	
	/** The iron pickaxe. */
	IRON_PICKAXE (0.5, '^'),
	
	//<ARMAS>
	/** The wood sword. */
	//Significado de value: dureza del material.
	WOOD_SWORD (1, 'i'),
	
	/** The iron sword. */
	IRON_SWORD (2, 'I'),
	
	/** The lava. */
	//<LÍQUIDOS>
	LAVA (1, '#'),
	
	/** The water. */
	WATER (0, '@'),
	
	/** The air. */
	AIR (0, ' ');
	
	
	/** The value. */
	//<Atributos>
	private double value;
	
	/** The symbol. */
	private char symbol;
	
	/** The rng. */
	/* Visibilidad package:
	 * 
	 */
	static Random rng = new Random(1L);
	
	/**
	 * Instantiates a new material.
	 *
	 * @param value the value
	 * @param symbol the symbol
	 */
	//<Constructor>
	Material(double value, char symbol) {
		this.value = value;
		this.symbol = symbol;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	//<Getters>
	public double getValue() {	return this.value;}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() { return this.symbol;}
	
	/**
	 * Checks if is block.
	 *
	 * @return true, if is block
	 */
	public boolean isBlock() {
		if(this.ordinal() >= BEDROCK.ordinal() && this.ordinal() <= OBSIDIAN.ordinal() || this.isLiquid())
			return true;
		return false;
	}
	
	/**
	 * Checks if is liquid.
	 *
	 * @return true, if is liquid
	 */
	public boolean isLiquid() {
		if(this.ordinal() == WATER.ordinal() || this.ordinal() == LAVA.ordinal() || this.ordinal() == AIR.ordinal())
			return true;
		return false;
	}
	
	/**
	 * Checks if is edible.
	 *
	 * @return true, if is edible
	 */
	public boolean isEdible() {
		if(this.ordinal() >= WATER_BUCKET.ordinal() && this.ordinal() <= BEEF.ordinal())
			return true;
		return false;
	}
	
	/**
	 * Checks if is weapon.
	 *
	 * @return true, if is weapon
	 */
	public boolean isWeapon() {
		if(this.ordinal() == WOOD_SWORD.ordinal() || this.ordinal() == IRON_SWORD.ordinal())
			return true;
		return false;
	}
	
	/**
	 * Checks if is tool.
	 *
	 * @return true, if is tool
	 */
	public boolean isTool() {
		if(this.ordinal() == IRON_SHOVEL.ordinal() || this.ordinal() == IRON_PICKAXE.ordinal())
			return true;
		return false;
	}
	
	/**
	 * Gets the random item.
	 *
	 * @param first the first
	 * @param last the last
	 * @return the random item
	 */
	/* Como ya se ha visto, a un método se le añade el 'static'
	 * cuando se desea acceder a él sin la necesidad de haber 
	 * creado previamente un objeto de dicha clase.
	 */
	public static Material getRandomItem(int first, int last) {
		int i = rng.nextInt(last-first+1)+first;
		return values()[i];
	}
	
}