package br.com.ifsp.blackjackjava.scene;

import java.util.Map;

import br.com.ifsp.blackjackjava.SceneItem;

public abstract class Scene {
	
	protected Map<String, SceneItem> sceneItens;
				
	public abstract Scene updateScene(int keyPressed);
	
	public Map<String, SceneItem> getSceneItens(){
		return this.sceneItens;
	}	

	protected SceneItem buildSceneItem(int xPosition, int yPosition, String itemName) {
		return new SceneItem(xPosition, yPosition, itemName);
	}	

}
