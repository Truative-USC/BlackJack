package messages;

public class gameIsCreated {
boolean gameIsCreated = false;
public boolean isGameIsCreated() {
	return gameIsCreated;
}
public void setGameIsCreated(boolean gameIsCreated) {
	this.gameIsCreated = gameIsCreated;
}
public gameIsCreated(String created) {
	gameIsCreated = Boolean.valueOf(created);
}
}
