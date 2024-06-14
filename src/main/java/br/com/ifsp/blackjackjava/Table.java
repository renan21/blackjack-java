package br.com.ifsp.blackjackjava;

import java.util.List;

import br.com.ifsp.blackjackjava.enums.GameStatusEnum;

public class Table {
	
	private Deck deck;
	private Player player;		
	private Player com;
	private GameStatusEnum status;	
	
	public Table() {
		this.status = GameStatusEnum.PLAYING;
		this.deck = new Deck();
		deck.shuffle();
				
		this.player = new Player();
		this.com = new Player();
				
		for(int i = 0; i < 2; i++) {
			player.receiveCard(deck.deal());
			com.receiveCard(deck.deal());
		}
	}
		
	public List<Card> getPlayerCards() {
		return this.player.getCards();
	}

	public List<Card> getComCards() {
		return this.com.getCards();
	}
	
	public int getPlayerScore() {
		return this.player.getScore();
	}

	public int getComScore() {
		return this.com.getScore();
	}
	
	public void dealPlayer() {
		this.player.receiveCard(deck.deal());
	}
	
	public void dealCom() {
		this.com.receiveCard(deck.deal());
	}

	public void setGameStatus(GameStatusEnum status) {
		this.status = status;
	}
	
	public GameStatusEnum getGameStatus() {
		return this.status;		
	}


	
}
