package model;

import java.lang.Math;
import java.util.HashSet;
import java.util.Set;

import model.exceptions.BadLocationException;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class Location.
 */
/*
 * TODO
 * Cambiar los setY() por checks
 * Sobreescribir HashCode y equals
 */
public class Location implements Comparable<Location>{
	
	/** The world. */
	// <DEPENDENCIA CON LA CLASE WORLD>
	private World world;
	
	/** The x. */
	//<Atributos del objeto>
	private double x;
	
	/** The y. */
	private double y;
	
	/** The z. */
	private double z;
	
	/** The upper y value. */
	public static double UPPER_Y_VALUE = 255;
	
	/** The sea level. */
	public static double SEA_LEVEL = 63;
	
	/**
	 * Gets the world.
	 *
	 * @return the world
	 */
	//<Getters>
	public World getWorld() {return this.world;}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public double getX() {return this.x;}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public double getY() {return this.y;}
	
	/**
	 * Gets the z.
	 *
	 * @return the z
	 */
	public double getZ() {return this.z;}
	
	/**
	 * Sets the world.
	 *
	 * @param w the new world
	 */
	//<Setters>
	public void setWorld(World w) {this.world = w;}
	
	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(double x) {this.x = x;}
	
	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(double y) {this.y = y;}
	
	/**
	 * Sets the z.
	 *
	 * @param z the new z
	 */
	public void setZ(double z) {this.z = z;}
	
	/**
	 * Instantiates a new location.
	 *
	 * @param w the w
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	//<Constructor parametrizado>
	public Location (World w, double x, double y, double z) {
		this.world = w;
		setX(x);
		setY(y);
		setZ(z);
	}
	
	/**
	 * Instantiates a new location.
	 *
	 * @param loc the loc
	 */
	//<Constructor de copia>
	public Location(Location loc) {
		this.world = loc.world;
		this.x = loc.x;
		this.y = loc.y;
		this.z = loc.z;
	}
	
	/**
	 * Adds the.
	 *
	 * @param loc the loc
	 * @return the location
	 */
	public Location add(Location loc) {
		if(this.world != loc.world)
			System.err.println("Cannot add Locations of differing worlds.");
		else {
			this.x += loc.x;
			this.y += loc.y;
			this.z += loc.z;
		}
		
		return this;
	}
	
	/**
	 * Distance.
	 *
	 * @param loc the loc
	 * @return the double
	 */
	public double distance(Location loc) {
		if(loc.world == null || this.world == null) {
			System.err.println("Cannot measure distance to a null world");
			return -1.0;
		}
		else if(loc.world != this.world){
			System.err.println("Cannot measure distance between" + this.world.getName()  + 
							   " and " + loc.world.getName());
			return -1.0;
		}
		
		double dx = x - loc.x;
	    double dy = y - loc.y;
	    double dz = z - loc.z;
	    return Math.sqrt(dx*dx + dy*dy + dz*dz);
	}
	
