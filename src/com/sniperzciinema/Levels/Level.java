package com.sniperzciinema.Levels;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.sniperzciinema.Game;
import com.sniperzciinema.Objects.Brick;

public abstract class Level {

	private ArrayList<Brick> bricks;

	public Level() {
		this.bricks = new ArrayList<Brick>();
	}

	/**
	 * Add a brick to the level
	 * 
	 * @param brick
	 */
	public void addBrick(Brick brick) {
		if (!collision(brick.getBlock()))
			bricks.add(brick);
	}

	/**
	 * Add a brick to the level's grid; Increase x and y represent the space of
	 * one brick
	 * 
	 * @param x
	 * @param y
	 * @param level
	 *            - How many times you have to hit the brick
	 */
	public void addBrick(int x, int y, int level) {
		Brick brick = new Brick(x, y, level);
		addBrick(brick);
	}

	/**
	 * If the brick is broken remove it; Else just lower it's lives
	 * 
	 * @param brick
	 */
	public void breakBrick(Brick brick) {
		if (brick.getLives() == 1) {
			bricks.remove(brick);
			Game.getPowerupHandler().dropPowerup(brick.getX(), brick.getY());
		} else
			brick.setLives(brick.getLives() - 1);
	}

	/**
	 * 
	 * @param ball
	 * @return if the ball has collided with any bricks
	 */
	public boolean collision(Rectangle ball) {
		for (Brick brick : getBricks())
			if (brick.getBlock().intersects(ball))
				return true;
		return false;

	}

	/**
	 * Get the brick at specific x and y co-ordinates
	 * 
	 * @param x
	 * @param y
	 * @return the brick(or null)
	 */
	public Brick getBrick(int x, int y) {
		Rectangle ball = new Rectangle(x + 1, y + 1, 1, 1);
		return getBrick(ball);
	}

	/**
	 * 
	 * @param ball
	 * @return brick that intersects with the ball
	 */
	public Brick getBrick(Rectangle ball) {
		for (Brick brick : getBricks())
			if (brick.getBlock().intersects(ball))
				return brick;
		return null;
	}

	/**
	 * 
	 * @return the list of bricks
	 */
	public List<Brick> getBricks() {
		return bricks;
	}

	/**
	 * 
	 * @return if all bricks have been broken
	 */
	public boolean isFinished() {
		return bricks.isEmpty();
	}

	/**
	 * Paint all the bricks on the panel
	 * 
	 * @param g
	 */
	public void paint(Graphics2D g) {
		if (!getBricks().isEmpty())
			for (Brick brick : getBricks()) {
				brick.paint(g);
			}
	}

}
