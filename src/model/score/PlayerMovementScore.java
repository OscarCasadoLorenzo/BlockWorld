package model.score;

import model.*;
import model.exceptions.score.ScoreException;

/**
 *	@author Oscar Casado Lorenzo
 */
/**
 * The Class PlayerMovementScore.
 */
public class PlayerMovementScore extends Score<Location>{
	
	/** The previous location. */
	private Location previousLocation = null;
	
	/**
	 * Instantiates a new player movement score.
	 *
	 * @param playerName the player name
	 */
	public PlayerMovementScore(String playerName) {
		super(playerName);
	}
	
	/**
	 * Compare to.
	 *
	 * @param o the o
	 * @return the int
	 */
	@Override
	public int compareTo(Score<Location> o) {
		if(this.score > o.score) 
			return 1;
		
		else if(this.score == o.score)
			return 0;
		
		else return -1;
	}

	/**
	 * Score.
	 *
	 * @param points the points
	 */
	@Override
	public void score(Location points){
		if(this.previousLocation != null) {
			this.score += points.distance(previousLocation);
			this.previousLocation = new Location(points);
		}
		else {
			this.previousLocation = new Location(points);
		}
	}

}
