package nz.ac.auckland.se281.a3.dealer;

import java.util.List;

import nz.ac.auckland.se281.a3.BlackJack;
import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public class HighestBidStrategy implements DealerStrategy {
	
	private BlackJack game;
	private List<Player> players;
	private int highestBid;
	private Player playerToBeat;
	
	/**
	 * Constructor for the top winner strategy which takes in the 
	 * BlackJack game instance so that it has access to its information
	 * 
	 * @param game the instance of the BlackJack game being played
	 */
	public HighestBidStrategy(BlackJack game) {
		// pass in the game  to the constructor so we have access to player information
		this.game = game;
	}
	
	/**
	 * Checks the bids made by all players in the Black Jack game and returns the player with
	 * the has the highest bid. If multiple players share the highest bid, then this method
	 * will return the player which appears first on the list. 
	 * 
	 * @return the player object which has the highest bid.
	 */
	public Player findPlayerToBeat() {
		players = game.getPlayers();
		highestBid = 0; // default value = 0 since all players must bid at least 1 chip
		
		/* 
		 * check the bids of all the players to find the player with the highest bid.
		 * This player will become the target that the dealer tries to beat
		 */
		for (Player player : players) {
			if (player.getHand().getBet() > highestBid) {
				highestBid = player.getHand().getBet();
				playerToBeat = player;
			}
		}
		
		return playerToBeat;
	}
	
	/**
	 * Determines the action of the dealer based on the scores of the target player's hand. 
	 * This method will return HIT if the dealer's hand is currently losing to the target
	 * player's hand, otherwise it will return HOLD.
	 * 
	 * @param hand hand of the dealer
	 * @return the action which the dealer will do
	 */
	public Action hitOrHold(Hand hand) {
		/*
		 * check for whether the dealer should hit or hold, based on the dealers hand
		 * and the hand of the target player
		 */
		if (hand.getScore() >= 17 && findPlayerToBeat().getHand().isBlackJack()) {
			return Action.HOLD;
		}
		if (hand.getScore() < findPlayerToBeat().getHand().getScore()
				&& !findPlayerToBeat().getHand().isBust()) {
			return Action.HIT;
		} else {
			return Action.HOLD;
		}
	}

}