	/**
	 * Length.
	 *
	 * @return the double
	 */
	public double length() {
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	/**
	 * Multiply.
	 *
	 * @param factor the factor
	 * @return the location
	 */
	public Location multiply(double factor) {
		this.x *= factor;
		setY(y * factor);
		this.z *= factor;
		
		return this;
	}
	
	/**
	 * Substract.
	 *
	 * @param loc the loc
	 * @return the location
	 */
	public Location substract(Location loc) {
		if (loc.world != this.world) 
			System.err.println("Cannot substract Locations of differing worlds.");
		else {
			x -= loc.x;
			y -= loc.y;
			z -= loc.z;
		}
		
		return this;
	}
	
	/**
	 * Zero.
	 *
	 * @return the location
	 */
	public Location zero() {
		this.x = this.y = this.z = 0.0;
		return this;
	}
	
	/**
	 * Below.
	 *
	 * @return the location
	 * @throws BadLocationException the bad location exception
	 */
	public Location below() throws BadLocationException{
		if(this.world != null && this.y == 0)
			throw new BadLocationException("No se pudo crear Location con altura negativa.");
		
		Location below = new Location(this);
		below.y -= 1;
		
		return below;
	}
	
	/**
	 * Above.
	 *
	 * @return the location
	 * @throws BadLocationException the bad location exception
	 */
	public Location above() throws BadLocationException{
		if(this.world != null && this.y == UPPER_Y_VALUE)
			throw new BadLocationException("No se pudo crear Location con altura superior a UPPER_Y_VALUE.");
		
		Location above = new Location(this);
		above.y += 1;
		
		return above;
	}
	
	/**
	 * Checks if is free.
	 *
	 * @return true, if is free
	 */
	public boolean isFree() {
	
		try {
			if(this.getWorld().isFree(this) && !this.getWorld().getPlayer().getLocation().equals(this))
				return true;
			return false;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Check.
	 *
	 * @param w the w
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return true, if successful
	 */
	public static boolean check( World w, double x, double y, double z) {
		/*
		 * 
		 *          y(Cielo)
		 *               -z(Norte)
		 * ________|  |_____
		 *         | /
		 *-x(Oeste)|/     x(Este)
		 * ________|__________     
		 *         |  |
		 *  z(Sur) |  |
		 *         |  
		 *         
		 *          -y(BEDROCK)
		 */  
		
		boolean checked = false;
		
		//x [-25,25] y [0,255] z [-25,25]
		if(w.getSize() % 2 != 0) {
			if( x >= (- w.getSize() / 2) && x <= (w.getSize() / 2) &&
				y >= 0 && y <= UPPER_Y_VALUE &&
				z >= (- w.getSize() / 2) && z <= (w.getSize() / 2)) {
				
				checked = true;
			}	
		}
		
		else {
			//x [-24,25] y [0,255] z [-24,25]
			if( x >= ((- w.getSize() / 2) + 1) && x <= (w.getSize() / 2) &&
				y >= 0 && y <= UPPER_Y_VALUE &&
				z >= ((- w.getSize() / 2) + 1) && z <= (w.getSize() / 2)) {
				
				checked = true;
			}
				
		}
		
		return checked;
	}

	/**
	 * Check.
	 *
	 * @param loc the loc
	 * @return true, if successful
	 */
	public static boolean check(Location loc) {
		return check(loc.world, loc.x, loc.y, loc.z);
	}
	
	/**
	 * Gets the neighborhood.
	 *
	 * @return the neighborhood
	 */
	public Set<Location> getNeighborhood(){
		Set<Location> neighborhood = new HashSet<Location>();
					
		for(double z = this.z - 1; z <= this.z + 1; z++) {
			for(double y = this.y + 1; y >= this.y - 1; y--) {
				for(double x = this.x - 1; x <= this.x + 1; x++) {
					
					Location position = new Location(null, x, y, z);

					//Si la posición no pertenece a ningun mundo
					if( this.world == null) {
			
						//No incluimos en el vencindario a la posicion central
						if(!position.equals(this)) 		
							neighborhood.add( position ); //Lo va a desordenar igualmente
					}
						
					//Si la posición pertenece a un mundo, le asignamos el nombre del mundo
					else {
						position.setWorld(this.world);
						
						if( check(position) ) {
							//No incluimos en el vencindario a la posicion central
							if(!position.equals(this)) 
								neighborhood.add( position );
						}
							
					}
						
				}
			}
		}
		return neighborhood;
	}

	
	/**
	 * Compare to.
	 *
	 * @param other the other
	 * @return the int
	 */
	public int compareTo(Location other) {
		if(this.x < other.getX() || (this.x == other.getX() && this.y < other.getY()) || 
		  (this.x == other.getX() && this.y == other.getY() && this.z < other.getZ()) ) 			
			return -1;
		
		
		else if(this.x == other.getX() && this.y == other.getY() && this.z == other.getZ()) 
			return 0;
		
		
		else return 1;
		
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
		result = prime * result + ((world == null) ? 0 : world.hashCode());
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Location other = (Location) obj;
		if (world == null) {
			if (other.world != null)
				return false;
		} else if (!world.equals(other.world))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
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
		String out = "Location{world=";
		if(this.world == null)
			out += "NULL";
		else out += this.world;
		out += ",x=" + x + ",y=" + y + ",z=" + z + "}";
		return out;
	}
	
}
