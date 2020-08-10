package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import messages.askBet;
import messages.checkGame;
import messages.checkUserName;
import messages.joinCheckGame;
import messages.playerJoined;
import messages.startGame;
import messages.userValid;
import messages.validGame;

public class ServerThread extends Thread {

	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private server server;
	private String userName;
	Scanner scan = null;
	public ServerThread(Socket s, server cr) {
		try {
			
			scan = new Scanner(System.in);
			this.server = cr;
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			this.start();
			
		} catch (IOException ioe) {
			System.out.println("ioe in ServerThread constructor: " + ioe.getMessage());
		}
	}

	public void sendMessage(message gm) {

		try {
			oos.writeObject(gm);
			oos.flush();
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		}
	}
	
	public void run() {
		try {
			
			
			
			while(true) {
//				message cm = (message)ois.readObject();
				Object obj = ois.readObject();
				
				if(obj instanceof checkGame) {
					int startGame = 0;
					checkGame g = (checkGame)obj;
					boolean exists = server.checkGameName(g.getName(), g.getplayers());
					if(exists == false) {
						startGame = 1;
						System.out.println("Sucessfully Started game: " + g.getName());
					}
					System.out.println("int value: " + startGame);
					validGame vG = new validGame(g.getName(), startGame);
					sendMessage(vG);

					
					
//					boolean exists = server.checkGame(cm.getMessage());
//					if(!exists) {
//
//					}
//				} else if (obj instanceof gotMove) {
//					if (move ==1 ) {
//						
//					}
				} if(obj instanceof checkUserName) {
					checkUserName cg = (checkUserName)obj;
					boolean playerUsed = server.checkuser(cg.getName(),this, cg.getGame(), cg.isFirstUser());
					int userValid = 0;
					if(playerUsed) {
						userValid = 0;
					}
					else {
						this.setUserName(cg.getName());

						userValid = 1;
					}
					userValid uid = new userValid("", userValid);
					sendMessage(uid);
					
					
					
				}
				if(obj instanceof joinCheckGame) {
					joinCheckGame jcg = (joinCheckGame)obj;
					boolean gameExists = server.gameExists(jcg.getGameName());
					jcg.setGameCheck(gameExists);
					sendMessage(jcg);
					
				}
				if(obj instanceof playerJoined) {
					playerJoined pJ = (playerJoined)obj;
					int remaining = server.getRemaining(pJ.getGameName());
					pJ.setRemaining(remaining);
					server.broadcastToStart(pJ, pJ.getGameName());
					
					if(remaining == 0) {
						pJ.setStart(true);
						server.broadcastEx(pJ, pJ.getGameName());
					}
					
				}
				if(obj instanceof startGame) {
					startGame sG = (startGame)obj;
					server.startGame(sG.getGameName(), this, sG.getUserName());
				}
				if(obj instanceof askBet) {
					askBet aB = (askBet)obj;
					
						message m = (message)obj;
						m.setMessage(aB.getPlayerName() + " bet " + aB.getBet() + "chips." );
						server.broadCastExclude(m, aB.getGameName(), this);
						
						server.sendToNext(aB.getGameName(), aB.getCurrIndx());
					
					}
				
				
			
				
			}
		} catch (IOException ioe) {
			System.out.println("ioe in ServerThread.run(): " + ioe.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("cnfe: " + cnfe.getMessage());
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}