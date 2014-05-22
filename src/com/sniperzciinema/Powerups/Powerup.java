package com.sniperzciinema.Powerups;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import com.sniperzciinema.Main;

public abstract class Powerup {

	// The timer class
	class DeActivateTask extends TimerTask {
		@Override
		public void run() {
			deactivate();
		}
	}

	private Image image;
	private long time;
	private int y, x;
	private boolean hide;
	private Timer timer;
	private int id;

	private boolean move;

	public Powerup(int id, String imagePath, long time, int x, int y) {
		this.id = id;
		ImageIcon i = new ImageIcon(Main.class.getResource(imagePath));
		image = i.getImage();
		this.time = time;
		this.y = y;
		this.x = x;
	}

	/**
	 * When a powerup is activated, check if it's already activated, and if so
	 * just set the time of the current powerup Otherwise it activates a new
	 * powerup and starts the timer
	 * <p>
	 * Either way causes the powerup picture to hide
	 */
	public void activate() {
		if (PowerupHandler.getActivatedPowerups().containsKey(id)) {
			PowerupHandler.getActivatedPowerups().get(id).setTime(time);
		} else {
			PowerupHandler.getActivatedPowerups().put(id, this);
			activation();
			startTimer();
		}
		hide();
	}

	/**
	 * When creating a class that extends Powerup, it contains it's own code to
	 * do on activation
	 */
	public abstract void activation();

	/**
	 * When a powerup is deactivated, we want to remove it's id from the map,
	 * and run the deactivation code
	 */
	public void deactivate() {
		deactivation();
		timer.cancel();
		PowerupHandler.getActivatedPowerups().remove(id);
	}

	/**
	 * When creating a class that extends Powerup, it contains it's own code to
	 * do on deactivation
	 */
	public abstract void deactivation();

	/**
	 * Draw the powerup
	 * 
	 * @param g
	 */
	public void draw(Graphics2D g) {
		if (!hide)
			g.drawImage(image, x, y, null);
	}

	/**
	 * 
	 * @return the powerups area
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, 20, 8);
	}

	/**
	 * 
	 * @return the y location of the powerup
	 */
	public int getY() {
		return y;
	}

	/**
	 * Hide the powerup so it doesn't get drawn with the rest
	 */
	private void hide() {
		hide = true;
	}

	/**
	 * Move the powerup down every other time this method gets called(This way
	 * it falls at half the speed the ball moves)
	 */
	public void move() {
		move = !move;
		if (move)
			this.y++;
	}

	/**
	 * Set the time the powerup has left
	 * 
	 * @param time
	 */
	public void setTime(long time) {
		this.time = time;
	}

	/**
	 * Start the timer
	 */
	public void startTimer() {
		timer = new Timer();
		timer.schedule(new DeActivateTask(), time * 1000);

	}
}
