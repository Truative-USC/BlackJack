package messages;

import server.message;

public class playerJoined extends message {

	private static final long serialVersionUID = 1L;


public playerJoined(String message, String name, String gameName) {
		super(message);
		this.name = name;
		this.gameName = gameName;
		
	}
private String name;
private int remaining;
private String gameName;
private boolean start;

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getRemaining() {
	return remaining;
}
public void setRemaining(int remaining) {
	this.remaining = remaining;
}
public String getGameName() {
	return gameName;
}
public void setGameName(String gameName) {
	this.gameName = gameName;
}
public boolean isStart() {
	return start;
}
public void setStart(boolean start) {
	this.start = start;
}

}
