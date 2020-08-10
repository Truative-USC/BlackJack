package messages;

import server.message;



//ask bet class sends the message to the user 
//can send a message to the user and ask them for their input
//it is normally initialized in the servers startGame function
public class askBet extends message {

	
	
	private int maxBet;
	private String askMessage;
	private String playerName;
	private String gameName;
	private int bet = 0;
	private int currIndx;
	public askBet(String none, String askMessage) {
		super(none);
		this.setMessage(askMessage);
	}
	
	public String getMessage() {
		return askMessage;
	}
	public void setMessage(String bet) {
		this.askMessage = bet;
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public int getMaxBet() {
		return maxBet;
	}

	public void setMaxBet(int maxBet) {
		this.maxBet = maxBet;
	}

	public int getCurrIndx() {
		return currIndx;
	}

	public void setCurrIndx(int currIndx) {
		this.currIndx = currIndx;
	}


	
}
