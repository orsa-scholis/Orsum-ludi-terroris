package main;

import logic.*;

public class Main {
	public static void main(String[] args) {
		int[][] field = new int[][] {
			// Bottom
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 1, 0, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }
			// Top
		};

		Game game = new Game(field);
		System.out.println(game.getQuadController().toStringWithIndices());
		game.getPathForMonster();
		game.export("debug/export.txt");
	}
}
