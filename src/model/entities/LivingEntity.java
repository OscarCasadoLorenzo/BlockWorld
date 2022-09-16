package model.entities;

import model.*;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class LivingEntity.
 */
public abstract class LivingEntity {
	
	/** The health. */
	private double health;
	
	/** The location. */
	protected Location location;
	
	/** The Constant MAX_HEALTH. */
	public static final double MAX_HEALTH = 20;
	
	/**
	 * Instantiates a new living entity.
	 *
	 * @param loc the loc
	 * @param health the health
	 */
	public LivingEntity(Location loc, double health) {
		this.location = loc;
		setHealth(health);
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public abstract char getSymbol();
	
	/**
	 * Gets the health.
	 *
	 * @return the health
	 */
	public double getHealth() {return this.health;}
	
	/**
	 * Sets the health.
	 *
	 * @param health the new health
	 */
	public void setHealth(double health) {	
		if(health > MAX_HEALTH)
			this.health = MAX_HEALTH;
		else this.health = health;
	}
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public Location getLocation() {return new Location(this.location);}
	
	
	/**
	 * Damage.
	 *
	 * @param amount the amount
	 */
	public void damage(double amount) {
		this.health -= amount;
	}
	
	/**
	 * Checks if is dead.
	 *
	 * @return true, if is dead
	 */
	public boolean isDead() {
		if(this.health > 0)
			return false;
		return true;
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
		long temp;
		temp = Double.doubleToLongBits(health);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((location == null) ? 0 : location.hashCode());
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
		LivingEntity other = (LivingEntity) obj;
		if (Double.doubleToLongBits(health) != Double.doubleToLongBits(other.health))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
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
		return "LivingEntity [health=" + health + ", location=" + location + "]";
	}
}
