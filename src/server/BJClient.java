package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import messages.askBet;
import messages.checkGame;
import messages.checkUserName;
import messages.joinCheckGame;
import messages.playerJoined;
import messages.startGame;
import messages.userMessage;
import messages.userValid;
import messages.validGame;

public class BJClient extends Thread {

	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	boolean in = false;
	private boolean gameStarter = false;
	boolean gameCanStart = false;
	boolean repeatedName = true;
	boolean gameDoesNotExist = false;
	boolean valid = false;
	boolean gameStarted = false;
	boolean waitingForPlayers = true;
	private String userName;
	Scanner scan = null;
	public boolean isGameStarted() {
		return gameStarted;
	}

	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	int runCounter = 0;
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isGameDoesNotExist() {
		return gameDoesNotExist;
	}

	public void setGameDoesNotExist(boolean gameDoesNotExist) {
		this.gameDoesNotExist = gameDoesNotExist;
	}

	public boolean isRepeatedName() {
		return repeatedName;
	}

	public void setRepeatedName(boolean repeatedName) {
		this.repeatedName = repeatedName;
	}

	private  int startGame = 3;
	private  int userValid = 0;

	
	public boolean isGamesValid() {
		return gameCanStart;
	}

	public void setGamesValid(boolean gamesValid) {
		this.gameCanStart = gamesValid;
	}

	int playerSize;

	public void setPlayerSize(int size) {
		playerSize = size;
	}

	public Integer getPlayerSize() {
		return playerSize;
	}

