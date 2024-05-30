package br.com.ifsp.blackjackjava.service;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import br.com.ifsp.blackjackjava.SceneItem;
import br.com.ifsp.blackjackjava.Table;
import br.com.ifsp.blackjackjava.scene.Scene;
import br.com.ifsp.blackjackjava.scene.impl.MainMenuScene;

public class SceneService {	

	private Table table;	
	private Scene scene;
	
	
	
	private boolean spriteMainMenuActive = false;
	private boolean spriteRuleActive = false;
	
	public void loadMainMenuSceneItens() {
		this.scene = new MainMenuScene();
	}
	
	public void loadGameSceneItens() {

	
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
