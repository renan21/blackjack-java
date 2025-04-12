package br.com.ifsp.blackjackjava.scene.impl;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Random;

import br.com.ifsp.blackjackjava.enums.ProbabilityEnum;
import br.com.ifsp.blackjackjava.rules.Card;
import br.com.ifsp.blackjackjava.rules.Table;
import br.com.ifsp.blackjackjava.scene.Scene;
import br.com.ifsp.blackjackjava.scene.SceneItem;

public class GameSceneImpl extends Scene {

	private Table table;
	private Random random;

	public GameSceneImpl() {
		table = new Table();
		random = new Random();
		table.updateGameStatus();
		updateSceneItens();		
	}
	
	@Override
	public Scene updateScene(int keyPressed) {

		SceneItem arrow = this.sceneItens.get("arrow");
		
        if (keyPressed == KeyEvent.VK_UP && !table.isHumanWon() && !table.isHumanLost() && !table.isGameDraw()) {
        	return handleArrowUp(arrow);
        } else if (keyPressed == KeyEvent.VK_DOWN && !table.isHumanWon() && !table.isHumanLost() && !table.isGameDraw()) {
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
            	break;
            case 650:
            	updateArrowPosition(arrow, 780, 570);
            	break;
            case 570:
            	updateArrowPosition(arrow, 780, 540);
            	break;
        }
        table.updateGameStatus();
        return this;
    }

    private Scene handleArrowDown(SceneItem arrow) {
        switch (arrow.getYPosition()) {
            case 540:
            	updateArrowPosition(arrow, 780, 570);
            	break;
            case 570:
            	updateArrowPosition(arrow, 850, 650);
            	break;
            case 650:
            	updateArrowPosition(arrow, 780, 540);
            	break;
        }
        table.updateGameStatus();
        return this;
    }

    private Scene handleArrowEnter(SceneItem arrow) {
        switch (arrow.getYPosition()) {
            case 540:
                table.dealHuman();
                break;
            case 570:
                table.setHumanStop();
                break;
            case 650:
            	return new MainMenuSceneImpl();
        }
        round();
        updateSceneItens();
        table.updateGameStatus();
        return this;
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
				table.setComStop();
			}
			table.updateGameStatus();
		}	
	}
		
	private boolean isRoundOngoing() {
        return !table.isHumanWon() 
            && !table.isHumanLost()
            && !table.isComStoped()
            && !table.isGameDraw();
    }	
		
	private boolean shouldGiveAnotherCardToCom() {
	    return calculateProbability(table.getComScore());
	}
	
	private boolean calculateProbability(int comScore) {
        ProbabilityEnum probability = ProbabilityEnum.fromScore(comScore);
        return random.nextDouble() < probability.getProbability();	
	}
	

	
	private void updateSceneItens() {
		this.sceneItens = new HashMap<String, SceneItem>();	
		
		if (table.isHumanWon()) {
			this.sceneItens.put("win", buildSceneItem(200, 250, "win"));
			this.sceneItens.put("arrow", buildSceneItem(840, 650, "arrow"));
		} else if (table.isHumanLost()) {
			this.sceneItens.put("loose", buildSceneItem(200, 250, "loose"));
			this.sceneItens.put("arrow", buildSceneItem(840, 650, "arrow"));
		} else if (table.isGameDraw()) {
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
			if (table.isHumanLost() || table.isHumanWon() || table.isGameDraw()) {


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

		String[] playerScoreChars = split(this.table.getHumanScore() + "");
		if (isScoreCharsLengthHigherThan(playerScoreChars, 1)) {
			this.sceneItens.put("player-first-char-points",	buildSceneItem(810, 450, "numbers\\" + playerScoreChars[0]));
			this.sceneItens.put("player-second-char-points", buildSceneItem(850, 450, "numbers\\" + playerScoreChars[1]));
		} else {
			this.sceneItens.put("player-first-char-points", buildSceneItem(830, 450, "numbers\\" + playerScoreChars[0]));
		}

		this.sceneItens.put("score-player", buildSceneItem(800, 420, "score"));

		counter = 1;
		cardPosition = 80;
		for (Card playerCard : this.table.getHumanCards()) {
			this.sceneItens.put("player-card" + counter, buildSceneItem(cardPosition, 450, "cards\\" + playerCard.getCardName()));
			cardPosition = cardPosition + 140;
			counter++;
		}
	}
	
	private String[] split(String score) {
		return score.split("");
	}

	private boolean isScoreCharsLengthHigherThan(String[] array, int value) {
		return array.length > value;
	}
}
