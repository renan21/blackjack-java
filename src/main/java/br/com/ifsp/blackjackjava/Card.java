package br.com.ifsp.blackjackjava;

public class Card {
	
	private String cardSuit;
	private String cardValue;
	
	public Card(String cardSuit, String cardValue) {
		super();
		this.cardSuit = cardSuit;
		this.cardValue = cardValue;
	}
		
	public String getCardSuit() {
		return cardSuit;
	}

	public String getCardValue() {
		return cardValue;
	}
	
	public String getCardName() {
		return cardSuit + "-" + cardValue;
	}
	
}