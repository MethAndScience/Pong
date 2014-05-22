package com.sniperzciinema.Levels.Levels;

import java.util.Random;

import com.sniperzciinema.Levels.Level;

public class Triangle extends Level {

	private int x = 0;

	public Triangle() {

		// Create a large triangle with bricks, Making half of them the harder
		// bricks
		// J = Y
		// I = X
		for (int j = 5; j != 16; j++) {
			for (int i = 3 + x; i != 25 - x; i++) {

				Random r = new Random();
				addBrick(i, j, r.nextInt(2) + 1);
			}
			x++;
		}
	}

}