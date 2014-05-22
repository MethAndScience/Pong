package com.sniperzciinema.Levels;

import java.util.ArrayList;

import com.sniperzciinema.Main;
import com.sniperzciinema.Levels.Levels.Square;
import com.sniperzciinema.Levels.Levels.Triangle;

public class LevelHandler {

	public static ArrayList<Level> levels;
	private static int level;

	public LevelHandler() {
		LevelHandler.levels = new ArrayList<Level>();

		levels.add(new Triangle());
		levels.add(new Square());
	}

	/**
	 * 
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * 
	 * @param level
	 * @return the level object
	 */
	public Level getLevelObject(int level) {
		return levels.get(level - 1);
	}

	/**
	 * 
	 * @param level
	 * @return if the level # exists
	 */
	public boolean isLevel(int level) {
		return levels.size() >= level;
	}

	/**
	 * Set the current level
	 * 
	 * @param level
	 */
	public void setLevel(int level) {
		Main.frame.setTitle("[-- Pong -- Level: " + level + " --]");
		LevelHandler.level = level;
	}

}
