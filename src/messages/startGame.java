package messages;

import server.message;

public class startGame extends message {

	
	private static final long serialVersionUID = 1L;
	private String gameName = "";
	private String userName = "";
	public startGame(String none, String gameName, String userName) {
		
		super(none);
		this.setGameName(gameName);
		this.setUserName(userName);
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
