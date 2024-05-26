package br.com.ifsp.blackjackjava.enums;

public enum CardSuitsEnum {
	
    SPADES("spades", "♠"),
    DIAMONDS("diamonds", "♦"),
    HEARTS("hearts", "♥"),
    CLUBS("clubs", "♣");

	private final String cardSuit;
	private final String cardSuitName;

	CardSuitsEnum(String cardSuitName, String cardSuit) {
		this.cardSuit = cardSuit;
		this.cardSuitName = cardSuitName;
	}

	public String getCardSuit() {
		return cardSuit;
	}

	public String getCardSuitName() {
		return cardSuitName;
	}

}
