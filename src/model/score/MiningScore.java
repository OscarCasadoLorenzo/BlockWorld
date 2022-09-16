package model.score;

import model.*;
import model.exceptions.score.ScoreException;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class MiningScore.
 */
public class MiningScore extends Score<Block>{

	/**
	 * Instantiates a new mining score.
	 *
	 * @param playerName the player name
	 */
	public MiningScore(String playerName) {
		super(playerName);
	}
	
	/**
	 * Compare to.
	 *
	 * @param o the o
	 * @return the int
	 */
	@Override
	public int compareTo(Score<Block> o) {
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
	public void score(Block points) throws ScoreException {
		this.score += points.getType().getValue();		
	}

}
