package server;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.Spring;

public  class game{

Map<Player, ServerThread> game = null;
Vector<ServerThread> userThreads = null;
private String gameName;
private Integer gameSize;

//returns the game map
public Map<Player, ServerThread> getGame() {
	return game;
}
//gets the index of the thread that was used in the last iteratino of the game
public int getThreadIndx(String playerName) {
	for(int i = 0; i < userThreads.size(); i++) {
		if(userThreads.get(i).getUserName().equals(playerName)) {
			return i;
		}
	}
	return 0;
}
//submits the bet to the server 
public void bet(String userName, int Bet) {
	for(Player p: game.keySet()) {
		if(p.getName().equals(userName)) {
			p.setBetAmount(Bet);
		}
	}
}
//returns the first user in the game
public ServerThread returnFirstUser() {
	for(Entry<Player, ServerThread> entry: game.entrySet()) {
		Player p = entry.getKey();
		ServerThread st = entry.getValue();
		if(p.isFirstUser()) {
			return st;
		}
	}
	return null;
}

//updates the users state
public void updateUser(String userName) {
	for(Player p: game.keySet()) {
		if(p.getName().equals(userName)) {
			p.setGotBet(true);
		}
	}
}
//bradcast to every thread in the server
public void broadcastToall(message m) {
	for(int i = 0; i < this.userThreads.size(); i++) {
		userThreads.get(i).sendMessage(m);
	}
}

public void setGame(Map<Player, ServerThread> game) {
	this.game = game;
}
//constructor
public game(String gameName, int players) {
	System.out.println("Player size in Game.java " + players);
	this.gameName = gameName;
	this.gameSize = players;
	this.game = new HashMap<Player, ServerThread>();
	this.userThreads = new Vector<ServerThread>();
	
}
//checks if the user already ixists in thte game
public boolean checkUsear(String name) {
	for(Player p: game.keySet()) {
		if(p.getName().equals(name)) {
			return true;
		}
	}
	return false;
}
//adds a user to the game
public void addUser(Player name, ServerThread thread) {
	this.game.put(name, thread);
}
public String getGameName() {
	return gameName;
}
public void setGameName(String gameName) {
	this.gameName = gameName;
}
public Integer getGameSize() {
	return gameSize;
}
public void setGameSize(Integer gameSize) {
	this.gameSize = gameSize;
}

	
}
	



	

