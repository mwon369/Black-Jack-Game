package nz.ac.auckland.se281.a3.dealer;

import java.util.List;

import nz.ac.auckland.se281.a3.BlackJack;
import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public class TopWinnerStrategy implements DealerStrategy {
	
	private BlackJack game;
	private List<Player> players;
	private int highestNetWinScore;
	private Player playerToBeat;
	
	/**
	 * Constructor for the top winner strategy which takes in the 
	 * BlackJack game instance so that it has access to its information
	 * 
	 * @param game the instance of the BlackJack game being played
	 */
	public TopWinnerStrategy(BlackJack game) {
		// same constructor logic as the highest bidder strategy
		this.game = game;
	}
	
	/**
	 * Checks the net-scores of all players in the Black Jack game and returns the player with
	 * the highest net-score. If multiple players share the highest net-score, then this method
	 * will return the player which appears first on the list. 
	 * 
	 * @return the player object which has the highest net-score.
	 */
	public Player findPlayerToBeat() {
		players = game.getPlayers();
		/* 
		 * set the highest net-win score to the net-win score of the first player in the list 
		 * by default, since the highest net-win score could effectively be any number
		 */
		highestNetWinScore = players.get(0).getNetWins();
		/*
		 * by default we'll also need to assign the target player as the first player in the list, 
		 * since there's the possibility that all other players may have lower net-win scores
		 */
		playerToBeat = players.get(0);
		
		
		/* 
		 * check the net-win scores of all the players to find the player with the highest score.
		 * This player will become the target that the dealer tries to beat
		 */
		for (Player player : players) {
			if (player.getNetWins() > highestNetWinScore) {
				highestNetWinScore = player.getNetWins();
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
	 * @return the action which the dealer will carry out
	 */
	public Action hitOrHold(Hand hand) {
		// same logic as the hitOrHold method in the highest bidder strategy
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
