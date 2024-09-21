package br.com.ifsp.blackjackjava.service;

import java.awt.event.KeyEvent;
import java.util.Map;

import br.com.ifsp.blackjackjava.SceneItem;
import br.com.ifsp.blackjackjava.scene.Scene;
import br.com.ifsp.blackjackjava.scene.impl.MainMenuSceneImpl;

public class SceneService {	

	private Scene scene;
	
	public void loadMainMenuSceneItens() {
		this.scene = new MainMenuSceneImpl();
	}
		
	public void keyPressed(KeyEvent keyEvent) {
		int keyCode = keyEvent.getKeyCode();		
		this.scene = this.scene.updateScene(keyCode);
	}
	
	public Map<String, SceneItem> getSceneItens() {
		return scene.getSceneItens();
	}

}