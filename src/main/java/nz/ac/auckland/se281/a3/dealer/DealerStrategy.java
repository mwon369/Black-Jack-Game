package nz.ac.auckland.se281.a3.dealer;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public interface DealerStrategy {
	
	/**
	 * Compulsory method that needs to be implemented for
	 * both the dealer strategies
	 * 
	 * @return the target player the dealer wants to beat
	 * based on the currently selected strategy
	 */
	Player findPlayerToBeat();
	
	/**
	 * Compulsory method that needs to be implemented for
	 * both the dealer strategies
	 * 
	 * @param hand hand of the dealer
	 * @return the action the dealer carries out, based on 
	 * the state of their hand in comparison to the target
	 * player's hand
	 */
	Action hitOrHold(Hand hand);

}
