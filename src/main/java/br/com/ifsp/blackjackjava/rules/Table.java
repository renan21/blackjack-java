package br.com.ifsp.blackjackjava.rules;

import java.util.List;

import br.com.ifsp.blackjackjava.enums.GameStatusEnum;
import br.com.ifsp.blackjackjava.enums.PlayerStatusEnum;

public class Table {
	
	private Deck deck;
	private Player human;		
	private Player com;
	private GameStatusEnum gameStatus;	
	
	public Table() {
		gameStatus = GameStatusEnum.PLAYING;
		deck = new Deck();
		deck.shuffle();
				
		human = new Player();
		com = new Player();
				
		for(int i = 0; i < 2; i++) {
			human.receiveCard(deck.deal());
			com.receiveCard(deck.deal());
		}
	}
		
	public List<Card> getHumanCards() {
		return human.getCards();
	}

	public List<Card> getComCards() {
		return com.getCards();
	}
	
	public int getHumanScore() {
		return human.getScore();
	}

	public int getComScore() {
		return com.getScore();
	}
	
	public void dealHuman() {
		human.receiveCard(deck.deal());
	}
	
	public void dealCom() {
		com.receiveCard(deck.deal());
	}
	
	public void updateGameStatus() {
		
		if(com.getScore() == 21){
			gameStatus = GameStatusEnum.HUMAN_LOST;
		} else if(human.getScore() == 21) {
			gameStatus = GameStatusEnum.HUMAN_WON;
		} else if(com.getScore() == 21 && human.getScore() == 21) {
			gameStatus = GameStatusEnum.DRAW;
		} else if(com.getScore() > 21 && human.getScore() < 21) {
			gameStatus = GameStatusEnum.HUMAN_WON;
		} else if(human.getScore() > 21 && com.getScore() < 21) {
			gameStatus = GameStatusEnum.HUMAN_LOST;			
		} else if(human.getScore() > 21 && com.getScore() > 21) {
			gameStatus = GameStatusEnum.DRAW;
		} else if(isBothPlayersStoped()) {
			if(com.getScore() > human.getScore()) {
				gameStatus = GameStatusEnum.HUMAN_LOST;
			} else if(com.getScore() < human.getScore()) {
				gameStatus = GameStatusEnum.HUMAN_WON;
			} else {
				gameStatus = GameStatusEnum.DRAW;				
			}
		}
		
		
		System.out.println("");
		System.out.println(" ************************* ");
		System.out.println("Human: " + getHumanScore());
		System.out.println("Com: " + getComScore());
		System.out.println("Human: " + getGameStatus());
	}
	
	public boolean isHumanWon() {
		return gameStatus == GameStatusEnum.HUMAN_WON;
	}
		
	public boolean isHumanLost() {
		return gameStatus == GameStatusEnum.HUMAN_LOST;
	}
		
	public boolean isComStoped() {
		return com.getPlayerStatus() == PlayerStatusEnum.STOP;
	}
		
	public boolean isBothPlayersStoped() {
		return com.getPlayerStatus() == PlayerStatusEnum.STOP && human.getPlayerStatus() == PlayerStatusEnum.STOP;
	}
	
	public boolean isGameDraw() {
		return gameStatus == GameStatusEnum.DRAW;
	}

	public void setComStop() {
		com.setPlayerStatus(PlayerStatusEnum.STOP);		
	}
		
	public void setHumanStop() {
		human.setPlayerStatus(PlayerStatusEnum.STOP);		
	}
	
	public boolean isHumanStoped() {
		return human.getPlayerStatus() == PlayerStatusEnum.STOP;
	}
	
	public GameStatusEnum getGameStatus() {
		return gameStatus;
	}

}