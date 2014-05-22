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
			if (timeleft != 0)
				timeleft--;
			else
				deactivate();
		}
	}

	private Image image;
	private long time;
	private long timeleft;
	private int y, x;
	private boolean hide;
	private Timer timer;
	private String name;

	private boolean move;

	public Powerup(String name, String imagePath, long time, int x, int y) {
		this.name = name;
		ImageIcon i = new ImageIcon(Main.class.getResource(imagePath));
		image = i.getImage();
		this.time = time;
		this.y = y;
		this.x = x;
	}

	/**
	 * @return the image
	 */
	public Image getImage(){
		return this.image;
	}
	/**
	 * When a powerup is activated, check if it's already activated, and if so
	 * just set the time of the current powerup Otherwise it activates a new
	 * powerup and starts the timer
	 * <p>
	 * Either way causes the powerup picture to hide
	 */
	public void activate() {
		if (PowerupHandler.getActivatedPowerups().containsKey(name)) {
			PowerupHandler.getActivatedPowerups().get(name).setTimeLeft(time);
		} else {
			PowerupHandler.getActivatedPowerups().put(name, this);
			activation();
			startTimer();
			timeleft = time;
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
		PowerupHandler.getActivatedPowerups().remove(name);
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
	 * @return the time left for this powerup
	 */
	public long getTimeLeft() {
		return this.timeleft;
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
	 * Set the time left for the powerup
	 * 
	 * @param i
	 */
	public void setTimeLeft(long i) {
		this.timeleft = i;
	}

	/**
	 * Start the timer
	 */
	public void startTimer() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new DeActivateTask(), 0, 1 * 1000);

	}
}
