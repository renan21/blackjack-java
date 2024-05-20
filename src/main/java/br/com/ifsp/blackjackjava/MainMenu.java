package br.com.ifsp.blackjackjava;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class MainMenu {
	
	private Image playImage;

	
	public void load() {
		ImageIcon playImage = new ImageIcon("resources\\play.png");
		this.playImage = playImage.getImage();
	}
	
	public void keyPressed(KeyEvent keyEvent) {
		int  keyCode = keyEvent.getKeyCode();
		
		if(keyCode == KeyEvent.VK_UP) {
			
		}
		
		if(keyCode == KeyEvent.VK_DOWN) {
			
		}
		
	}

	public Image getPlayImage() {
		return playImage;
	}


	
	
	

}
