package br.com.ifsp.blackjackjava.scene.impl;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Random;

import br.com.ifsp.blackjackjava.Card;
import br.com.ifsp.blackjackjava.SceneItem;
import br.com.ifsp.blackjackjava.Table;
import br.com.ifsp.blackjackjava.enums.GameStatusEnum;
import br.com.ifsp.blackjackjava.enums.ProbabilityEnum;
import br.com.ifsp.blackjackjava.scene.Scene;

public class GameSceneImpl extends Scene {

	private Table table;
	private Random random;

	public GameSceneImpl() {
		table = new Table();
		random = new Random();
		updateSceneItens();
	}

	@Override
	public Scene updateScene(int keyPressed) {

		SceneItem arrow = this.sceneItens.get("arrow");
		
        if (keyPressed == KeyEvent.VK_UP
        	&& table.getGameStatus() != GameStatusEnum.PLAYER_LOST 
        	&& table.getGameStatus() != GameStatusEnum.PLAYER_WON) {
        	return handleArrowUp(arrow);
        } else if (keyPressed == KeyEvent.VK_DOWN 
        			&& table.getGameStatus() != GameStatusEnum.PLAYER_LOST 
        			&& table.getGameStatus() != GameStatusEnum.PLAYER_WON) {
        	return handleArrowDown(arrow);
        } else if (keyPressed == KeyEvent.VK_ENTER) {
            return handleArrowEnter(arrow);
        }
        return this; 	
	}
	    
    private Scene handleArrowUp(SceneItem arrow) {
        switch (arrow.getYPosition()) {
            case 540:
            	updateArrowPosition(arrow, 850, 650);
                return this;
            case 650:
            	updateArrowPosition(arrow, 780, 570);
            	return this;
            case 570:
            	updateArrowPosition(arrow, 780, 540);
            	return this;
            default:
            	return this;
        }
    }

    private Scene handleArrowDown(SceneItem arrow) {
        switch (arrow.getYPosition()) {
            case 540:
            	updateArrowPosition(arrow, 780, 570);
            	return this;
            case 570:
            	updateArrowPosition(arrow, 850, 650);
            	return this;
            case 650:
            	updateArrowPosition(arrow, 780, 540);
            	return this;
            default:
            	return this;
        }
    }

    private Scene handleArrowEnter(SceneItem arrow) {
        switch (arrow.getYPosition()) {
            case 540:
                table.dealPlayer();
                round();
                updateSceneItens();
                return this;
            case 570:
                table.setGameStatus(GameStatusEnum.PLAYER_STOPED);
                round();
                updateSceneItens();
                return this;
            case 650:
            	return new MainMenuSceneImpl();
            default:
            	return this;
        }
    }
    
    private void updateArrowPosition(SceneItem arrow, int xPos, int yPos) {
    	arrow.updateXPosition(xPos);
    	arrow.updateYPosition(yPos);        
    }

	private void round() {	
		while(isRoundOngoing()){
			if(shouldGiveAnotherCardToCom()) {
				table.dealCom();
			} else {
				table.setGameStatus(GameStatusEnum.COM_STOPED);
			}
			isPlayerLost();
			isPlayerWon();
			isGameIsDraw();						
		}	
	}
	
    private void isGameIsDraw() {
		if(table.getGameStatus() == GameStatusEnum.BOTH_STOPED
				&& table.getPlayerScore() == table.getComScore() || (table.getPlayerScore() == 21 && table.getComScore() == 21)) {
			table.setGameStatus(GameStatusEnum.DRAW);
		}
	}
	
	private void isPlayerWon() {
		if (table.getPlayerScore() == 21) {
			table.setGameStatus(GameStatusEnum.PLAYER_WON);
		}
		
		if(table.getGameStatus() == GameStatusEnum.BOTH_STOPED
				&& table.getPlayerScore() > table.getComScore()) {
			table.setGameStatus(GameStatusEnum.PLAYER_WON);
		}
	}
	
	private void isPlayerLost() {
		if (table.getComScore() == 21) {
			table.setGameStatus(GameStatusEnum.PLAYER_LOST);
		}
	}
	
	private boolean isRoundOngoing() {
        return table.getGameStatus() != GameStatusEnum.PLAYER_WON 
            && table.getGameStatus() != GameStatusEnum.PLAYER_LOST 
            && table.getGameStatus() != GameStatusEnum.COM_STOPED
            && table.getGameStatus() != GameStatusEnum.DRAW;
    }	
		
	private boolean shouldGiveAnotherCardToCom() {		
		int comScore = table.getComScore();
	    if (isComStopped(comScore)) {
	        return false;
	    }
	    return calculateProbability(comScore);
	}
	
