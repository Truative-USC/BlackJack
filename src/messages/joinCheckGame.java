package messages;

import server.message;

public class joinCheckGame extends message {
	
	private static final long serialVersionUID = 1L;
	private String gameName;
	boolean checkGame = false;
	public joinCheckGame(String nothing, String gameName) {
		super(nothing);
		this.setGameName(gameName);
		
	}
	public boolean checkGame() {
		return checkGame;
	}
	public void setGameCheck(boolean value) {
		this.checkGame = value;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	
}
