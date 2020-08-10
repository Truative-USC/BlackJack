package messages;

import java.io.Serializable;

public class userMessage implements Serializable {
	public static final long serialVersionUID = 1;
	private String username;
	private String message;
	private gameMessage gm = null;
	
	public userMessage(String username) {
		this.username = username;
		this.message = message;
		setGm(new gameMessage(username));
		
	}
	public String getUsername() {
		return username;
	}
	public String getMessage() {
		return message;
	}
	public gameMessage getGm() {
		return gm;
	}
	public void setGm(gameMessage gm) {
		this.gm = gm;
	}
}
