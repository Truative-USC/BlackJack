package server;

import java.util.ArrayList;

public class Player {
private Boolean playerBusted;
private Boolean playerStayed;
private Boolean playerWon;
private String name;
private ArrayList<Card> currCards = null;
private int chips;
private int betAmount;
private boolean inGame;
private int playerHand;
boolean FirstUser;
private boolean gotBet;


public boolean isGotBet() {
	return gotBet;
}
public void setGotBet(boolean gotBet) {
	this.gotBet = gotBet;
}
public boolean isFirstUser() {
	return FirstUser;
}
public void setFirstUser(boolean firstUser) {
	FirstUser = firstUser;
}

public Player(String userName) {
	System.out.println("Creates Player" + userName);
	currCards = new ArrayList<Card>();
	setName(userName);
	setChips(500);
	setPlayerBusted(false);
	setInGame(false);
	this.playerHand = 0;
	
}
public void recieveCard(Card c) {
	this.currCards.add(c);
	this.playerHand += c.getValue();
}


//prints out the playes hand to the command line
public String printPlayerHand() { //probbaly not gonna work. come back to this
	String aceValue = "";
	String regValue = "";
	boolean hasAce = false;
	for(int i = 0; i < currCards.size(); i++) {
		if(currCards.get(i).getSuit().contains("ACE")) {
			aceValue += 11;
			regValue += 1;
			hasAce = true;
		}
		else {
			regValue += currCards.get(i).getValue();
			aceValue += currCards.get(i).getValue();

		}
	}
	
	String output = "";
	if(hasAce) {
		output = regValue + " or " + aceValue;
	}
	else {
		output = regValue;
	}
	return output;
}

//hit a player
public void hit(Card c) {
	this.playerHand += c.getValue();
	currCards.add(c);
	if(this.playerHand > 21) {
		setPlayerBusted(true);
	}
	if(this.playerHand == 21) {
		setPlayerWon(true);
	}
}

public void playerWon() {
	setChips(this.chips +=betAmount*2);
}

public void playerLost() {
	setChips(this.chips-betAmount);
}

public Boolean getPlayerBusted() {
	return playerBusted;
}
public void setPlayerBusted(Boolean playerBusted) {
	this.playerBusted = playerBusted;
}
public int getChips() {
	return chips;
}
public void setChips(int chips) {
	this.chips = chips;
}
public int getBetAmount() {
	return betAmount;
}
public void setBetAmount(int betAmount) {
	this.betAmount = betAmount;
}
public ArrayList<Card> getCurrCards() {
	return currCards;
}
public Boolean getPlayerWon() {
	return playerWon;
}

public void setPlayerWon(Boolean playerWon) {
	this.playerWon = playerWon;
}
public void setCurrCards(ArrayList<Card> currCards) {
	this.currCards = currCards;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Boolean getPlayerStayed() {
	return playerStayed;
}
public void setPlayerStayed(Boolean playerStayed) {
	this.playerStayed = playerStayed;
}
public boolean isInGame() {
	return inGame;
}
public void setInGame(boolean inGame) {
	this.inGame = inGame;
}	
}
