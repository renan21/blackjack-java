package br.com.ifsp.blackjackjava.rules;

import java.util.ArrayList;
import java.util.List;

import br.com.ifsp.blackjackjava.enums.PlayerStatusEnum;

public class Player {

	private PlayerStatusEnum playerStatus;
	private List<Card> cards = new ArrayList<Card>();
	
	Player(){
		playerStatus = PlayerStatusEnum.PLAYING;
	}
	
	public void receiveCard(Card card) {
		cards.add(card);
	}
	
	public List<Card> getCards(){
		return cards;
	}
	
	public PlayerStatusEnum getPlayerStatus() {
		return playerStatus;
	}
	
	public void setPlayerStatus(PlayerStatusEnum playerStatus) {
		this.playerStatus = playerStatus;		
	}
	
	public int getScore() {
		int score = 0;		
		int scoreWithAce = 0;
		
		for (Card card : cards) {
			int cardValue = Integer.parseInt(card.getCardValue());
			if(cardValue > 10) {
				cardValue = 10;
			}
			if(cardValue == 1) {
				cardValue = 11;
			}
			scoreWithAce = scoreWithAce + cardValue;
		}
		
		for(Card card : cards) {
			int cardValue = Integer.parseInt(card.getCardValue());
			if(cardValue > 10) {
				cardValue = 10;
			}
			
			score = score + cardValue;
		}
		
		return scoreWithAce <= 21 ? scoreWithAce : score;
	}
		
}

