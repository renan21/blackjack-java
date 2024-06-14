package br.com.ifsp.blackjackjava.service;

import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Random;

import br.com.ifsp.blackjackjava.SceneItem;
import br.com.ifsp.blackjackjava.Table;
import br.com.ifsp.blackjackjava.scene.Scene;
import br.com.ifsp.blackjackjava.scene.impl.MainMenuSceneImpl;

public class SceneService {	

	private Table table;	
	private Scene scene;
	
	public void loadMainMenuSceneItens() {
		this.scene = new MainMenuSceneImpl();
	}
		
	public void keyPressed(KeyEvent keyEvent) {
		int keyCode = keyEvent.getKeyCode();
				
		//540 - Play - Continue
		//570 - Rule - Stop
		//600 - Exit
		//650 - Back
		
		this.scene = this.scene.updateScene(keyCode);

									
//		if(keyCode == KeyEvent.VK_UP) {
//			if(!spriteRuleActive) {
//				if(arrow.getYPostion() == 540) {				
//					if(spriteMainMenuActive) {
//						
//					} else {
//						arrow.updateYPostition(650);
//						arrow.updateXPostition(840);
//					}				
//				} else if(arrow.getYPostion() == 570) {
//					arrow.updateYPostition(540);				
//				}  else if(arrow.getYPostion() == 600 || arrow.getYPostion() == 650) {				
//					
//					arrow.updateYPostition(570);
//					if(!spriteMainMenuActive) {
//						arrow.updateXPostition(780);
//					}				
//				}
//			}
//		}
//		
//		
//		
//		
//				
//		
//		if(keyCode == KeyEvent.VK_DOWN) {
//			
//			if(!spriteRuleActive) {
//				if(arrow.getYPostion() == 540) {
//					if(spriteMainMenuActive) {
//						
//					} else {
//						arrow.updateXPostition(780);
//					}
//					
//					arrow.updateYPostition(570);
//					
//				} else if(arrow.getYPostion() == 570) {
//					
//					if(spriteMainMenuActive) {
//						arrow.updateYPostition(600);
//					} else {
//						arrow.updateYPostition(650);
//						arrow.updateXPostition(840);
//					}
//					
//				} else if(arrow.getYPostion() == 600 || arrow.getYPostion() == 650) {
//					
//					if(!spriteMainMenuActive) {
//						arrow.updateXPostition(780);
//					}
//					
//					arrow.updateYPostition(540);
//					
//				} 
//			}
//		}
//		
//		if(keyCode == KeyEvent.VK_ENTER) {
//			
//			if(spriteMainMenuActive) {
//				if(arrow.getYPostion() == 540) {
//					//Play
//					sceneItens.clear();
//					round(99);
//					loadGameSceneItens();
//				} else if(arrow.getYPostion() == 570) {
//					// Rule
//					sceneItens.clear();
//					loadRuleSceneItens();
//				} else if(arrow.getYPostion() == 600) {
//					//Exit
//					
//				}
//			} else if(spriteRuleActive){
//				loadMainMenuSceneItens();
//			} else {
//				if(arrow.getYPostion() == 540) {
//					sceneItens.clear();
//					round(0);				
//					loadGameSceneItens();
//				} else if(arrow.getYPostion() == 570) {
//					//Stop
//					round(3);
//					loadGameSceneItens();
//				} else if(arrow.getYPostion() == 650) {
//					//Back
//					table = null;
//					loadMainMenuSceneItens();
//				}
//			}
//		}
		
	}
	
	




	public Map<String, SceneItem> getSceneItens() {
		return scene.getSceneItens();
	}



}