	private boolean calculateProbability(int comScore) {
        ProbabilityEnum probability = ProbabilityEnum.fromScore(comScore);
        return random.nextDouble() < probability.getProbability();	
	}
	

	
	private void updateSceneItens() {
		this.sceneItens = new HashMap<String, SceneItem>();	
		
		if (isPlayerWon(table.getGameStatus())) {
			this.sceneItens.put("win", buildSceneItem(200, 250, "win"));
			this.sceneItens.put("arrow", buildSceneItem(840, 650, "arrow"));
		} else if (isPlayerLost(this.table.getGameStatus())) {
			this.sceneItens.put("loose", buildSceneItem(200, 250, "loose"));
			this.sceneItens.put("arrow", buildSceneItem(840, 650, "arrow"));
		} else if (isPlayerStoped(this.table.getGameStatus())) {
			this.sceneItens.put("draw", buildSceneItem(200, 250, "draw"));
			this.sceneItens.put("arrow", buildSceneItem(840, 650, "arrow"));
		} else {
			this.sceneItens.put("proceed", buildSceneItem(800, 540, "proceed"));
			this.sceneItens.put("stop", buildSceneItem(800, 570, "stop"));
			this.sceneItens.put("arrow", buildSceneItem(780, 540, "arrow"));
		}
		
		this.sceneItens.put("back", buildSceneItem(880, 650, "back"));

		int counter = 1;
		int cardPosition = 80;
		for (Card comCard : this.table.getComCards()) {
			if (isPlayerLost(this.table.getGameStatus()) || isPlayerWon(this.table.getGameStatus()) || isGameDraw(this.table.getGameStatus())) {


				this.sceneItens.put("com-card" + counter, buildSceneItem(cardPosition, 30, "cards\\" + comCard.getCardName()));

				String[] comScoreChars = split(this.table.getComScore() + "");
				if (isScoreCharsLengthHigherThan(comScoreChars, 1)) {
					this.sceneItens.put("com-first-char-points", buildSceneItem(810, 60, "numbers\\" + comScoreChars[0]));
					this.sceneItens.put("com-second-char-points", buildSceneItem(850, 60, "numbers\\" + comScoreChars[1]));
				} else {
					this.sceneItens.put("com-first-char-points", buildSceneItem(830, 60, "numbers\\" + comScoreChars[0]));
				}

				this.sceneItens.put("com--score", buildSceneItem(800, 30, "score"));

			} else {
				this.sceneItens.put("com-back-card" + counter, buildSceneItem(cardPosition, 30, "back-card"));
			}

			cardPosition = cardPosition + 140;
			counter++;
		}

		String[] playerScoreChars = split(this.table.getPlayerScore() + "");
		if (isScoreCharsLengthHigherThan(playerScoreChars, 1)) {
			this.sceneItens.put("player-first-char-points",	buildSceneItem(810, 450, "numbers\\" + playerScoreChars[0]));
			this.sceneItens.put("player-second-char-points", buildSceneItem(850, 450, "numbers\\" + playerScoreChars[1]));
		} else {
			this.sceneItens.put("player-first-char-points", buildSceneItem(830, 450, "numbers\\" + playerScoreChars[0]));
		}

		this.sceneItens.put("score-player", buildSceneItem(800, 420, "score"));

		counter = 1;
		cardPosition = 80;
		for (Card playerCard : this.table.getPlayerCards()) {
			this.sceneItens.put("player-card" + counter, buildSceneItem(cardPosition, 450, "cards\\" + playerCard.getCardName()));
			cardPosition = cardPosition + 140;
			counter++;
		}
	}
	
	private boolean isComStopped(int comScore) {
	    return table.getGameStatus() == GameStatusEnum.COM_STOPED || comScore == 21;
	}
	
	private boolean isPlayerStoped(GameStatusEnum gameStatus) {
		return GameStatusEnum.PLAYER_STOPED == gameStatus;
	}

	private boolean isPlayerLost(GameStatusEnum gameStatus) {
		return GameStatusEnum.PLAYER_LOST == gameStatus;
	}

	private boolean isPlayerWon(GameStatusEnum playerSituation) {
		return GameStatusEnum.PLAYER_WON == playerSituation;
	}

	private boolean isGameDraw(GameStatusEnum gameStatus) {
		return GameStatusEnum.DRAW == gameStatus;
	}

	private String[] split(String score) {
		return score.split("");
	}

	private boolean isScoreCharsLengthHigherThan(String[] array, int value) {
		return array.length > value;
	}
}
