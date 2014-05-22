package com.sniperzciinema;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import com.sniperzciinema.Powerups.PowerupHandler;

public class Sidebar {
	
	//Create variables
	private Game game;
	private JLabel score;
	private JLabel lives;
	private JLabel greenTime;
	private JLabel blueTime;
	private JLabel redTime;

	public Sidebar(Game game) {
		
		//Set up all the variables
		this.game = game;

		score = new JLabel("Score: 0");
		score.setForeground(Color.GREEN);
		score.setBounds(Main.width - 150, 20, 100, 100);

		lives = new JLabel("Lives:");
		lives.setForeground(Color.WHITE);
		lives.setBounds(Main.width - 175, Main.height - 100, 100, 100);

		greenTime = new JLabel();
		greenTime.setFont(new Font("Serif", Font.BOLD, 20));
		greenTime.setForeground(Color.GREEN);
		greenTime.setBounds(Main.width - 150, 195, 20, 20);
		
		redTime = new JLabel();
		redTime.setFont(new Font("Serif", Font.BOLD, 20));
		redTime.setForeground(Color.RED);
		redTime.setBounds(Main.width - 150, 220, 20, 20);
		
		blueTime = new JLabel();
		blueTime.setFont(new Font("Serif", Font.BOLD, 20));
		blueTime.setForeground(Color.BLUE);
		blueTime.setBounds(Main.width - 150, 245, 20, 20);

		//Add components to the main panel
		game.add(score);
		game.add(lives);
		game.add(greenTime);
		game.add(redTime);
		game.add(blueTime);
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
		if(PowerupHandler.getActivatedPowerups().containsKey("Green"))
			g.drawImage(PowerupHandler.getActivatedPowerups().get("Green").getImage(), Main.width - 175, 200, null);
		if(PowerupHandler.getActivatedPowerups().containsKey("Red"))
			g.drawImage(PowerupHandler.getActivatedPowerups().get("Red").getImage(), Main.width - 175, 225, null);
		if(PowerupHandler.getActivatedPowerups().containsKey("Blue"))
			g.drawImage(PowerupHandler.getActivatedPowerups().get("Blue").getImage(), Main.width - 175, 250, null);
		
	}

	/**
	 * Update the score JLabel
	 */
	public void update() {
		score.setText("Score: " + game.getScore());
		greenTime.setText(PowerupHandler.getActivatedPowerups().containsKey("Green") ?  PowerupHandler.getActivatedPowerups().get("Green").getTimeLeft()+"" : "");
		redTime.setText(PowerupHandler.getActivatedPowerups().containsKey("Red") ?  PowerupHandler.getActivatedPowerups().get("Red").getTimeLeft()+"" : "");
		blueTime.setText(PowerupHandler.getActivatedPowerups().containsKey("Blue") ?  PowerupHandler.getActivatedPowerups().get("Blue").getTimeLeft()+"" : "");
		}

}
