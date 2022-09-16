package model.score;

import model.exceptions.score.ScoreException;
import model.*;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class CollectedItemsScore.
 */
public class CollectedItemsScore extends Score<ItemStack>{

	/**
	 * Instantiates a new collected items score.
	 *
	 * @param playerName the player name
	 */
	public CollectedItemsScore(String playerName) {
		super(playerName);
	}
	
	/**
	 * Compare to.
	 *
	 * @param o the o
	 * @return the int
	 */
	@Override
	public int compareTo(Score<ItemStack> o) {
		if(this.getScoring() > o.getScoring()) 
			return -1;
		
		
		else if(this.getScoring() == o.getScoring())
			return 0;
		
		else return 1;
	}

	/**
	 * Score.
	 *
	 * @param points the points
	 * @throws ScoreException the score exception
	 */
	@Override
	public void score(ItemStack points) throws ScoreException {
		this.score += points.getAmount() * points.getType().getValue();		
	}

	
}
