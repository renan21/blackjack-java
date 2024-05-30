package br.com.ifsp.blackjackjava.scene.impl;

import java.util.List;

import br.com.ifsp.blackjackjava.SceneItem;
import br.com.ifsp.blackjackjava.scene.Scene;

public class MainMenuSceneImpl extends Scene {
	
	private SceneItem logo;
	private SceneItem play;
	private SceneItem rule;
	private SceneItem exit;
	private SceneItem arrow;
	
	MainMenuSceneImpl(){
		this.logo = buildSceneItem(300, 80, "logo");
		this.play = buildSceneItem(450, 540, "play");
		this.rule = buildSceneItem(442, 570, "rule");
		this.exit = buildSceneItem(452, 600, "exit");
		this.arrow = buildSceneItem(400, 540, "arrow");
	}
		
	@Override
	public List<SceneItem> getSceneItens(){
		return List.of(logo, play, rule, exit, arrow);
	}

}