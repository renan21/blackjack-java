package br.com.ifsp.blackjackjava;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainMenu implements Scene {	

	private Map<String, SceneItem> sceneItens;
	
	public void loadSceneItens() {
		sceneItens = new HashMap<String, SceneItem>();
		sceneItens.put("logo", new SceneItem(300, 80, "src\\main\\resources\\images\\logo.png"));
		sceneItens.put("play", new SceneItem(450, 540, "src\\main\\resources\\images\\play.png"));
		sceneItens.put("rules", new SceneItem(442, 570, "src\\main\\resources\\images\\rules.png"));
		sceneItens.put("exit", new SceneItem(452, 600, "src\\main\\resources\\images\\exit.png"));
		sceneItens.put("arrow", new SceneItem(400, 540, "src\\main\\resources\\images\\arrow.png"));
		
	}
	
	public void keyPressed(KeyEvent keyEvent) {
		int keyCode = keyEvent.getKeyCode();
		SceneItem arrow = sceneItens.get("arrow");
		
		System.out.println(arrow.getDefaultYPostion());
		
				
		if(keyCode == KeyEvent.VK_UP) {
			if(arrow.getDefaultYPostion() == 540) {
				arrow.updateYPostition(600);
			} else if(arrow.getDefaultYPostion() == 570) {
				arrow.updateYPostition(540);
			} else if(arrow.getDefaultYPostion() == 600) {
				arrow.updateYPostition(570);
			}
		}
		
		if(keyCode == KeyEvent.VK_DOWN) {
			if(arrow.getDefaultYPostion() == 540) {
				arrow.updateYPostition(570);
			} else if(arrow.getDefaultYPostion() == 570) {
				arrow.updateYPostition(600);
			} else if(arrow.getDefaultYPostion() == 600) {
				arrow.updateYPostition(540);
			}
		}
		
	}
	
	
	public Map<String, SceneItem> getSceneItens() {
		return sceneItens;
	}


}
