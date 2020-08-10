package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Dealer {
	private int DealerHand = 0;
	private List<Card> deck = null;
	private Boolean bust = false;
	private Boolean dealerWin = false;
	public Dealer() {
		this.deck = new Deck().getDeck();
		Collections.shuffle(this.deck);
	}
	public int getDealerHand() {
		return DealerHand;
	}
	public void hit(Card c) {
		
		deck.add(c);
		DealerHand += c.getValue();
		
		if(DealerHand > 21 ) {
			bust = true;
		}
		if(DealerHand== 21) {
			dealerWin = true;
		}
		
	}
	public void dealerBusted() {
		bust = false;
	}
	
	public void dealerWin() {
		dealerWin = true;
	}
	public boolean dealerHasLost() {
		return bust;
	}
	public boolean dealerHasWon() {
		return dealerWin;
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	public Card getHit() {
		Card temp = deck.remove(0);
		return temp;
		}
}
