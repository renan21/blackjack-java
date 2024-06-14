package br.com.ifsp.blackjackjava.scene.impl;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Random;

import br.com.ifsp.blackjackjava.Card;
import br.com.ifsp.blackjackjava.SceneItem;
import br.com.ifsp.blackjackjava.Table;
import br.com.ifsp.blackjackjava.enums.GameStatusEnum;
import br.com.ifsp.blackjackjava.scene.Scene;

public class GameSceneImpl extends Scene {

	private Table table;

	public GameSceneImpl() {
		this.table = new Table();
		updateSceneItens();
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

	@Override
	public Scene updateScene(int keyPressed) {

		SceneItem arrow = this.sceneItens.get("arrow");

		switch (keyPressed) {
		case KeyEvent.VK_UP:
			if (arrow.getYPostion() == 540) {
				arrow.updateYPostition(650);
				arrow.updateXPostition(850);
			} else if (arrow.getYPostion() == 650) {
				arrow.updateYPostition(570);
				arrow.updateXPostition(780);
			} else if (arrow.getYPostion() == 570) {
				arrow.updateYPostition(540);
			}
			return this;
		case KeyEvent.VK_DOWN:
			if (arrow.getYPostion() == 540) {
				arrow.updateYPostition(570);
			} else if (arrow.getYPostion() == 570) {
				arrow.updateYPostition(650);
				arrow.updateXPostition(850);
			} else if (arrow.getYPostion() == 650) {
				arrow.updateYPostition(540);
				arrow.updateXPostition(780);
			}
			return this;
		case KeyEvent.VK_ENTER:
			if (arrow.getYPostion() == 540) {
				table.dealPlayer();
			} else if (arrow.getYPostion() == 570) {
				table.setGameStatus(GameStatusEnum.PLAYER_STOPED);
			} else if (arrow.getYPostion() == 650) {
				return new MainMenuSceneImpl();
			}

			round();
			updateSceneItens();

			return this;
		default:
			return this;
		}
	}

	private void round() {
		
		if(shouldGiveAnotherCardToCom()) {
			table.dealCom();
		} else {
			table.setGameStatus(GameStatusEnum.COM_STOPED);
		}
		
		
		if (table.getPlayerScore() == 21 && table.getComScore() == 21) {
			table.setGameStatus(GameStatusEnum.DRAW);
		}

		if (table.getPlayerScore() == 21 || (table.getPlayerScore() < 21 && table.getComScore() > 21)) {
			table.setGameStatus(GameStatusEnum.PLAYER_WON);
		}

		if (table.getComScore() == 21 || (table.getComScore() < 21 && table.getPlayerScore() > 21)) {
			table.setGameStatus(GameStatusEnum.PLAYER_LOST);
		}
		
	}
	
	private boolean shouldGiveAnotherCardToCom() {
		
		int comScore = table.getComScore();		
		Random random = new Random();
		
		if(comScore == 15) {
			return random.nextDouble() < 0.95; 
		} else if(comScore == 16) {
			return random.nextDouble() < 0.85; 
		} else if(comScore == 17) {
			return random.nextDouble() < 0.75; 
		} else if(comScore == 18) {
			return random.nextDouble() < 0.65; 
		} else if(comScore == 19) {
			return random.nextDouble() < 0.55; 
		} else if(comScore == 20) {
			return random.nextDouble() < 0.45; 
		} else if(comScore == 21) {
			return random.nextDouble() < 1.0; 
		}
		
		return true;
	}
	
	private void updateSceneItens() {
		this.sceneItens = new HashMap<String, SceneItem>();	

		if (table.getPlayerScore() == 21 && table.getComScore() == 21) {
			table.setGameStatus(GameStatusEnum.DRAW);
		}

		if (table.getPlayerScore() == 21) {
			table.setGameStatus(GameStatusEnum.PLAYER_WON);
		}

		if (table.getComScore() == 21) {
			table.setGameStatus(GameStatusEnum.PLAYER_LOST);
		}
		
		if (isPlayerWon(this.table.getGameStatus())) {
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

				String card = comCard.getCardSuit() + comCard.getCardValue();
				this.sceneItens.put("com-card" + counter, buildSceneItem(cardPosition, 30, "cards\\" + card));

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
			this.sceneItens.put("player-first-char-points", buildSceneItem(830, 450, playerScoreChars[0]));
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

}
