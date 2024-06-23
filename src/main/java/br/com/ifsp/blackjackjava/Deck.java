package br.com.ifsp.blackjackjava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.ifsp.blackjackjava.enums.CardSuitsEnum;
import br.com.ifsp.blackjackjava.enums.CardValuesEnum;

public class Deck { //TODO: Deixar essas classe estatica, não deixar ela ser instanciada
	
	private List<Card> cards = new ArrayList<Card>();

	public Deck() {
		buildDeck();
	}
	
	private void buildDeck() {
		for(CardSuitsEnum cardSuit : CardSuitsEnum.values()) {
			for(CardValuesEnum cardValues : CardValuesEnum.values()) {
				cards.add(new Card(cardSuit.getCardSuitName(), cardValues.getCardValue()));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card deal() {
		return cards.remove(0);
	}
}
