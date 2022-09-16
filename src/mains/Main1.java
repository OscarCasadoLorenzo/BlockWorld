
package mains;

import java.util.Set;


import model.*;
import model.exceptions.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Main1.
 */
public class Main1 {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	/*
	 * la función main se debe encapsular dentro de una clase como método 
	 * público y estático.
	 */
	public static void main(String[] args) {
		
			System.out.println(Material.WATER.isLiquid());
			try {
				Block b = new SolidBlock(Material.WATER_BUCKET);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
	}
}
