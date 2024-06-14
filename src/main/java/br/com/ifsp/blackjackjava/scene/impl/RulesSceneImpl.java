package br.com.ifsp.blackjackjava.scene.impl;

import br.com.ifsp.blackjackjava.scene.Scene;

public class RulesSceneImpl extends Scene {
		
	public RulesSceneImpl(){
		this.sceneItens.put("rules", buildSceneItem(30, 30, "rules"));
		this.sceneItens.put("back", buildSceneItem(880, 650, "back"));
		this.sceneItens.put("arrow", buildSceneItem(840, 650, "arrow"));
	}
		
	@Override
	public Scene updateScene(int keyPressed) {
		// TODO Auto-generated method stub
		return null;
	}


}