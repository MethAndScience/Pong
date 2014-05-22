package com.sniperzciinema.Powerups;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;

import com.sniperzciinema.Main;
import com.sniperzciinema.Powerups.Powerups.BluePowerup;
import com.sniperzciinema.Powerups.Powerups.RedPowerup;

public class PowerupHandler {

	private ArrayList<Powerup> powerups;
	private long lastDrop;
	private Random r;
	private Image net;
	private boolean magicball;
	private boolean safetynet;
	
	private static HashMap<Integer, Powerup> activatedPowerups = new HashMap<Integer, Powerup>();

	public static HashMap<Integer, Powerup> getActivatedPowerups() {
		return activatedPowerups;
	}

	public PowerupHandler() {
		r = new Random(System.currentTimeMillis());
		powerups = new ArrayList<Powerup>();
		ImageIcon i = new ImageIcon(Main.class.getResource("/com/sniperzciinema/Image/Paddle.png"));
		net = i.getImage();
	}

	/**
	 * 
	 * @return if there's a collision with the paddle and a powerup
	 */
	public boolean colide() {
		for (Powerup powerup : getPowerups())
			if (Main.game.getPaddle().getBounds().intersects(powerup.getBounds()))
				return true;
		return false;
	}

	public void drawAll(Graphics2D g) {
		for (Powerup powerup : getPowerups()) {
			powerup.draw(g);
		}
		if(isSafetynet())
			g.drawImage(net, 0, Main.height-40, Main.width-Main.sideBarWidth, 10, null);
	}

	/**
	 * This method gets called when ever a brick breaks;
	 * <p>
	 * - The powerup will only be spawned if one wasn't created within 2 seconds
	 * ago
	 * <p>
	 * - Theres only a 50% Chance it'll spawn
	 * <p>
	 * - It's Random which one will spawn
	 * 
	 * @param x
	 * @param y
	 */
	public void dropPowerup(int x, int y) {
		if ((System.currentTimeMillis() / 1000) - (lastDrop / 1000) > 2) {
			int i = r.nextInt(3);
			if (i == 1) {
				i = r.nextInt(3);
				if (i == 0)
					getPowerups().add(new BluePowerup(x, y));
				else if (i == 1)
					getPowerups().add(new BluePowerup(x, y));
				else if (i == 2)
					getPowerups().add(new RedPowerup(x, y));
				
				lastDrop = System.currentTimeMillis();
			}
		}
	}

	/**
	 * 
	 * @param rec
	 * @return the powerup that is within the area
	 */
	public Powerup getPowerup(Rectangle rec) {
		for (Powerup powerup : getPowerups())
			if (powerup.getBounds().intersects(rec))
				return powerup;
		return null;
	}

	/**
	 * @return A list of all the powerups that are falling
	 */
	public ArrayList<Powerup> getPowerups() {
		return powerups;
	}

	/**
	 * Move all the powerups down(Powerups will only move every other time this
	 * function is called)
	 * <p>
	 * If a powerup is off the screen, it gets added to a list; then that list
	 * is removed from the initial powerups list; We can't remove well looping
	 * through them...
	 */
	public void moveAll() {
		ArrayList<Powerup> remove = new ArrayList<Powerup>();
		for (Powerup powerup : getPowerups()) {
			powerup.move();
			if (powerup.getY() > Main.height)
				remove.add(powerup);
		}
		for (Powerup powerup : remove)
			getPowerups().remove(powerup);
	}

	public boolean isMagicball() {
		return magicball;
	}

	public void setMagicball(boolean magicball) {
		this.magicball = magicball;
	}

	public boolean isSafetynet() {
		return safetynet;
	}

	public void setSafetynet(boolean safetynet) {
		this.safetynet = safetynet;
	}
}
