package server;

import java.util.Collections;
import java.util.List;

public class Deck {
	
	 private List<Card> deck;
	 public Deck() {
		 deck.add(new Card("ACE OF SPADES", 11));
		 deck.add(new Card("TWO OF SPADES", 2));
		 deck.add(new Card("THREE OF SPADES", 3));
		 deck.add(new Card("FOUR OF SPADES", 4));
		 deck.add(new Card("FIVE OF SPADES", 5));
		 deck.add(new Card("SIX OF SPADES", 6));
		 deck.add(new Card("SEVEN OF SPADES",7));
		 deck.add(new Card("EIGHT OF SPADES", 8));
		 deck.add(new Card("NINE OF SPADES", 9));
		 deck.add(new Card("TEN OF SPADES", 10));
		 deck.add(new Card("JACK OF SPADES", 10));
		 deck.add(new Card("QUEEN OF SPADES", 10));
		 deck.add(new Card("KING OF SPADES", 10));
		 deck.add(new Card("ACE OF HEARTS", 11));
		 deck.add(new Card("TWO OF HEARTS", 2));
		 deck.add(new Card("THREE OF HEARTS", 3));
		 deck.add(new Card("FOUR OF HEARTS", 4));
		 deck.add(new Card("FIVE OF HEARTS", 5));
		 deck.add(new Card("SIX OF HEARTS", 6));
		 deck.add(new Card("SEVEN OF HEARTS",7));
		 deck.add(new Card("EIGHT OF HEARTS", 8));
		 deck.add(new Card("NINE OF HEARTS", 9));
		 deck.add(new Card("TEN OF HEARTS", 10));
		 deck.add(new Card("JACK OF HEARTS", 10));
		 deck.add(new Card("QUEEN OF HEARTS", 10));
		 deck.add(new Card("KING OF HEARTS", 10));
		 deck.add(new Card("ACE OF CLUBS", 11));
		 deck.add(new Card("TWO OF CLUBS", 2));
		 deck.add(new Card("THREE OF CLUBS", 3));
		 deck.add(new Card("FOUR OF CLUBS", 4));
		 deck.add(new Card("FIVE OF CLUBS", 5));
		 deck.add(new Card("SIX OF CLUBS", 6));
		 deck.add(new Card("SEVEN OF CLUBS",7));
		 deck.add(new Card("EIGHT OF CLUBS", 8));
		 deck.add(new Card("NINE OF CLUBS", 9));
		 deck.add(new Card("TEN OF CLUBS", 10));
		 deck.add(new Card("JACK OF CLUBS", 10));
		 deck.add(new Card("QUEEN OF CLUBS", 10));
		 deck.add(new Card("KING OF CLUBS", 10));
		 deck.add(new Card("ACE OF DIAMONDS", 11));
		 deck.add(new Card("TWO OF DIAMONDS", 2));
		 deck.add(new Card("THREE OF DIAMONDS", 3));
		 deck.add(new Card("FOUR OF DIAMONDS", 4));
		 deck.add(new Card("FIVE OF DIAMONDS", 5));
		 deck.add(new Card("SIX OF DIAMONDS", 6));
		 deck.add(new Card("SEVEN OF DIAMONDS",7));
		 deck.add(new Card("EIGHT OF DIAMONDS", 8));
		 deck.add(new Card("NINE OF DIAMONDS", 9));
		 deck.add(new Card("TEN OF DIAMONDS", 10));
		 deck.add(new Card("JACK OF DIAMONDS", 10));
		 deck.add(new Card("QUEEN OF DIAMONDS", 10));
		 deck.add(new Card("KING OF DIAMONDS", 10)); 
		 Collections.shuffle(deck);
	 }
	 
	public List<Card> getDeck() {
		return deck;
	}
}
