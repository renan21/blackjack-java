package br.com.ifsp.blackjackjava;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class MainMenu {
	
	private Image logo;
	private Image play;
	private Image rules;
	private Image exit;
	private Image arrow;
	private int arrowY;

	
	public void load() {
		ImageIcon logo = new ImageIcon("src\\main\\resources\\images\\logo.png");
		ImageIcon play = new ImageIcon("src\\main\\resources\\images\\play.png");
		ImageIcon rules = new ImageIcon("src\\main\\resources\\images\\rules.png");
		ImageIcon exit = new ImageIcon("src\\main\\resources\\images\\exit.png");
		ImageIcon arrow = new ImageIcon("src\\main\\resources\\images\\arrow.png");
		
		this.logo = logo.getImage();
		this.play = play.getImage();
		this.rules = rules.getImage();
		this.exit = exit.getImage();
		this.arrow = arrow.getImage();
		this.arrowY = 540;
	}
	
	public void keyPressed(KeyEvent keyEvent) {
		int keyCode = keyEvent.getKeyCode();
				
		if(keyCode == KeyEvent.VK_UP) {
			if(this.arrowY == 540) {
				this.arrowY = 600;
			} else if(this.arrowY == 570) {
				this.arrowY = 540;
			} else if(this.arrowY == 600) {
				this.arrowY = 570;
			}
		}
		
		if(keyCode == KeyEvent.VK_DOWN) {
			if(this.arrowY == 540) {
				this.arrowY = 570;
			} else if(this.arrowY == 570) {
				this.arrowY = 600;
			} else if(this.arrowY == 600) {
				this.arrowY = 540;
			}
		}
		
	}

	public Image getLogo() {
		return this.logo;
	}

	public Image getPlay() {
		return this.play;
	}

	public Image getRules() {
		return this.rules;
	}

	public Image getExit() {
		return this.exit;
	}

	public Image getArrow() {
		return this.arrow;
	}
	
	public int getArrowY() {
		return this.arrowY;
	}

}
