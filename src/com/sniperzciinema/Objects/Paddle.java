package com.sniperzciinema.Objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import com.sniperzciinema.Game;
import com.sniperzciinema.Main;
import com.sniperzciinema.Powerups.Powerup;

public class Paddle {
	private int y;
	private static int width = 60;
	private static final int height = 10;

	private int x;
	private int moveX;
	private Image image;
	private Game game;

	public Paddle(Game game) {
		this.game = game;
		this.y = Main.height - 80;
		this.x = ((Main.width - Main.sideBarWidth) / 2) - (width / 2);
		this.moveX = 0;

		ImageIcon i = new ImageIcon(Main.class.getResource("/com/sniperzciinema/Image/Paddle.png"));
		image = i.getImage();
	}

	/**
	 * @return the area of the paddle
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	/**
	 * 
	 * @return the direction of the paddle;
	 *         <p>
	 *         - 1 means left
	 *         <p>
	 *         - -1 mean right
	 */
	public int getDirection() {
		return moveX / 2;
	}

	/**
	 * 
	 * @return the y location
	 */
	public int getTop() {
		return y;
	}

	/**
	 * @return the top area of the paddle
	 */
	public Rectangle getTopBounds() {
		return new Rectangle(x, y, width, 1);
	}

	/**
	 * 
	 * @return the width of the paddle
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 
	 * @return the x location
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return if the paddle is moving
	 */
	public boolean isMoving() {
		return moveX != 0;
	}

	/**
	 * When a key is pressed move moveX accordingly
	 * 
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			moveLeft();
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			moveRight();

		// If the ball is not shot, then we'll listen to these keys
		if (!game.getBall().isShot()) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				moveX = 0;
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
				game.getBall().shoot();
				moveX = 0;
			}
		}
	}

	/**
	 * When a key is released we'll want to set moveX to 0
	 * 
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		if (game.getBall().isShot())
			moveX = 0;
	}

	/**
	 * Handle moving the paddle
	 */
	public void move() {

		if (game.getBall().isShot()) {
			if (x + moveX > 0 && x + moveX < Main.width - Main.sideBarWidth - width)
				x = x + moveX;

			// If the paddle hits a powerup, activate the powerup
			if (Game.powerupHandler.colide()) {
				Powerup powerup = Game.powerupHandler.getPowerup(getBounds());
				powerup.activate();
			}

		}

	}

	/**
	 * Move moveX to make the paddle go left
	 */
	public void moveLeft() {
		moveX = -2;
	}

	/**
	 * Move moveX to make the paddle go right
	 */
	public void moveRight() {
		moveX = 2;
	}

	/**
	 * Paint the paddle on the board;
	 * <p>
	 * And if the ball isn't show yet, draw arrows to show which way the ball
	 * will shoot
	 * 
	 * @param g
	 */
	public void paint(Graphics2D g) {
		if (image != null)
			g.drawImage(image, x, y, width, height, null);
		else
			g.fillRect(x, y, width, height);

		// If the ball isn't show yet
		if (game.getBall() != null && !game.getBall().isShot())
			if (moveX < 0)
				g.drawLine(x + width / 2, y, (x + width / 2) - 50, y - 50);

			else if (moveX > 0)
				g.drawLine(x + width / 2, y, (x + width / 2) + 50, y - 50);

			else
				g.drawLine(x + width / 2, y, (x + width / 2), y - 50);
	}

	/**
	 * Set the paddle's width
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		Paddle.width = width;
	}

}