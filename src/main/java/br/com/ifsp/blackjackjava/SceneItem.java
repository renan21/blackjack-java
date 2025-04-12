package br.com.ifsp.blackjackjava;

import java.awt.Image;

import javax.swing.ImageIcon;

public class SceneItem {
	
	private Image image;
	private int xPostion;	
	private int yPostion;
<<<<<<< HEAD

	public SceneItem(int defaultXPostion, int defaultYPostion, String imagePath) {
		this.xPostion = defaultXPostion;
		this.yPostion = defaultYPostion;
		ImageIcon imageIcon = new ImageIcon(imagePath);
=======
	
	private final String extension = ".png";
	private final String path = "src\\main\\resources\\images\\";
	
	public SceneItem(int defaultXPostion, int defaultYPostion, String itemName) {
		this.xPostion = defaultXPostion;
		this.yPostion = defaultYPostion;
		
		String finalName = path + itemName + extension;
		
		ImageIcon imageIcon = new ImageIcon(finalName);
>>>>>>> release/refactoring
		this.image = imageIcon.getImage();
	}
		
	public Image getImage() {
		return image;
	}
	
	public int getXPosition() {
		return xPostion;
	}
	
	public int getYPosition() {
		return yPostion;
	}

	public void updateYPosition(int yPosition) {
		this.yPostion = yPosition;
	}

	public void updateXPosition(int xPosition) {
		this.xPostion = xPosition;		
	}
<<<<<<< HEAD
}
=======
	
}
>>>>>>> release/refactoring
