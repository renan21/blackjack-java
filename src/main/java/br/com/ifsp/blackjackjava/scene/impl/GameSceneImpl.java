package br.com.ifsp.blackjackjava.scene.impl;

import java.util.List;

import br.com.ifsp.blackjackjava.Card;
import br.com.ifsp.blackjackjava.SceneItem;
import br.com.ifsp.blackjackjava.enums.PlayerSituationEnum;
import br.com.ifsp.blackjackjava.scene.Scene;

public class GameSceneImpl extends Scene {
	
	private SceneItem back;
	private SceneItem win;
	private SceneItem arrow;
	private SceneItem loose;
	private SceneItem draw;
	private SceneItem proceed;
	private SceneItem stop;
	private SceneItem backcard;
	
	GameSceneImpl(int playerSituation){
		
		this.back = buildSceneItem(880, 650, "back");
		
		if(isPlayerWin(playerSituation)) {
			this.win = buildSceneItem(200, 250, "win");
			this.arrow = buildSceneItem(840, 650, "arrow");
		} else if(isPlayerLoose(playerSituation)) {
			this.loose = buildSceneItem(200, 250, "loose");
			this.arrow = buildSceneItem(840, 650, "arrow");
		} else if(isPlayerStop(playerSituation)) {
			this.draw = buildSceneItem(200, 250, "draw");
			this.arrow = buildSceneItem(840, 650, "arrow");
		} else {
			this.proceed = buildSceneItem(800, 540, "proceed");
			this.stop = buildSceneItem(800, 570, "stop");
			this.arrow = buildSceneItem(780, 540, "arrow");
		}
				
	}

	


	private boolean isPlayerStop(int playerSituation) {
		return PlayerSituationEnum.STOP.getSituation() == playerSituation;
	}




	private boolean isPlayerLoose(int playerSituation) {
		return PlayerSituationEnum.LOOSE.getSituation() == playerSituation;
	}




	private boolean isPlayerWin(int playerSituation) {
		return PlayerSituationEnum.WIN.getSituation() == playerSituation;
	}




	public void aaa() {
		
		

				
		int counter = 0;
		int cardPosition = 80;
		for(Card comCard : table.getComCards()) {
			if(table.getPlayerSituation() == 1 || table.getPlayerSituation() == 2 || table.isDraw()) {
				sceneItens.put("playerCard" + counter, new SceneItem(cardPosition, 30, String.format("src\\main\\resources\\images\\cards\\%s-%s.png", comCard.getCardSuit(), comCard.getCardValue())));
				String[] comScoreChars = split(table.getComScore() + "");
				if(comScoreChars.length > 1) {
					sceneItens.put("firstCharComScore", new SceneItem(810, 60, String.format("src\\main\\resources\\images\\numbers\\%s.png", comScoreChars[0])));
					sceneItens.put("secondCharComScore", new SceneItem(850, 60, String.format("src\\main\\resources\\images\\numbers\\%s.png", comScoreChars[1])));
				} else {
					sceneItens.put("firstCharComScore", new SceneItem(830, 60, String.format("src\\main\\resources\\images\\numbers\\%s.png", comScoreChars[0])));
				}
				sceneItens.put("comScoreTitle", new SceneItem(800, 30, "src\\main\\resources\\images\\score.png"));
			} else {
				sceneItens.put("comCard" + counter, new SceneItem(cardPosition, 30, String.format("src\\main\\resources\\images\\back-card.png", comCard.getCardSuit(), comCard.getCardValue())));
			}

			counter++;
			cardPosition = cardPosition + 140;
		}
		


		String[] playerScoreChars = split(table.getPlayerScore() + "");
		if(playerScoreChars.length > 1) {
			sceneItens.put("firstCharPlayerScore", new SceneItem(810, 450, String.format("src\\main\\resources\\images\\numbers\\%s.png", playerScoreChars[0])));
			sceneItens.put("secondCharPlayerScore", new SceneItem(850, 450, String.format("src\\main\\resources\\images\\numbers\\%s.png", playerScoreChars[1])));
		} else {
			sceneItens.put("firstCharPlayerScore", new SceneItem(830, 450, String.format("src\\main\\resources\\images\\numbers\\%s.png", playerScoreChars[0])));
		}
		sceneItens.put("playerScoreTitle", new SceneItem(800, 420, "src\\main\\resources\\images\\score.png"));

		
		counter = 0;
		cardPosition = 80;
		for(Card playerCard : table.getPlayerCards()) {
			sceneItens.put("playerCard-" + counter, new SceneItem(cardPosition, 450, String.format("src\\main\\resources\\images\\cards\\%s-%s.png", playerCard.getCardSuit(), playerCard.getCardValue())));
			counter++;
			cardPosition = cardPosition + 140;
		}
		
	}


	@Override
	public List<SceneItem> getSceneItens() {
		// TODO Auto-generated method stub
		return null;
	}



}
