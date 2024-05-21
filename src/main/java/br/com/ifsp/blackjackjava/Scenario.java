package br.com.ifsp.blackjackjava;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Scenario extends JPanel implements ActionListener {
	
	private Image background;
	private MainMenu mainMenu;
	private Timer timer;
	
	public Scenario() {
		setFocusable(true);
		setDoubleBuffered(true);
		
		ImageIcon imageIcon = new ImageIcon("src\\main\\resources\\images\\background.png");
		this.background = imageIcon.getImage();
		
		mainMenu = new MainMenu();
		mainMenu.load();
		
		addKeyListener(new KeyboardAdapter());
		
		this.timer = new Timer(5, this);
		this.timer.start();

	}
	
	@Override
	public void paint(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D) graphics;
		graphics2d.drawImage(background, 0, 0, null);
		graphics2d.drawImage(mainMenu.getLogo(), 300, 80, this);
		graphics2d.drawImage(mainMenu.getPlay(), 450, 540, this);		
		graphics2d.drawImage(mainMenu.getRules(), 442, 570, this);
		graphics2d.drawImage(mainMenu.getExit(), 452, 600, this);
		graphics2d.drawImage(mainMenu.getArrow(), 400, mainMenu.getArrowY(), this);
		graphics.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	private class KeyboardAdapter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			mainMenu.keyPressed(e);
		}
		
	}

}
