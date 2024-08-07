package donovan.fr.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Hand {
	
	private List<Card> cards = new ArrayList<Card>();
	private List<Integer> value;
	private List<Integer> countOfCardValue = new ArrayList<Integer>();
	private List<Integer> countOfCardColor = new ArrayList<Integer>();
	
	public Hand(List<Card> cards) {
		this.cards = cards;
		this.countOfCardValue = new ArrayList<Integer>();
		this.countOfCardColor = new ArrayList<Integer>();
		
		this.setValue();
	}
	
	public void addCard(Card card) {
		this.cards.add(card);
		int valueCard = card.getValue().getId();
		int colorCard = card.getColor().getId();
		
		while (countOfCardValue.size() <= valueCard) {
			countOfCardValue.add(0);
		}
		countOfCardValue.set(valueCard, countOfCardValue.get(valueCard)+1);
		
		while (countOfCardColor.size() <= colorCard) {
			countOfCardColor.add(0);
		}
		countOfCardColor.set(colorCard, countOfCardColor.get(colorCard)+1);
	}
	
	public List<Integer> getValue() {
		return value;
	}
	
	private void setValue() {
		this.initCountColorAndValue();
		this.value = this.getBestValuePossible();
	}
	
	private void initCountColorAndValue() {
		for (Card currentCard : this.cards) {
			int newValueLong = currentCard.getValue().getId();
			int newColorLong = currentCard.getColor().getId();
			
			while (this.countOfCardValue.size() <= newValueLong) {
				this.countOfCardValue.add(0);
			}
			this.countOfCardValue.set(newValueLong, this.countOfCardValue.get(newValueLong)+1);
			
			while (this.countOfCardColor.size() <= newColorLong) {
				this.countOfCardColor.add(0);
			}
			this.countOfCardColor.set(newColorLong, this.countOfCardColor.get(newColorLong)+1);
		}
	}
	
	private List<Integer> getBestValuePossible() {
		List<Integer> maximumValues = new ArrayList<Integer>();
		int indexHandPossible = 0;
		
		boolean isBestCard = true;
		initMaximumValues(maximumValues, indexHandPossible, isBestCard);
		indexHandPossible++;
		
		boolean isPair = this.countOfCardValue.contains(2);
		initMaximumValues(maximumValues, indexHandPossible, isPair);
		indexHandPossible++;
		
		boolean isTwoPair = this.countOfCardValue.stream().filter(count -> count == 2).count() == 2;
		initMaximumValues(maximumValues, indexHandPossible, isTwoPair);
		indexHandPossible++;
		
		boolean isThreeOfAKind = this.countOfCardValue.contains(3);
		initMaximumValues(maximumValues, indexHandPossible, isThreeOfAKind);
		indexHandPossible++;
		
		boolean isOneTwoThreeFourFive = this.countOfCardValue.equals(Arrays.asList(1,1,1,1,0,0,0,0,0,0,0,0,1));
		boolean isOtherStraight = this.countOfCardValue.stream().reduce(0, (subtotal, element) -> element == 0 ? 0 : subtotal+1) == 5;
		boolean isStraight = isOtherStraight || isOneTwoThreeFourFive;
		initMaximumValues(maximumValues, indexHandPossible, isStraight);
		indexHandPossible++;
		
		boolean isFlush = countOfCardColor.contains(5);
		initMaximumValues(maximumValues, indexHandPossible, isFlush);
		indexHandPossible++;
		
		boolean isFull = this.countOfCardValue.contains(3) && this.countOfCardValue.contains(2);
		initMaximumValues(maximumValues, indexHandPossible, isFull);
		indexHandPossible++;
		
		boolean isFourOfAkind = this.countOfCardValue.contains(4);
		initMaximumValues(maximumValues, indexHandPossible, isFourOfAkind);
		indexHandPossible++;
		
		boolean isStraightFlush = isStraight && isFlush;
		initMaximumValues(maximumValues, indexHandPossible, isStraightFlush);
		indexHandPossible++;
		
		boolean isRoyalFlush = isStraightFlush && countOfCardValue.size() == 13 && !isOneTwoThreeFourFive;
		initMaximumValues(maximumValues, indexHandPossible, isRoyalFlush);
		indexHandPossible++;
		
		if (isStraight) {
			Scanner scanner = new Scanner(System.in);
			System.out.println(countOfCardValue);
			System.out.println(maximumValues);
			System.out.println(isStraight);
			System.out.println(this);
			System.out.println(Arrays.asList(1,1,1,1,0,0,0,0,0,0,0,0,1).stream().reduce(0, (subtotal, element) -> element == 0 ? 0 : subtotal+1));
			System.out.println(Arrays.asList(1,1,0,1,0,0,0,0,0,0,0,0,2).stream().reduce(0, (subtotal, element) -> element == 0 ? 0 : subtotal+1));
			System.out.println(Arrays.asList(1,1,1,1,0,0,0,0,0,0,0,0,1,0).stream().reduce(0, (subtotal, element) -> element == 0 ? 0 : subtotal+1));
			System.out.println(Arrays.asList(1,1,1,1,0,0,0,0,0,0,0,0,1,1).stream().reduce(0, (subtotal, element) -> element == 0 ? 0 : subtotal+1));
			System.out.println(Arrays.asList(1,1,1,1,0,0,0,0,0,0,0,0,1,1,1).stream().reduce(0, (subtotal, element) -> element == 0 ? 0 : subtotal+1));
			System.out.println(Arrays.asList(1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1).stream().reduce(0, (subtotal, element) -> element == 0 ? 0 : subtotal+1));
			scanner.next();
		}
		return maximumValues;
	}
	
	private void initMaximumValues(List<Integer> maximumValues, int rankHand, boolean handIsPossible) {
		if (handIsPossible) {
			maximumValues.clear();
			maximumValues.add(rankHand);
			List<List<Integer>> valueOfCards = new ArrayList<List<Integer>>();
			for (int i = this.countOfCardValue.size()-1; i >= 0 ; i--) {
				int cardValue = i+2;
				int count = this.countOfCardValue.get(i);
				while (valueOfCards.size() < count) {
					valueOfCards.add(new ArrayList<Integer>());
				}
				if (count != 0) {
					valueOfCards.get(count-1).add(cardValue);
				}
			}
			for (int i = valueOfCards.size()-1 ; i >= 0 ; i--) {
				maximumValues.addAll(valueOfCards.get(i));
			}
		}
	}
	
	public void reset() {
		this.cards.clear();
	}
	@Override
	public String toString() {
		return cards.toString();
	}
	
	
	
	
	
}
