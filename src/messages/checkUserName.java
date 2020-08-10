package messages;

import server.message;

public class checkUserName extends message{


	private static final long serialVersionUID = 1L;
	private String name;
	private String game;
	private boolean firstUser;
	public checkUserName(String message, String name, String game, boolean firstUser) {
		super(message);
		this.setGame(game);
		this.setName(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String userName) {
		this.name = userName;
	}
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}
	public boolean isFirstUser() {
		return firstUser;
	}
	public void setFirstUser(boolean firstUser) {
		this.firstUser = firstUser;
	}

}
