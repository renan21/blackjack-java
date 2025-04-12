package br.com.ifsp.blackjackjava.scene.impl;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import br.com.ifsp.blackjackjava.scene.Scene;
import br.com.ifsp.blackjackjava.scene.SceneItem;

public class MainMenuSceneImpl extends Scene {	
	
	public MainMenuSceneImpl(){
		this.sceneItens = new HashMap<String, SceneItem>();
		this.sceneItens.put("logo", buildSceneItem(300, 80, "logo"));
		this.sceneItens.put("play", buildSceneItem(450, 540, "play"));
		this.sceneItens.put("rule", buildSceneItem(442, 570, "rule"));
		this.sceneItens.put("exit", buildSceneItem(452, 600, "exit"));
		this.sceneItens.put("arrow", buildSceneItem(400, 540, "arrow"));
	}
		
	@Override
	public Scene updateScene(int keyPressed) {
		
		SceneItem arrow = this.sceneItens.get("arrow");		
		
		switch(keyPressed) {
			case KeyEvent.VK_UP:
				if(arrow.getYPosition() == 540) {
					arrow.updateYPosition(600);
				} else if(arrow.getYPosition() == 600) {
					arrow.updateYPosition(570);
				} else if(arrow.getYPosition() == 570) {
					arrow.updateYPosition(540);
				}
				return this;
			case KeyEvent.VK_DOWN:
				if(arrow.getYPosition() == 540) {
					arrow.updateYPosition(570);
				} else if(arrow.getYPosition() == 570) {
					arrow.updateYPosition(600);
				} else if(arrow.getYPosition() == 600) {
					arrow.updateYPosition(540);
				}
				return this;
			case KeyEvent.VK_ENTER:
				if(arrow.getYPosition() == 540) {
					return new GameSceneImpl();
				} else if(arrow.getYPosition() == 570) {
					return new RulesSceneImpl();
				} else if(arrow.getYPosition() == 600) {
					System.exit(0);
				}
			default:
				return this;
		}

	}


}