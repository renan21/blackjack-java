package br.com.ifsp.blackjackjava;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import br.com.ifsp.blackjackjava.service.SceneService;

public class Scenario extends JPanel implements ActionListener {
	
	private Image background;
	private SceneService sceneService;
	private Timer timer;

	public Scenario() {
		setFocusable(true);
		setDoubleBuffered(true);
		
		ImageIcon imageIcon = new ImageIcon("src\\main\\resources\\images\\background.png");
		this.background = imageIcon.getImage();
		
		sceneService = new SceneService();
		sceneService.loadMainMenuSceneItens();
		
		addKeyListener(new KeyboardAdapter());
		
		this.timer = new Timer(5, this);
		this.timer.start();

	}
	
	@Override
	public void paint(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D) graphics;
		graphics2d.drawImage(background, 0, 0, this);
		for(Entry<String, SceneItem> sceneItem : sceneService.getSceneItens().entrySet()) {
			graphics2d.drawImage(sceneItem.getValue().getImage(), sceneItem.getValue().getXPosition(), sceneItem.getValue().getYPosition(), this);			
		}
		
		graphics.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	private class KeyboardAdapter extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			sceneService.keyPressed(e);
		}
		
	}
	
}