	public BJClient(String hostname, int port) {
		try {
			System.out.println("Trying to connect to " + hostname + ":" + port);
			Socket s = new Socket(hostname, port);
			System.out.println("Connected to " + hostname + ":" + port);
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());
			scan = new Scanner(System.in);
			valid = true;
			
			this.start();

		} catch (IOException ioe) {
			System.out.println("ioe in ChatClient constructor: " + ioe.getMessage());
		}
	}

	public void run() {
		
		try {
		
			runCounter += 1;

			while (true) {
				Object obj = ois.readObject();
				//checks if the game name is valid
				if (obj instanceof validGame) {
					validGame g = (validGame)obj;
						startGame = g.isValidGame();	
						if(startGame == 1) {
							gameCanStart = true;
						}
						
				}
				//checks if the username is valid
				//if so set that the name is not repeated to false so u can continue
				if(obj instanceof userValid) {

					userValid uid = (userValid)obj;
					int userValue = uid.getValue();
					if(userValue == 0) {
						this.repeatedName = true;
					}
					else {
						this.repeatedName = false;
					}
					
				}
				//checks to see if the game name is valid on choice 2
				
				if(obj instanceof joinCheckGame) {

					joinCheckGame jcg = (joinCheckGame)obj;
					this.gameDoesNotExist  = jcg.checkGame();
				}
				//player joined is for the game starter to see who joins the game
				if(obj instanceof playerJoined) {
					playerJoined pJ = (playerJoined)obj;

					if(gameStarter == true) {
						String name = pJ.getName();
						int remaining = pJ.getRemaining();
						if(remaining == 0) {
							gameStarted = true;
							waitingForPlayers = false;
							System.out.println(pJ.getName() + " has joined the game");
							System.out.println("All players connected. Let the game commence. Good luck to all");
							
						}
						else {
							System.out.println(pJ.getName() + " has joined the game");
							System.out.println("waiting for " + remaining + " players to join...");
						}
						
					}
					else if(pJ.isStart()) {
						System.out.println("All Players are connected. Let the game commence");
						gameStarted = true;
						waitingForPlayers = false;
					}

				}
				//outputs their total hand and asks what they're going to bet
				if(obj instanceof askBet) { //asking players for bets
					askBet aB = (askBet)obj;
					System.out.println(aB.getMessage());
					int userBet = 0;
						
							 userBet = scan.nextInt();
							if(userBet > aB.getMaxBet()) {
								System.out.println("invalid bet. Please try again");
							}
							else {
								System.out.println("You bet " + userBet + " chips." );
							}

						
						
					aB.setBet(userBet);
					sendMessage(aB);
					
				}
				if(obj instanceof message) {
					message m = (message)obj;
					System.out.println(m.getMessage());
				}
			}
		} catch (IOException ioe) {
			System.out.println("ioe in ChatClient.run(): " + ioe.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("cnfe: " + cnfe.getMessage());
		}
	}

	public void sendToConsole(userMessage um) {
		try {
			oos.writeObject(um.getGm());
			oos.flush();
		} catch (IOException ioe) {
			System.out.print("ioe exception in sendToConsole:" + ioe.getMessage());
		}
	}

	public void sendMessage(message cm) {
		try {
			oos.writeObject(cm);
			oos.flush();
		} catch (IOException ioe) {
			System.out.println("ioe exception in sendMesage" + ioe.getMessage());
		}
	}
	public boolean isGameCanStart() {
		return gameCanStart;
	}

	public void setGameCanStart(boolean gameCanStart) {
		this.gameCanStart = gameCanStart;
	}
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to my BJ Server. Where we go fulllll send. Ready, set, full send!");
		int port = 0;
		Scanner input = new Scanner(System.in);
		BJClient client = null;
		boolean running = false;
		int choice = 0;
		
			do {
				System.out.println("Please enter in the IP Adress ");
				String IP = input.nextLine();
				boolean validIP = false;
				System.out.println("Please enter in Port");
				boolean ranit = true;
				try {
					
				
				Integer portValue = input.nextInt();
				client = new BJClient(IP, portValue);
				ranit = true;
				running = client.isValid();
				}
			 catch (InputMismatchException e) {
				System.out.println("Invalid Port");
				ranit = false;
			}
			}while(!running);
			
			running = false;
			client.in = true;
		
			while(!running) { //menu
				System.out.println("1.) Start Game");
				System.out.println("2.) Join Game");
				try {
					choice = input.nextInt();
					 if(choice == 1 || choice == 2) {
						 running = true;
					 }
					 else {
						 running = false;
					 }
				}
				catch(InputMismatchException e) {
					System.out.println("Invalid input;");
					continue;
				}
				 
			}
		boolean commenceGameLookUp = false;
		Integer playersInt = 0;
		String gameName = null;
		
		if (choice == 1) {
			do {
				
			
			boolean beganGame = true;
			running = false;
			int players = 0;
			while (beganGame) {
				
				System.out.println("Enter amount of players(1-3)");
				input.nextLine();
				players = input.nextInt();
				 
				if (players < 1 || players > 3) {
					System.out.println("Too many players. Please try again");
				} else {
					running = true;
					beganGame = false;
				}
			}
			String validGame = null;
			input.nextLine();

				while (!client.isGamesValid()) {
					
					System.out.println("Enter Game name: ");
					gameName = input.nextLine();

					if (gameName.isEmpty()) {
						System.out.println("Invalid game Name. Please try again");
						continue;
					} else {
						client.setPlayerSize(players);
						checkGame cg = new checkGame(gameName);
						cg.setPlayerSize(players);
						client.setGamesValid(true);
						client.sendMessage(cg);
						
						try {
							TimeUnit.MILLISECONDS.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						if (client.startGame == 1) {
							
							client.setGamesValid(true);
							validGame = gameName;
							
							
						} else {
							System.out.println("Invalid. Game name is already in use. Please try again.");
							System.out.println(client.runCounter);
							gameName = "";
							client.setGamesValid(false);
							
						}

					}

				}
			
				boolean waitingForUser = true;
				while(client.isRepeatedName()) {
					System.out.println("Please enter student Name");
					String userName = input.nextLine();
					checkUserName checkUser = new checkUserName("", userName,validGame, true);
					client.sendMessage(checkUser);
					
					try {
						TimeUnit.MILLISECONDS.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(client.isRepeatedName()) {
						System.out.println("User name already in use. Please try again");
						
					}
					else {
						client.setGameStarter(true);
						client.setUserName(userName);
						int rem = players - 1;
						System.out.println("Waiting for " +  rem + " players to join...");
						if(players == 1) {
							client.waitingForPlayers = false;
							startGame start = new startGame("",gameName, client.userName);
							client.sendMessage(start);
						}
						break;
					}
				}

				while(client.waitingForPlayers == true) {
					System.out.print("");
				}

		}while(!running);
		}
		else if(choice == 2) {
			String joinGameName = "";
			input.nextLine();
			while(client.isGameDoesNotExist() == false) {
				System.out.println("Please enter Game name: ");
				 joinGameName = input.nextLine();
				joinCheckGame cg = new joinCheckGame("",joinGameName);
				client.sendMessage(cg);
				
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}


				if(client.isGameDoesNotExist() == false) {
					System.out.println(joinGameName + " does not exist. Please try again");
				}
				else {
					break;
				}
			}
			
			while(client.isRepeatedName() == true) {
				System.out.println("Please Enter a userName");
				String userNameInput = input.nextLine();
				
				checkUserName checkuserName = new checkUserName("", userNameInput, joinGameName, false);
				client.sendMessage(checkuserName);
				
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				
				if(client.isRepeatedName()) {
					System.out.print("User name is already in use inside this game");
				}
				else {
					playerJoined player = new playerJoined("", userNameInput, joinGameName); 
					client.sendMessage(player);
					client.setUserName(userNameInput);
					break;
				}
				
			}
			while(client.isGameStarted() == false) {
			
				System.out.println("The Game will start Shortly. Waiting for other players to join...");
				while(client.waitingForPlayers) {
					System.out.print("");
				}
				
			}
			
		}
		
		startGame start = new startGame("",gameName, client.userName);
	
		if(client.gameStarter && client.gameStarted) {

			client.sendMessage(start);

		}
		

	}

	public boolean isGameStarter() {
		return gameStarter;
	}

	public void setGameStarter(boolean gameStarter) {
		this.gameStarter = gameStarter;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}