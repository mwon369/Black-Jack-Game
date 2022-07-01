package nz.ac.auckland.se281.a3.bot;

import java.util.Random;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

public class LowRiskStrategy implements BotStrategy {

	/**
	 * Decides whether the bot hits or holds based on the score of its
	 * hand currently. This method will return HOLD if the score of the 
	 * bot's hand is between 17 and 21, otherwise it will return HIT.
	 * 
	 * @param hand hand of the bot
	 * @return the action the bot plays
	 */
	public Action play(Hand hand) {
		int score = hand.getScore();
		
		// check if the bots hand has a non-bust score of 17 or more
		if (score >= 17) {
			return Action.HOLD;
		} else {
			return Action.HIT;
		}
	}
	
	/**
	 * Randomly generates a number between 10 and 50 (inclusive)
	 * which will determine the size of the bet made.
	 * 
	 * @return the bet the bot makes. This bet will be between
	 * 10 and 50 inclusive
	 */
	public int bet() {
		// generate a random number between 10 to 50 inclusive
		Random random = new Random();
		return random.nextInt(41) + 10;
	}

}
