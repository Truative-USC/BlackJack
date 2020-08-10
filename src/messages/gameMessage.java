package messages;

import java.util.Scanner;

import server.Card;
import server.Dealer;
import server.Player;

public class gameMessage {

	private Player p1 = null;
	boolean waiting = false;
	private static Dealer d = null;
	Scanner scan = null;
	
	public gameMessage(String userName) {
		System.out.print("creates game Message");
		p1 = new Player(userName);
		scan = new Scanner(System.in);
	}
	
	public void displayWaiting(int waitingAmount) {
		System.out.println("Waiting for " + waitingAmount + "other players to join...");
	}
	
	public void displayAboutToStart() {
		System.out.println();
	}
	public void waitingForOtherPlayers() {
		System.out.print("The game will start shortly. Waiting for other players to join...");
	}
	public void playerJoinedGame(Player p) {
		System.out.println(p.getName() + " joined the game");
	}
	public void beginGame() {
		System.out.print("Let the game commence. Good luck to all players!");
	}
	public void dealerIsShuffling() {
		System.out.println("Dealer is shuffling cards...");
		d.shuffle();
	}
//	public void playerStayed(Player p) {
//		System.out.print(s);
//	}
	public void bet() {
		System.out.println(p1.getName() +" its your turn to bet. Your chip total is" + p1.getChips());
		int bet = scan.nextInt();
		if(bet > p1.getChips()) {
			System.out.println("Invalid bed. Please try again");
		}
		bet = scan.nextInt();
		p1.setBetAmount(bet);
		System.out.println("You bet " + bet + " chips.");
	}
	public void printOffPlayerBet(Player p) {
		System.out.println("Its "+p.getName()+" turn to make a bet;");
		System.out.println(p.getName() + " bet " + p.getBetAmount() + " chips.");
	}
	public void playerBetTurn(Player p) {
		System.out.println("It is "+p.getName() + " to make a bet.");
	}
	public void myTurnToBet() {
		System.out.print(p1.getName() + ", it is your turn to make a bet. Your chip total is " + p1.getChips());
	}

	public void hitPlayer() {
		p1.hit(d.getHit());
	}
	public void watchPlayerHit(Player p) {
		System.out.println(p.getName() + " hit. They were dealt the " + p.getCurrCards().get(p.getCurrCards().size()-1).getSuit());
		
	}
	public boolean playerWon() {
		return p1.getPlayerWon();
	}
	public boolean playerStayed() {
		return p1.getPlayerStayed();
	}
	public boolean playerBusted() {
		return p1.getPlayerBusted();
	}
	public void printDealer() {
		
	}
	public void displayHit() {
		System.out.println("It is your turn to add cards to your hand");
		System.out.println("Enter Either '1' or 'stay' to stay. Enter either '2' or 'hit' to hit");
		String userInput = scan.next();
		
		
	}
	
	public void giveCard(Card c) {
		p1.getCurrCards().add(c);
	}
	public void dealToPlayer() {
		p1.recieveCard(d.getHit());
		p1.recieveCard(d.getHit());
	}
	public void printPlayerData() {
		
		
		System.out.println("Player: " + p1.getName());
		System.out.print("Cards: ");
		String cards = "";
		String status = p1.printPlayerHand();
		for(int i = 0; i < p1.getCurrCards().size(); i++ ) {
			cards +=  " | " + p1.getCurrCards().get(i).getSuit();
		}
		if(p1.getPlayerWon()) {
			status += " - blackjack";
		}
		if(p1.getPlayerBusted()) {
			status += " - busted";
		}
		System.out.print(status);
		System.out.println(cards);
		System.out.println("Chips total: " + p1.getChips() + " | Bet Amount: " + p1.getBetAmount());
		
	}
}
