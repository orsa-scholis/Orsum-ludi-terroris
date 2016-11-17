package logic;

public class Game {
	private QuadController quadController;

	/**
	 * Game constructor
	 * @param field	Represents the field. A 0 represents an empty space, while 1 represents an obstacle
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
	}

	public QuadController getQuadController() {
		return quadController;
	}
	
}
