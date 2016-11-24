package main;

import logic.graph.*;
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
		
		Point point1 = new Point(1.0/8.0, 3.0/8.0);
		Point point2 = new Point(4.0/8.0, 1.0/8.0);
		boolean hasObsti = game.getQuadController().testLineForObstacles(point1, point2);
		System.out.println("("+game.getQuadController().indexForPoint(point1).getX()+","
				+game.getQuadController().indexForPoint(point1).getY()+") / ("
				+game.getQuadController().indexForPoint(point2).getX()+","
				+game.getQuadController().indexForPoint(point2).getY()+") "
				+(hasObsti ? "has" : "hasn't") + " got an obstacle");
	}
}
