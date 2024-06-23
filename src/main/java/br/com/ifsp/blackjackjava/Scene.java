package br.com.ifsp.blackjackjava;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Scene {	

	private Table table;
	private Map<String, SceneItem> sceneItens;
	
	private boolean spriteMainMenuActive = false;
	private boolean spriteRuleActive = false;
	
	public void loadMainMenuSceneItens() {		
		spriteMainMenuActive = true;
		spriteRuleActive = false;
		
		sceneItens = new HashMap<String, SceneItem>();
		sceneItens.put("logo", new SceneItem(300, 80, "src\\main\\resources\\images\\logo.png"));
		sceneItens.put("play", new SceneItem(450, 540, "src\\main\\resources\\images\\play.png"));
		sceneItens.put("rule", new SceneItem(442, 570, "src\\main\\resources\\images\\rule.png"));
		sceneItens.put("exit", new SceneItem(452, 600, "src\\main\\resources\\images\\exit.png"));
		sceneItens.put("arrow", new SceneItem(400, 540, "src\\main\\resources\\images\\arrow.png"));
	}
	
	public void loadGameSceneItens() {
		
		spriteMainMenuActive = false;
		spriteRuleActive = false;
		
		sceneItens = new HashMap<String, SceneItem>();
		sceneItens.put("back", new SceneItem(880, 650, "src\\main\\resources\\images\\back.png"));
		
		
		if(table.getPlayerSituation() == 2) {
			sceneItens.put("win", new SceneItem(200, 250, "src\\main\\resources\\images\\win.png"));
			sceneItens.put("arrow", new SceneItem(840, 650, "src\\main\\resources\\images\\arrow.png"));
		} else if (table.getPlayerSituation() == 1) {
			sceneItens.put("loose", new SceneItem(200, 250, "src\\main\\resources\\images\\loose.png"));
			sceneItens.put("arrow", new SceneItem(840, 650, "src\\main\\resources\\images\\arrow.png"));
		} else if(table.isDraw()) {
			sceneItens.put("draw", new SceneItem(200, 250, "src\\main\\resources\\images\\draw.png"));
			sceneItens.put("arrow", new SceneItem(840, 650, "src\\main\\resources\\images\\arrow.png"));
		} else {
			sceneItens.put("continue", new SceneItem(800, 540, "src\\main\\resources\\images\\continue.png"));
			sceneItens.put("stop", new SceneItem(800, 570, "src\\main\\resources\\images\\stop.png"));
			sceneItens.put("arrow", new SceneItem(780, 540, "src\\main\\resources\\images\\arrow.png"));
		}
				
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
	
	public void loadRuleSceneItens() {
		spriteMainMenuActive = false;
		spriteRuleActive = true;
		
		sceneItens.put("rules", new SceneItem(30, 30, "src\\main\\resources\\images\\rules.png"));		
		sceneItens.put("back", new SceneItem(880, 650, "src\\main\\resources\\images\\back.png"));
		sceneItens.put("arrow", new SceneItem(840, 650, "src\\main\\resources\\images\\arrow.png"));
	}
	
	public void keyPressed(KeyEvent keyEvent) {
		int keyCode = keyEvent.getKeyCode();
		SceneItem arrow = sceneItens.get("arrow");
				
		//540 - Play - Continue
		//570 - Rule - Stop
		//600 - Exit
		//650 - Back
									
		if(keyCode == KeyEvent.VK_UP) {
			if(!spriteRuleActive) {
				if(arrow.getYPostion() == 540) {				
					if(spriteMainMenuActive) {
						arrow.updateYPostition(600);
					} else {
						arrow.updateYPostition(650);
						arrow.updateXPostition(840);
					}				
				} else if(arrow.getYPostion() == 570) {
					arrow.updateYPostition(540);				
				}  else if(arrow.getYPostion() == 600 || arrow.getYPostion() == 650) {				
					
					arrow.updateYPostition(570);
					if(!spriteMainMenuActive) {
						arrow.updateXPostition(780);
					}				
				}
			}
		}
				
		
		if(keyCode == KeyEvent.VK_DOWN) {			
			if(!spriteRuleActive) {
				if(arrow.getYPostion() == 540) {
					if(spriteMainMenuActive) {
						
					} else {
						arrow.updateXPostition(780);
					}
					
					arrow.updateYPostition(570);
					
				} else if(arrow.getYPostion() == 570) {
					
					if(spriteMainMenuActive) {
						arrow.updateYPostition(600);
					} else {
						arrow.updateYPostition(650);
						arrow.updateXPostition(840);
					}
					
				} else if(arrow.getYPostion() == 600 || arrow.getYPostion() == 650) {
					
					if(!spriteMainMenuActive) {
						arrow.updateXPostition(780);
					}
					
					arrow.updateYPostition(540);	
				}
			}
		}
		
		if(keyCode == KeyEvent.VK_ENTER) {			
			if(spriteMainMenuActive) {
				if(arrow.getYPostion() == 540) {
					//Play
					sceneItens.clear();
					round(99);
					loadGameSceneItens();
				} else if(arrow.getYPostion() == 570) {
					// Rule
					sceneItens.clear();
					loadRuleSceneItens();
				} else if(arrow.getYPostion() == 600) {
					//Exit
					System.exit(0);
				}
			} else if(spriteRuleActive){
				loadMainMenuSceneItens();
			} else {
				if(arrow.getYPostion() == 540) {
					sceneItens.clear();
					round(0);				
					loadGameSceneItens();
				} else if(arrow.getYPostion() == 570) {
					//Stop
					round(3);
					loadGameSceneItens();
				} else if(arrow.getYPostion() == 650) {
					//Back
					table = null;
					loadMainMenuSceneItens();
				}
			}
		}		
	}
	
	
	private void round(int continueStop) {		
		if(table == null) {
			table = new Table();
			table.buildTable();
			
			if(table.getPlayerScore() == 21) {
				table.setPlayerWin();
			}
		} else {
			
			if(continueStop == 0) {
				table.dealPlayer();
				if(table.getPlayerScore() == 21) {
					table.setPlayerWin();
					return;
				} else if(table.getPlayerScore() > 21) {
					table.setPlayerLoose();
					return;
				}
				
				if(shouldGiveAnotherCardToCom()) {
					table.dealCom();
				} else {
					table.setComStop();
				}
				
				if(table.getComScore() > 21) {
					table.setPlayerWin();
					return;
				}
			} else if (continueStop == 3) {
				table.setPlayerStop();
				while(shouldGiveAnotherCardToCom()) {
					table.dealCom();						
				}
				
				if(table.getComScore() == 21) {
					table.setPlayerLoose();
					return;
				} else if(table.getComScore() > 21) {
					table.setPlayerWin();
					return;
				}
				
				table.setComStop();
			}			
						
			if(table.getComSituation() == 3 && table.getPlayerSituation() == 3) {
				if(table.getComScore() > table.getPlayerScore()) {
					table.setPlayerLoose();
					return;
				} else if (table.getPlayerScore() > table.getComScore()) {
					table.setPlayerWin();
					return;
				} else if (table.getPlayerScore() == table.getComScore()){
					table.setDraw();
					return;
				}
			}
		}		
	}

	private boolean shouldGiveAnotherCardToCom() {		
		int comScore = table.getComScore();		
		Random random = new Random();		
		if(table.getComSituation() == 3 || comScore >= 21) {
			return false;
		} else if(comScore == 15) {
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

	public Map<String, SceneItem> getSceneItens() {
		return sceneItens;
	}

	private String[] split(String string) {
		return string.split("");
	}

}
