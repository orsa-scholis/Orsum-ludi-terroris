package logic;

import java.util.ArrayList;

import logic.algorithm.PathFinder;
import logic.graph.Node;

public class Game {
	private QuadController quadController;
	private PathFinder pathFinder;

	/**
	 * Game constructor
	 * @param field	Represents the field. A 0 represents an empty space, while 1 represents an obstacle. 
	 * The origin ((0, 0)-Index) is in the bottom left corner
	 */
	public Game(int[][] field) {
		super();
		
		int width = field[0].length;
		int height = field.length;
		this.quadController = new QuadController(width, height);
		
		for (Quad quad : quadController.getQuads()) {
			int isObstacle = field[quad.getIndex().getY()][quad.getIndex().getX()];
			quad.setObstacle(isObstacle == 1);
		}
		
		setup();
	}
	
	protected void setup() {
		pathFinder = new PathFinder(quadController.getGraphNodesWithObstacles());
		
		System.out.println(pathFinder.getGraph().toString(quadController.getDWidth()));
	}
	
	public void getPathForMonster() {
		
	}

	public QuadController getQuadController() {
		return quadController;
	}
}
