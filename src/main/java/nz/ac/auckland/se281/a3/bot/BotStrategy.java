package nz.ac.auckland.se281.a3.bot;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

public interface BotStrategy {
	
	/**
	 * Compulsory method that should be implemented within every bot
	 * strategy class

	 * @param hand hand of the bot
	 * @return the Action that the bots will play based on the currently
	 * selected strategy
	 */
	Action play(Hand hand);
	
	/**
	 * Compulsory method that should be implemented within every bot
	 * strategy class
	 * 
	 * @return the bet that the bots will make based on the currently
	 * selected strategy
	 */
	int bet();

}
