package com.sniperzciinema.Objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.sniperzciinema.Game;
import com.sniperzciinema.Main;
import com.sniperzciinema.Levels.LevelHandler;
import com.sniperzciinema.Powerups.PowerupHandler;

public class Ball {
	private static final int diameter = 10;
	private int x = 0;
	private int y = 0;
	private int moveX = 1;
	private int moveY = 1;
	private Game game;
	private Image ball;
	private Image ballMagic;
	private LevelHandler levelHandler;
	private boolean shot;

	public Ball(Game game) {
		this.game = game;
		this.y = game.getPaddle().getTop() - diameter;
		this.x = game.getPaddle().getX() - (diameter / 2) + game.getPaddle().getWidth() / 2;
		this.moveX = 0;
		this.moveY = -1;
		this.shot = false;
		this.levelHandler = Game.getLevelHandler();
		ImageIcon i = new ImageIcon(Main.class.getResource("/com/sniperzciinema/Image/Ball.png"));
		ball = i.getImage();
		ImageIcon i2 = new ImageIcon(Main.class.getResource("/com/sniperzciinema/Image/MagicBall.png"));
		ballMagic = i2.getImage();
	}

	/**
	 * 
	 * @param brick
	 * @param ball
	 * @return did the ball collide with the bottom of the brick
	 */
	public boolean collisionBrickBottom(Brick brick, Rectangle ball) {
		return ball.intersects(brick.getBottom());
	}

	/**
	 * 
	 * @param brick
	 * @param ball
	 * @return did the ball collide with the left side of the brick
	 */
	public boolean collisionBrickLeft(Brick brick, Rectangle ball) {
		return ball.intersects(brick.getLeft());
	}

	/**
	 * 
	 * @param brick
	 * @param ball
	 * @return did the ball collide with the right side of the brick
	 */
	public boolean collisionBrickRight(Brick brick, Rectangle ball) {
		return ball.intersects(brick.getRight());
	}

	/**
	 * 
	 * @param brick
	 * @param ball
	 * @return did the ball collide with the top of the brick
	 */
	public boolean collisionBrickTop(Brick brick, Rectangle ball) {
		return ball.intersects(brick.getTop());
	}

	/**
	 * 
	 * @return did the ball collide with the paddle
	 */
	private boolean collisionPaddle() {
		return game.getPaddle().getTopBounds().intersects(getBounds());
	}

	/**
	 * If the players lives are 1, it means the player died; Else just remove a
	 * life from the player and reset the objects
	 */
	public void die() {
		if (game.getLives() == 1)
			game.gameOver();
		else {
			game.setLives(game.getLives() - 1);
			game.reset();
		}
	}

	/**
	 * 
	 * @return the area of the ball
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, diameter, diameter);
	}

	/**
	 * 
	 * @return the regular image of the ball
	 */
	public Image getImage() {
		return ball;
	}

	/**
	 * 
	 * @return if the ball has been shot yet
	 */
	public boolean isShot() {
		return shot;
	}

	/**
	 * Move the ball depending on what it's about to hit
	 */
	public void move() {

		// Make sure the level isn't done yet
		if (levelHandler.getLevelObject(levelHandler.getLevel()).isFinished()) {
			Game.getLevelHandler();
			if (Game.getLevelHandler().isLevel(levelHandler.getLevel() + 1)) {
				Game.getLevelHandler().setLevel(levelHandler.getLevel() + 1);
				game.reset();
			} else {
				game.gameOver();
			}
		}
		// If the ball has been shot we'll allow the ball to move by physics
		if (shot) {

			// If the ball is going to collide with the left side of the frame
			if (x + moveX < 0)
				moveRight();
			// If the ball is going to collide with the right side of the frame
			if (x + moveX > Main.width - Main.sideBarWidth - diameter)
				moveLeft();

			// If the ball is going to collide with the top of the frame
			if (y + moveY < 0)
				moveDown();

			// If the ball is going to collide with the bottom of the frame
			if (y + moveY > game.getHeight())
				if (!Game.getPowerupHandler().isSafetynet())
					die();
				else {
					moveUp();
					PowerupHandler.getActivatedPowerups().get("Red").deactivate();
				}

			// If the ball has collided with any bricks
			if (levelHandler.getLevelObject(levelHandler.getLevel()).collision(getBounds())) {
				Brick brick = levelHandler.getLevelObject(levelHandler.getLevel()).getBrick(getBounds());

				// If the ball is magic, we're not going to bounce the ball
				// away, so we just let it fly through
				if (!Game.getPowerupHandler().isMagicball()) {

					if (collisionBrickBottom(brick, getBounds())) {
						moveDown();
					} else if (collisionBrickTop(brick, getBounds())) {
						moveUp();
					}
					if (collisionBrickLeft(brick, getBounds())) {
						moveLeft();
					} else if (collisionBrickRight(brick, getBounds())) {
						moveRight();
					}
				}

				// Break the brick and raise the players score
				levelHandler.getLevelObject(levelHandler.getLevel()).breakBrick(brick);
				game.setScore(game.getScore() + 20);
			}

			// If the ball has colided with the paddle
			// TODO: Fix the physics of this collision using the angle the ball
			// hits at
			if (collisionPaddle()) {
				moveUp();

				// If the paddle is moving, change the direction of the ball to
				// go with it
				if (game.getPaddle().isMoving())
					moveX = game.getPaddle().getDirection();

				// Don't let the ball glitch out inside the paddle
				y = game.getPaddle().getTop() - diameter;

				// Add 2 to the score
				game.setScore(game.getScore() + 2);
			}

			// Move the x and y accordingly
			x = x + moveX;
			y = y + moveY;

		}
	}

	/**
	 * Move the moveY to make the ball go down
	 */
	private void moveDown() {
		moveY = 1;
	}

	/**
	 * Move the moveX to make the ball go left
	 */
	private void moveLeft() {
		moveX = -1;
	}

	/**
	 * Move the moveX to make the ball go right
	 */
	private void moveRight() {
		moveX = 1;
	}

	/**
	 * Move the moveY to make the ball go up
	 */
	private void moveUp() {
		moveY = -1;
	}

	/**
	 * Paint the ball on the panel depending on if it's a magic ball or not
	 * 
	 * @param g
	 */
	public void paint(Graphics2D g) {
		if (!Game.getPowerupHandler().isMagicball())
			g.drawImage(ball, x, y, diameter, diameter, null);
		else
			g.drawImage(ballMagic, x, y, diameter, diameter, null);
	}

	/**
	 * Shoot the ball(Only used at reset to choose where to throw the ball)
	 */
	public void shoot() {
		moveX = game.getPaddle().getDirection();
		shot = true;
	}
}
