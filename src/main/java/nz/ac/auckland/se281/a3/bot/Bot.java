package nz.ac.auckland.se281.a3.bot;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Player;

/**
 * you should change this class for TASK 1
 */
public class Bot extends Player {

	private BotStrategy botStrategy;

	/**
	 * Constructor for the bot which will be assigned a name
	 * and the user-chosen bot strategy
	 * 
	 * @param name name given to bot
	 * @param botStrategy the chosen strategy the bot plays with
	 */
	public Bot(String name, BotStrategy botStrategy) {
		super(name);
		// bot constructor will account for the strategy the player chooses 
		this.botStrategy = botStrategy;
	}

	@Override
	public Action decideAction(Hand hand) {
		return botStrategy.play(hand);
	}

	@Override
	public int makeABet() {
		return botStrategy.bet();
	}

}
