package model.score;


import model.exceptions.score.*;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class Score.
 *
 * @param <T> the generic type
 */
public abstract class Score<T> implements Comparable<Score<T>>{

	/** The player name. */
	private String playerName;
	
	/** The score. */
	protected double score;
	
	/**
	 * Instantiates a new score.
	 *
	 * @param playerName the player name
	 */
	public Score(String playerName) {
		this.playerName = playerName;
		this.score = 0;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String out = this.playerName + ":" + this.score;
		return out;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.playerName;
	}
	
	/**
	 * Gets the scoring.
	 *
	 * @return the scoring
	 */
	public double getScoring() {
		return this.score;
	}
	
	/**
	 * Score.
	 *
	 * @param points the points
	 * @throws ScoreException the score exception
	 */
	public abstract void score(T points) throws ScoreException;
}
