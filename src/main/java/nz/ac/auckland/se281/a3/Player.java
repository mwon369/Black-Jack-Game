package nz.ac.auckland.se281.a3;

/**
 * 
 * You can (and should) add new fields and/or methods
 *
 */
public abstract class Player extends Participant {
	
	/*
	 * instance fields to keep track of a players overall game statistics
	 * and their most recent game result, i.e., win or loss.
	 */
	protected int wins = 0;
	protected int losses = 0;
	protected String mostRecentResult;

	public Player(String name) {
		super(name);
	}

	public abstract int makeABet();
	
	/**
	 * Increments the total wins of the player if the player wins the round
	 */
	public void increaseWins() {
		wins++;
	}
	
	/**
	 * Increments the total losses of the player if the player loses the round
	 */
	public void increaseLosses() {
		losses++;
	}
	
	/**
	 * Calculates the overall net-win score of the player by computing
	 * wins minus losses
	 * @return the net-win score of the player
	 */
	public int getNetWins() {
		return wins - losses;
	}
	
	/**
	 * Gets the wins of the player that will make printing player statistics
	 * easier
	 * @return the number of wins the player has
	 */
	public int getWins() {
		return this.wins;
	}
	
	/**
	 * Gets the losses of the player that will make printing player statistics
	 * easier
	 * @return the number of losses the player has
	 */
	public int getLosses() {
		return this.losses;
	}

}
