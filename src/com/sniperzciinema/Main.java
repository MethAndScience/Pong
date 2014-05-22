package com.sniperzciinema;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {

	// Initialize all the variables
	public static JFrame frame;
	public static int width = 1200;
	public static int height = 700;
	public static int sideBarWidth = 200;
	public static int sideBarHeight = 700;

	public static Game game;
	public static Image brickImage1;
	public static Image brickImage2;

	// When the program starts; load all the images
	public static void main(String[] args) {
		ImageIcon b1 = new ImageIcon(Main.class.getResource("/com/sniperzciinema/Image/Brick1.jpg"));
		brickImage1 = b1.getImage();
		ImageIcon b2 = new ImageIcon(Main.class.getResource("/com/sniperzciinema/Image/Brick2.jpg"));
		brickImage2 = b2.getImage();

		// Create a frame to hold the game in
		frame = new JFrame("[-- Pong --]");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game = new Game();

		game.setIcon(new ImageIcon(Main.class.getResource("/com/sniperzciinema/Image/Background.jpg")));
		frame.getContentPane().add(game);
		frame.setSize(width, height);
		frame.setVisible(true);

		// Start the game
		game.restart();
	}

}