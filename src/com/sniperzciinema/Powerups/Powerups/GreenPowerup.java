package com.sniperzciinema.Powerups.Powerups;

import com.sniperzciinema.Main;
import com.sniperzciinema.Powerups.Powerup;

public class GreenPowerup extends Powerup {

	private int width;
	private static int id = 1;
	private static int time = 20;

	public GreenPowerup(int x, int y) {
		super(id, "/com/sniperzciinema/Image/GreenPowerup.png", time, x, y);
		width = Main.game.getPaddle().getWidth();
	}

	/**
	 * Double the width of the paddle
	 */
	@Override
	public void activation() {
		Main.game.getPaddle().setWidth(width + (width));
	}

	/**
	 * Make the paddles width return to normal
	 */
	@Override
	public void deactivation() {
		Main.game.getPaddle().setWidth(width);
	}

}
