package model.score;

import java.util.SortedSet;
import java.util.TreeSet;

import model.exceptions.score.EmptyRankingException;

/**
 *	@author Oscar Casado Lorenzo
 */

/**
 * The Class Ranking.
 *
 * @param <ScoreType> the generic type
 */
public class Ranking<ScoreType extends Score<?>>{
	
	/** The scores. */
	private SortedSet<ScoreType> scores;
	
	/**
	 * Instantiates a new ranking.
	 */
	public Ranking() {
		this.scores = new TreeSet<ScoreType>();
	}
	
	/**
	 * Adds the score.
	 *
	 * @param points the points
	 */
	public void addScore(ScoreType points) {
		this.scores.add(points);
	}
	
	/**
	 * Gets the sorted ranking.
	 *
	 * @return the sorted ranking
	 */
	public SortedSet<ScoreType> getSortedRanking(){
		return scores;
	}
	
	/**
	 * Gets the winner.
	 *
	 * @return the winner
	 * @throws EmptyRankingException the empty ranking exception
	 */
	public ScoreType getWinner() throws EmptyRankingException {
		if(this.scores.isEmpty())
			throw new EmptyRankingException();
		
		return scores.first();
	}

}
