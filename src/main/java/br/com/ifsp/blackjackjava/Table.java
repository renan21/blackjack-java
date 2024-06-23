package br.com.ifsp.blackjackjava;

import java.util.List;

public class Table {
	
	private Deck deck;
	private Player player;		
	private Player com;
	private boolean draw = false;	
	
	public void buildTable() {
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
		return player.getCards();
	}

	public List<Card> getComCards() {
		return com.getCards();
	}
	
	public int getPlayerScore() {
		return player.getScore();
	}

	public int getComScore() {
		return com.getScore();
	}
	
	public void dealPlayer() {
		player.receiveCard(deck.deal());
	}
	
	public void dealCom() {
		com.receiveCard(deck.deal());
	}

	public void setPlayerLoose() {
		player.setSituation(1);		
	}
	
	public void setPlayerWin() {
		player.setSituation(2);		
	}

	public void setComStop() {
		com.setSituation(3);		
	}
	
	public void setPlayerStop() {
		player.setSituation(3);
	}
	
	public int getPlayerSituation() {
		return player.getSituation();
	}
	
	public int getComSituation() {
		return com.getSituation();
	}

	public void setDraw() {
		draw = true;
	}
	
	public boolean isDraw() {
		return draw;
	}
	
}
