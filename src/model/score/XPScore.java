package model.score;

import java.util.ArrayList;
import java.util.List;

import model.entities.Player;
import model.exceptions.score.ScoreException;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class XPScore.
 */
public class XPScore extends Score<Player>{

	/** The scores. */
	private List<Score<?>> scores;
	
	/** The player. */
	private Player player;
	
	/**
	 * Instantiates a new XP score.
	 *
	 * @param player the player
	 */
	public XPScore(Player player) {
		super(player.getName());
		this.scores = new ArrayList<Score<?>>();
		this.player = player;
	}
	
	/**
	 * Compare to.
	 *
	 * @param arg0 the arg 0
	 * @return the int
	 */
	@Override
	public int compareTo(Score<Player> arg0) {
		if(this.getScoring() < arg0.getScoring())
			return 1;
		else if(this.getScoring() == arg0.getScoring())
			return 0;
		else return -1;
	}

	/**
	 * Adds the score.
	 *
	 * @param score the score
	 */
	public void addScore(Score<?> score) {
		this.scores.add(score);
		this.score = this.getScoring();
	}
	
	/**
	 * Score.
	 *
	 * @param points the points
	 * @throws ScoreException the score exception
	 */
	@Override
	public void score(Player points) throws ScoreException {
		if(!this.player.equals(points))
			throw new ScoreException("No corresponde al mismo jugador.");
		
		double total = 0;
		for (Score<?> score : this.scores) {
			total += score.getScoring();
		}
		
		//Hacemos la media
		if(scores.size() != 0)
			total /= scores.size();
		
		this.score = total + player.getHealth() + player.getFoodLevel();
	}
	
	/**
	 * Gets the scoring.
	 *
	 * @return the scoring
	 */
	public double getScoring() {
		score(this.player);
		return score;
	}

}
