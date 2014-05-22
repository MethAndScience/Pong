package com.sniperzciinema;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class Sidebar {
	
	//Create variables
	private Game game;
	private JLabel score;
	private JLabel lives;

	public Sidebar(Game game) {
		
		//Set up all the variables
		this.game = game;

		score = new JLabel("Score: 0");
		score.setForeground(Color.GREEN);
		score.setBounds(Main.width - 150, 20, 100, 100);

		lives = new JLabel("Lives:");
		lives.setForeground(Color.WHITE);
		lives.setBounds(Main.width - 175, Main.height - 100, 100, 100);

		//Add components to the main panel
		game.add(score);
		game.add(lives);
	}

	/**
	 * Draw the lives back on the board
	 * 
	 * @param g - Graphics2D
	 */
	public void paintLives(Graphics2D g) {
		if (game.getBall() != null)
			for (int i = 0; i != game.getLives(); i++) {
				g.drawImage(game.getBall().getImage(), Main.width - 125 + (i * 25), Main.height - 60, 15, 15, null);
			}
	}

	/**
	 * Update the score JLabel
	 */
	public void update() {
		score.setText("Score: " + game.getScore());
	}

}
