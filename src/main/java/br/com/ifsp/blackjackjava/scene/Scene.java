package br.com.ifsp.blackjackjava.scene;

import java.util.List;

import br.com.ifsp.blackjackjava.SceneItem;

public abstract class Scene {		

	public abstract  List<SceneItem> getSceneItens();
	
	protected SceneItem buildSceneItem(int xPosition, int yPosition, String itemName) {
		return new SceneItem(xPosition, yPosition, itemName);
	}	

}
