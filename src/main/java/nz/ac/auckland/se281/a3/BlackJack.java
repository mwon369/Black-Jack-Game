package nz.ac.auckland.se281.a3;

import java.util.ArrayList;
import java.util.List;

import nz.ac.auckland.se281.a3.bot.Bot;
import nz.ac.auckland.se281.a3.bot.BotStrategy;
import nz.ac.auckland.se281.a3.bot.BotStrategyFactory;
import nz.ac.auckland.se281.a3.dealer.Dealer;
import nz.ac.auckland.se281.a3.dealer.DealerStrategy;
import nz.ac.auckland.se281.a3.dealer.HighestBidStrategy;
import nz.ac.auckland.se281.a3.dealer.TopWinnerStrategy;

/**
 * Unless it is specified in the JavaDoc, you cannot change any methods.
 * 
 * You can add new methods and/or new instance fields
 */
public class BlackJack {

	private List<Player> players;
	private Dealer dealer;
	private Deck deck;
	private DealerStrategy[] strategies = new DealerStrategy[2];

	public BlackJack(Deck deck) {
		this.deck = deck;
		players = new ArrayList<>();
		players.add(new Human("Player1")); // add the Human player first.
	}

	/**
	 * Thi constructor is for testing reasons
	 * 
	 * @param cards
	 */
	protected BlackJack(Card... cards) {
		this(new Deck(cards));

	}

	public BlackJack() {
		this(new Deck());
	}

	public List<Player> getPlayers() {
		return players;
	}

	private String getBotStrategy() {
		System.out.println("Choose Bot strategy: random (R) - low risk (LR) - high risk (HR)");
		String result = Main.scanner.next();
		while (!result.equals("R") && !result.equals("LR") && !result.equals("HR") && !result.equals("A")) {
			System.out.println("please type \"R\", \"LR\",  \"HR\"");
			result = Main.scanner.next();
		}
		return result;
	}

	// do not change this method
	public void start() {
		initBots();
		initDealer();
		String res;
		int round = 0;
		do {
			round++;
			for (Participant p : players) {
				p.play(deck, round);
			}
			dealer.play(deck, round);
			printAndUpdateResults(round); // after each game print result and update scoreboard
			System.out.println("Do you want to play again?");
			res = Main.scanner.next();
			while (!res.equals("yes") && !res.equals("no")) {
				System.out.println("please type either \"yes\" or \"no\"");
				res = Main.scanner.next();
			}
		} while (res.equals("yes"));
		printGameStatistics(); // when the user terminates the game print the statistics
	}

	/**
	 * TODO This method initializes the Bots, you should change this method for
	 * Task1
	 */
	protected void initBots() {
		// get the bot strategy chosen by the user and initialize the bots with the chosen strategy
		String botStrategyString = getBotStrategy();
		BotStrategy botStrategy = BotStrategyFactory.chooseStrategy(botStrategyString);
		Bot bot1 = new Bot("Bot1", botStrategy);
		Bot bot2 = new Bot("Bot2", botStrategy);
		players.add(bot1);
		players.add(bot2);
	}

	/**
	 * TODO This method initializes the Dealer, you should change this method for
	 * Task2
	 */
	protected void initDealer() {
		/*
		 * store all dealer strategies in the strategy array and initialize the dealer
		 * with the highest bidder strategy.
		 */
		strategies[0] = new HighestBidStrategy(this);
		strategies[1] = new TopWinnerStrategy(this);
		dealer = new Dealer("Dealer", strategies[0]);
	}

	/**
	 * TODO This method prints and updates the results (wins and losses) you should
	 * change this method for Task 2 and Task 3
	 */
	protected void printAndUpdateResults(int round) {
		checkForWinners();
		
		/*
		 * check what the current strategy is now and decide if it needs to be changed
		 * based on the net-win score of all the players
		 */
		if (dealer.getStrategy() == strategies[0]) {
			for (Player player : players) {
				if (player.getNetWins() >= 2) {
					dealer.setStrategy(strategies[1]);
					break;
				}
			}
		} else {
			boolean topWinner = false;
			for (Player player : players) {
				if (player.getNetWins() >= 2) {
					topWinner = true;
					break;
				}
			}
			if (!topWinner) {
				dealer.setStrategy(strategies[0]);
			}
		}
		
		// loop through all players and print their winnings/losses for the given round
		for (Player player : players) {
			System.out.println("Round " + round + ": " + player.getName() + " " + player.mostRecentResult 
					+ " " + player.getHand().getBet() + " chips");
		}
	}

	/**
	 * TODO This method should print the statistic of the game when it ends
	 */
	protected void printGameStatistics() {
		// loop through all players and print their overall win/loss stats
		for (Player player : players) {
			System.out.println(player.getName() + " won " + player.getWins() + " times and lost "
					+ player.getLosses() + " times");
		}
	}
	
	
	/**
	 * Increments the wins and losses of each player or bot based on the result of each round
	 */
	protected void checkForWinners() {
		/*
		 * checks each player's hand to see if they can win the round and adjusts each players
		 * instance fields regarding wins/losses accordingly
		 */
		for (Player player : players) {
			if (!player.getHand().isBust() && player.getHand().getScore() > dealer.getHand().getScore()) {
				player.increaseWins();
				player.mostRecentResult = "won";
			} else if (!player.getHand().isBust() && dealer.getHand().isBust()) {
				player.increaseWins();
				player.mostRecentResult = "won";
			} else if (player.getHand().isBlackJack() && !dealer.getHand().isBlackJack()) {
				player.increaseWins();
				player.mostRecentResult = "won";
			} else {
				player.increaseLosses();
				player.mostRecentResult = "lost";
			}
		}
	}

}
