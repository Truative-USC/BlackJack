package messages;

import server.message;

public class checkGame extends message{
	
	private static final long serialVersionUID = 1L;
	private String gameName;
	private boolean createGame = false;
	int playerSize;
	boolean checkValid;
	public checkGame(String gameName) {
		super(gameName);
		this.gameName = gameName;
		
	}

	public void setValid(boolean valid) {
		checkValid = valid;
	}
	public boolean getValid() {
		return checkValid;
	}
	public void setPlayerSize(int player) {
		playerSize = player;
	}
	public Integer getplayers() {
		return playerSize;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	public String getName() {
		return gameName;
	}

	public boolean isCreateGame() {
		return createGame;
	}

	public void setCreateGame(boolean createGame) {
		this.createGame = createGame;
	}

	
}
