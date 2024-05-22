package br.com.ifsp.blackjackjava;

import java.awt.Image;

import javax.swing.ImageIcon;

public class SceneItem {
	
	private Image image;
	private int xPostion;	
	private int yPostion;


	public SceneItem(int defaultXPostion, int defaultYPostion, String imagePath) {
		this.xPostion = defaultXPostion;
		this.yPostion = defaultYPostion;		
		
		ImageIcon imageIcon = new ImageIcon(imagePath);
		this.image = imageIcon.getImage();
	}
		
	public Image getImage() {
		return image;
	}
	
	public int getDefaultXPostion() {
		return xPostion;
	}
	
	public int getDefaultYPostion() {
		return yPostion;
	}

	public void updateYPostition(int yPosition) {
		this.yPostion = yPosition;
	}
	
}
