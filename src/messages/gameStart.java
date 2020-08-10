package messages;

import server.message;

public class gameStart extends message{
	

	private static final long serialVersionUID = 1L;
private boolean startGame = false;
public gameStart(String message, boolean gameStatus) {
	super(message);
	this.setStartGame(gameStatus);
}
public boolean isStartGame() {
	return startGame;
}
public void setStartGame(boolean startGame) {
	this.startGame = startGame;
}
}
