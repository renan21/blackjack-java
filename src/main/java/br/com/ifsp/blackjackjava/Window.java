package br.com.ifsp.blackjackjava;

import javax.swing.JFrame;

public class Window extends JFrame{

	public Window() {
		add(new Scenario());
		setTitle("Blackjack Java");
		setSize(1024, 728);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}

}
