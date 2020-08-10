package messages;

import server.message;

public class sendBetToAll extends message {

	
	private static final long serialVersionUID = 1L;
	private int bet = 0;
	private String name;
	private String gameName;
	
	public int getBet() {
		return bet;
	}
	public void setBet(int bet) {
		this.bet = bet;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public sendBetToAll(String message, int bet, String userName) {
		super(message);
		this.bet = bet;
		this.name = userName;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
}
