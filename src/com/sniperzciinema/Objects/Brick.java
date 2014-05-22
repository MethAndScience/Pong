package com.sniperzciinema.Objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.sniperzciinema.Main;

public class Brick {

	private int width = 35;
	private int height = 15;
	private int x, y;

	private int lives;

	public Brick(int x, int y, int lives) {
		this.x = x * width + 5;
		this.y = y * height + 5;
		this.lives = lives;

	}

	/**
	 * 
	 * @return the bricks area
	 */
	public Rectangle getBlock() {
		return new Rectangle(x, y, width, height);
	}

	/**
	 * 
	 * @return the bottom of the brick
	 */
	public Rectangle getBottom() {
		return new Rectangle(x, y + height - 1, width, 1);
	}

	/**
	 * 
	 * @return the left side of the brick
	 */
	public Rectangle getLeft() {
		return new Rectangle(x, y, 1, height);
	}

	/**
	 * 
	 * @return the players lives
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * 
	 * @return the right side of the brick
	 */
	public Rectangle getRight() {
		return new Rectangle(x + width - 1, y, 1, height);
	}

	/**
	 * 
	 * @return the top of the brick
	 */
	public Rectangle getTop() {
		return new Rectangle(x, y, width, 1);
	}

	/**
	 * 
	 * @return the x location of the brick
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return the y location of the brick
	 */
	public int getY() {
		return y;
	}

	/**
	 * Paint the brick on the panel
	 * 
	 * @param g
	 */
	public void paint(Graphics2D g) {
		if (lives == 1)
			g.drawImage(Main.brickImage1, x, y, width, height, null);
		if (lives == 2)
			g.drawImage(Main.brickImage2, x, y, width, height, null);
	}

	/**
	 * Set the bricks lives
	 * 
	 * @param lives
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}
}
