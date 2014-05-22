package com.sniperzciinema.Levels.Levels;

import java.util.Random;

import com.sniperzciinema.Levels.Level;

public class Square extends Level {

	public Square() {

		// Create a small square out of bricks
		for (int j = 2; j != 9; j++)
			for (int i = 2; i != 9; i++) {
				Random r = new Random();
				addBrick(i, j, r.nextInt(2) + 1);
			}
	}

}