package nz.ac.auckland.se281.a3.bot;

import java.util.Random;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

public class RandomStrategy implements BotStrategy {
	
	/**
	 * Randomly decides whether the bot hits or holds based
	 * on a random number generator.
	 * 
	 * @param hand hand of the bot
	 * @return the action the bot plays
	 */
	public Action play(Hand hand) {
		Random random = new Random();
		
		// generate a random number and return and action based on its value
		float randomNumber = random.nextFloat();
		
		if (randomNumber < 0.50f) {
			return Action.HIT;
		} else {
			return Action.HOLD;
		}
	}
	
	/**
	 * Randomly generates a number between 1 and 100 (inclusive)
	 * which will determine the size of the bet made.
	 * 
	 * @return the bet the bot makes. This bet will be between
	 * 1 and 100 inclusive
	 */
	public int bet() {
		// generate a random number between 1 to 100 inclusive
		Random random = new Random();
		return random.nextInt(100) + 1;
	}

}
