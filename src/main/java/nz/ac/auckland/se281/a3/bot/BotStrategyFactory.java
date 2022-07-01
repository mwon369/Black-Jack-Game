package nz.ac.auckland.se281.a3.bot;

public class BotStrategyFactory {
	
	/**
	 * This method implements the factory design pattern that will be used
	 * to abstract the logic behind a user choosing a bot strategy
	 * 
	 * @param botStrategyString a string reading either "R", "LR" or "HR"
	 * @return the BotStrategy selected by the user based on their string input
	 */
	public static BotStrategy chooseStrategy(String botStrategyString) {
		
		BotStrategy strategy;
		
		/* 
		 * assign and return the correct bot strategy based on user input
		 * R will assign the bots to the random strategy
		 * LR will assign the bots to the low risk strategy
		 * HR will assign the bots to the high risk strategy
		 */
		switch (botStrategyString) {
		case "R":
			strategy = new RandomStrategy();
			break;
		case "LR":
			strategy = new LowRiskStrategy();
			break;
		case "HR":
			strategy = new HighRiskStrategy();
			break;
		default:
			strategy = null;
			break;
		}
		
		return strategy;
	}

}
