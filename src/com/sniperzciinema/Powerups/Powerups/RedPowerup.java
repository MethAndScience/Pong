package com.sniperzciinema.Powerups.Powerups;

import com.sniperzciinema.Game;
import com.sniperzciinema.Powerups.Powerup;

public class RedPowerup extends Powerup {

	private static int id = 3;
	private static int time = 60;

	public RedPowerup(int x, int y) {
		super(id, "/com/sniperzciinema/Image/RedPowerup.png", time, x, y);
	}

	/**
	 * Turn on the safety net
	 */
	@Override
	public void activation() {
		Game.powerupHandler.setSafetynet(true);
	}

	/**
	 * Turn off the safetynet
	 */
	@Override
	public void deactivation() {
		Game.powerupHandler.setSafetynet(false);
	}

}
