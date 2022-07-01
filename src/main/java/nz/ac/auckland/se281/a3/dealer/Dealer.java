package nz.ac.auckland.se281.a3.dealer;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant;

/**
 * 
 * You should change this class for Task 2
 *
 */
public class Dealer extends Participant {
	
	private DealerStrategy dealerStrategy;
	
	/**
	 * Constructor for the dealer which will be assigned a name
	 * and an instance of the BlackJack game
	 * 
	 * @param name name given to dealer
	 * @param dealerStrategy the default strategy the dealer starts with
	 */
	public Dealer(String name, DealerStrategy dealerStrategy) {
		super(name);
		// dealer constructor will account for the chosen default strategy
		this.dealerStrategy = dealerStrategy;
	}
	
	@Override
	public Action decideAction(Hand hand) {
		return dealerStrategy.hitOrHold(hand);
	}
	
	/**
	 * Sets the strategy of the dealer at runtime based on the state 
	 * of the game currently
	 * 
	 * @param strategy strategy set at runtime
	 */
	public void setStrategy(DealerStrategy strategy) {
		dealerStrategy = strategy;
	}
	
	/**
	 * Returns the current strategy of the dealer that was chosen
	 * at runtime.
	 * 
	 * @return a DealerStrategy object
	 */
	public DealerStrategy getStrategy() {
		return this.dealerStrategy;
	}

}
