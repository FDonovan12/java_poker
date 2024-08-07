package donovan.fr.entity;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Deck {
	private List<String> listOfColorSymbol = Arrays.asList("♠","♥","♦","♣");
	private List<String> listOfValueName = Arrays.asList("Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King","ACE");
	private List<Card> deckList = new ArrayList<Card>();
	
	public Deck() {
		for (int i = 0; i < this.listOfColorSymbol.size(); i++) {
			Color color = new Color(i, listOfColorSymbol.get(i));
			for (int j = 0; j < this.listOfValueName.size(); j++) {
				Value value = new Value(j, listOfValueName.get(j));
				Card card = new Card(value, color);
				deckList.add(card);
			}
		}
	}
	
	public Card drawOneCard() {
		return this.deckList.remove(this.deckList.size()-1);
	}
	
	public void drawHand(Player player) {
		List<Card> cards = new ArrayList<Card>(); 
		for (int i = 0; i < 5; i++) {
			cards.add(this.drawOneCard());
		}
		player.setHand(new Hand(cards));
	}
	
	public void shuffleDeckList() {
		List<Card> newDeckList = new ArrayList<Card>();
		while (!this.deckList.isEmpty()) {
			int indexRandom = (int) (Math.random() * this.deckList.size());
			newDeckList.add(this.deckList.remove(indexRandom));
		}
		this.deckList = newDeckList;
	}

	@Override
	public String toString() {
		return this.deckList.toString();
	}
	
	 @Test
	 public void assertThatDeckIsComplete() {
		 Deck deck = new Deck();
		 assertEquals(deck.toString(), "[Two ♠, Three ♠, Four ♠, Five ♠, Six ♠, Seven ♠, Eight ♠, Nine ♠, Ten ♠, Jack ♠, Queen ♠, King ♠, ACE ♠, Two ♥, Three ♥, Four ♥, Five ♥, Six ♥, Seven ♥, Eight ♥, Nine ♥, Ten ♥, Jack ♥, Queen ♥, King ♥, ACE ♥, Two ♦, Three ♦, Four ♦, Five ♦, Six ♦, Seven ♦, Eight ♦, Nine ♦, Ten ♦, Jack ♦, Queen ♦, King ♦, ACE ♦, Two ♣, Three ♣, Four ♣, Five ♣, Six ♣, Seven ♣, Eight ♣, Nine ♣, Ten ♣, Jack ♣, Queen ♣, King ♣, ACE ♣]");
	 }
}
