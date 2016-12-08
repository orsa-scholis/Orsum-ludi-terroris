package main;

import logic.*;
import logic.algorithm.Path;
import logic.graph.Point;

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
		game.setMonsterPosition(new Point(0.85, 0.85));
		System.out.println(game.getQuadController().toStringWithIndices());
		Path path = game.getPathForMonster();
		System.out.println("Path: ");
		System.out.println(path);
		game.export("debug/export.txt");
	}
}
