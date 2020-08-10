package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

import messages.askBet;

public class server {

	private Vector<ServerThread> serverThreads;
	private static Vector<game> games = new Vector<game>();
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public boolean checkGame(String game) {
		
		
		if(games.contains(game)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//checks if the user is avaiable or not
	public boolean checkuser(String name, ServerThread thread, String gameName, boolean type) {
		Map<Player, ServerThread> users  = null;
		int index = 0;
		for(int i =0; i < games.size(); i++) {
			if(games.get(i).getGameName().equals(gameName)) {
				users = games.get(i).getGame();
				index = i;
			}
		}
		
		for(Player p: users.keySet()) {
			if(p.getName().equals(name)) {
				return true;
			}
		}
		 
			
			Player temp = new Player(name);
			temp.setFirstUser(type);
			games.get(index).addUser(temp, thread);
			games.get(index).userThreads.add(thread);
		
			return false;
		
		
	}
	//submits a bet to the game with the usertname
	public void bet(String gameName, String userName, int Bet) {
		for(int i = 0; i < games.size();i++) {
			if(games.get(i).getGameName().equals(gameName)) {
				games.get(i).bet(userName, Bet);
			}
		}
	}
	
	
	
	
	
	//broadcast shuffling to all
	//updates the users state
	public void updateUser(String userName, String gameName) {
		game curr = null;

		for(int i = 0; i < games.size();i++) {
			if(games.get(i).getGameName().equals(gameName)) {
				curr = games.get(i);
			}
		}
		curr.updateUser(userName);
	}
	
	//broadcast to the starter of the game
	public void broadcastToStart(message cm, String gameName) {
		ServerThread st = null;
		for(int i =0; i < games.size(); i++) {
			if(games.get(i).getGameName().equals(gameName)) {
				st = games.get(i).userThreads.get(0);
			}
		}
		st.sendMessage(cm);
	}
	//gets the remaining value
	public int getRemaining(String gameName) {
		for(int i =0; i < games.size(); i++) {
			if(games.get(i).getGameName().equals(gameName)) {
				
				int size = games.get(i).getGameSize();
				int playersSize = games.get(i).game.size();
				int remaining = size  - playersSize;
				
				return remaining;
			}
		}
		return 0;
	}
	
	
	
	public boolean gameExists(String gameName) {
		for(int i = 0; i < games.size(); i++) {
			if(games.get(i).getGameName().equals(gameName)) {
				return true;
			}
		}
		return false;
	}
	public void createGame(String Name, Integer players) {
		game newGame = new game(Name, players);
		games.add(newGame);
	}
	public boolean checkGameName(String Name, int size) {
		boolean exists = false;
		for(int i = 0; i < games.size(); i++) {
			if(games.get(i).getGameName().equals(Name)) {
				exists = true;
			}
		}
		
		if(exists == false) {
			games.add(new game(Name, size));
			return false;
		}
		
		return exists;
	}
	public void sendToNext(String gameName, int index) {
		game curr = null;
		for(int i = 0; i < games.size(); i++) {
			if(games.get(i).getGameName().equals(gameName)) {
				curr = games.get(i);
			}
		}
		Vector<ServerThread> currThreads = curr.userThreads;
		
		if(currThreads.size() == index + 1) {
			
			message gm = new message("The dealer is shuffling...");
			this.broadcastToAll(gameName, gm);
		}
		else {
			startGame(gameName, currThreads.get(index+ 1), currThreads.get(index + 1).getUserName());
		}
		
	}
	
	public void startGame(String gameName, ServerThread st, String userName) {
		
		game curr = null;
		for(int i = 0; i < games.size();i++) {
			if(games.get(i).getGameName().equals(gameName)) {
				curr = games.get(i);
			}
		}
			
		Map<Player, ServerThread> game = curr.game;
		//ask players for bets
		for(Map.Entry<Player, ServerThread> entry : game.entrySet()) {
			
			Player player = entry.getKey();
			ServerThread thread = entry.getValue();
			if(player.getName().equals(userName)) {
				int index = curr.getThreadIndx(player.getName());
				String user = player.getName();
				int chipTotal = player.getChips();
				String message = userName + ", it is your turn to make a bet. Your chip total is " +chipTotal;
				askBet aB = new askBet("", message);
				aB.setMaxBet(player.getChips());
				aB.setPlayerName(player.getName());
				aB.setGameName(gameName);
//				this.specificBroad(aB, gameName, st);
				aB.setCurrIndx(index);
				st.sendMessage(aB);
				String mess = "It is " + player.getName() + "'s turn to bet;";
				message m = new message(mess);
				this.broadCastExclude(m, gameName, st);
				
				
				break;
			}
			
			}
 			
	}
	
	public server(int port) {
		
		ServerSocket ss = null;
		serverThreads = new Vector<ServerThread>();
		try {
			ss = new ServerSocket(port);
			while (true) {
				System.out.println("waiting for connection...");
				Socket s = ss.accept();
				System.out.println("connection from " + s.getInetAddress());
				ServerThread st = new ServerThread(s, this);
				serverThreads.add(st);
			}
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		} finally {
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException ioe) {
					System.out.println("ioe closing ss: " + ioe.getMessage());
				}
			}
		}
	}
	
	public void broadcast(message gm, ServerThread st) {
		if(gm != null) {
			for (ServerThread thread : serverThreads) {
				if(st != thread) {
					st.sendMessage(gm);
					
				}
			}
		}
		
	}
	
	
	public void broadcastToAll(String gameName, message gm) {
		if(gm!= null) {
			for(int i  = 0; i < games.size(); i++) {
				if(games.get(i).getGameName().equals(gameName)) {
					games.get(i).broadcastToall(gm);
				}
			}
		}
	}
	
	public static void main(String [] args) {
		System.out.println("Welcome to black Jack Server");
		Scanner scan = new Scanner(System.in);
		int port = 0;
		boolean running = true;
		while(running) {
			System.out.println("Please Enter Port");
			try {
				port = scan.nextInt();
				
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid Port");
				scan.next();
			}
			if(port  > 65535 || port < 1) {
				System.out.println("Invalid Port");
				
			}
			else {
				running = false;
				System.out.println("Sucessfuly started the blackjack server");
			}
		}
		server server = new server(port);
		scan.close();
	}


	public void specificBroad(message m, String gameName, ServerThread st) {
	
		
		
		st.sendMessage(m);
	}
	
	public void broadCastExclude(message m, String gameName, ServerThread st) {
		for(game game: games) {
			if(game.getGameName().equals(gameName)) {
				for(ServerThread thread: game.userThreads) {
					if(thread != st) {

						thread.sendMessage(m);
					}
					
				}
			}
		}
	}
	
	public void broadcastEx(message m, String gameName) {
		game curr = null;
		for(int i = 0; i < games.size(); i++) {
			if(games.get(i).getGameName().equals(gameName)) {
				curr = games.get(i);
			}
		}
		for(int k = 1; k < curr.userThreads.size(); k++) {
			curr.userThreads.get(k).sendMessage(m);
		}
		
		
	}

	
}
