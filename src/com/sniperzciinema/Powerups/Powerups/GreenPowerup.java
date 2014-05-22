package com.sniperzciinema.Powerups.Powerups;

import com.sniperzciinema.Main;
import com.sniperzciinema.Powerups.Powerup;

public class GreenPowerup extends Powerup {

	private int width;
	private static String name = "Green";
	private static int time = 20;

	public GreenPowerup(int x, int y) {
		super(name, "/com/sniperzciinema/Image/GreenPowerup.png", time, x, y);
		
		width = Main.game.getPaddle().getWidth();
	}

	/**
	 * Double the width of the paddle
	 */
	@Override
	public void activation() {
		Main.game.getPaddle().setX(Main.game.getPaddle().getX() - (width/2));
		Main.game.getPaddle().setWidth(width*2);
	}

	/**
	 * Make the paddles width return to normal
	 */
	@Override
	public void deactivation() {
		Main.game.getPaddle().setX(Main.game.getPaddle().getX() + (width/2));
		Main.game.getPaddle().setWidth(width);
	}

}
