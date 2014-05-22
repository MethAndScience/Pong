package com.sniperzciinema.Powerups.Powerups;

import com.sniperzciinema.Game;
import com.sniperzciinema.Powerups.Powerup;

public class BluePowerup extends Powerup {

	private static String name = "Blue";
	private static int time = 10;

	public BluePowerup(int x, int y) {
		super(name, "/com/sniperzciinema/Image/BluePowerup.png", time, x, y);
	}

	/**
	 * Turn on the magic ball
	 */
	@Override
	public void activation() {
		Game.powerupHandler.setMagicball(true);
	}

	/**
	 * Turn off the magic ball
	 */
	@Override
	public void deactivation() {
		Game.powerupHandler.setMagicball(false);
	}

}
