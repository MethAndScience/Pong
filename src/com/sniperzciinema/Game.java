package com.sniperzciinema;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.sniperzciinema.Levels.LevelHandler;
import com.sniperzciinema.Objects.Ball;
import com.sniperzciinema.Objects.Paddle;
import com.sniperzciinema.Powerups.Powerup;
import com.sniperzciinema.Powerups.PowerupHandler;

@SuppressWarnings("serial")
public class Game extends JLabel {

	public static boolean playing = false;

	private Ball ball;
	private Paddle paddle;
	private Sidebar sidebar;
	public static LevelHandler levelHandler;
	public static PowerupHandler powerupHandler;

	private int score;
	private int lives;

	/**
	 * 
	 * @return the powerup handler
	 */
	public static PowerupHandler getPowerupHandler() {
		return powerupHandler;
	}

	public Game() {

		// Set the *Needed* variables and configuration
		setLayout(null);
		sidebar = new Sidebar(this);
		this.lives = 3;
		this.score = 0;

		// Tell the program to listen to key presses
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

				// Escape pauses the game
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if (isPaused())
						resume();
					else
						pause();
				} else

					// If it's not escape, let the paddle sort it out
					paddle.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {

				// Let the paddle sort out keys being released
				paddle.keyReleased(e);
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

		// Focus the window on this pannel, so pressing keys will actually do
		// something
		setFocusable(true);
	}

	/**
	 * End the game
	 */
	public void gameOver() {

		// On a game over, stop the game and ask if they would like to play
		// again
		pause();
		int confirm = JOptionPane.showOptionDialog(Main.frame, "Play Again?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		// If their answer is yes, start a new game
		if (confirm == JOptionPane.YES_OPTION) {
			restart();
		} else {
			System.exit(ABORT);
		}
	}

	/**
	 * 
	 * @return the ball
	 */
	public Ball getBall() {
		return ball;
	}

	/**
	 * 
	 * @return the levelHandler
	 */
	public static LevelHandler getLevelHandler() {
		return levelHandler;
	}

	/**
	 * 
	 * @return the lives left
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * 
	 * @return the paddle
	 */
	public Paddle getPaddle() {
		return paddle;
	}

	/**
	 * 
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * 
	 * @return if game is paused
	 */
	public boolean isPaused() {
		return !playing;
	}

	/**
	 * Paint all the graphics on the screen
	 */
	@Override
	public void paint(Graphics g) {

		// Erase all the current graphics
		super.paint(g);

		// Create new graphics
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		levelHandler.getLevelObject(levelHandler.getLevel()).paint(g2d);
		if (ball != null)
			ball.paint(g2d);
		if (paddle != null)
			paddle.paint(g2d);

		powerupHandler.drawAll(g2d);
		sidebar.paintLives(g2d);
	}

	/**
	 * Pause the game
	 * <p>
	 * Currently skips to next level
	 * 
	 */
	public void pause() {
		if (levelHandler.isLevel(levelHandler.getLevel() + 1))
			levelHandler.setLevel(levelHandler.getLevel() + 1);
		else
			levelHandler.setLevel(1);
		// playing = false;
	}

	/**
	 * Reset everything
	 */
	public void reset() {
		resetPowerups();
		resetPaddle();
		resetBall();
	}

	/**
	 * Reset the ball
	 */
	private void resetBall() {
		ball = new Ball(this);
	}

	/**
	 * Reset the paddle
	 */
	private void resetPaddle() {
		paddle = new Paddle(this);

	}

	/**
	 * Reset the powerups
	 * <p>
	 * - Deactivate them all
	 * <p>
	 * - Reset PowerupHandler
	 */
	private void resetPowerups() {
		if (!PowerupHandler.getActivatedPowerups().isEmpty())
			for (Entry<Integer, Powerup> e : PowerupHandler.getActivatedPowerups().entrySet()) {
				e.getValue().deactivate();
			}
		powerupHandler = new PowerupHandler();
	}

	/**
	 * Reset everything to where we are ready to play from the start again
	 */
	public void restart() {

		levelHandler = new LevelHandler();
		levelHandler.setLevel(1);
		reset();
		setScore(0);
		setLives(3);
		resume();
	}

	/**
	 * Main game loop
	 */
	public void resume() {
		playing = true;
		while (playing) {
			update();
		}
	}

	/**
	 * Set the lives left
	 * 
	 * @param lives
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}

	/**
	 * Set the score
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Updates/Moves all mechanics involoved in the game
	 */
	public void update() {
		ball.move();
		paddle.move();
		sidebar.update();
		powerupHandler.moveAll();

		// Repaint the graphics(Calls "void paint(Graphics g);")
		repaint();

		// Get the thread to sleep for 4 milleseconds
		try {
			Thread.sleep(4);
		} catch (InterruptedException e) {
			System.out.println("No Thread?");
		}
	}
}
