package messages;

import java.io.Serializable;

import server.message;

public class validGame extends message {

	private static final long serialVersionUID = 1L;
int validGame;
public validGame(String gameName, int value) {
	super(gameName);
	
	validGame = value;
}
public Integer isValidGame() {
	return validGame;
}
public void setValidGame(Integer validGame) {
	this.validGame = validGame;
}

	
	
}
