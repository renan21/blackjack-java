package br.com.ifsp.blackjackjava.enums;

public enum CardValuesEnum {	
	
	ACE("1"),
	TWO("2"),
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"),
	EIGHT("8"),
	NINE("9"),
	TEN("10"),
	JACK("11"),
	QUEEN("12"),
	KING("13");
	
	private final String cardValue;

	CardValuesEnum(String cardValue) {
		this.cardValue = cardValue;
	}

	public String getCardValue() {
		return cardValue;
	}

}
