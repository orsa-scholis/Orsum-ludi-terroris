package logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import logic.algorithm.PathFinder;
import logic.graph.Connection;
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
		pathFinder = new PathFinder(quadController.getGraphNodesWithObstacles(), quadController);
		
		System.out.println(pathFinder.getGraph().toString(quadController.getDWidth()));
	}
	
	public void getPathForMonster() {
		
	}
	
	public void export(String path) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(new File(path)));
			writer.write(getExportStr());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private String getExportStr() {
		String returnString = "{\"size\":"+quadController.getWidth()+",\"nodes\":[";
		
		ArrayList<Connection> connections = new ArrayList<>();
		for (Node node : pathFinder.getGraph().getNodes()) {
			returnString += "[" + node.getPoint().getX() + "," + node.getPoint().getY() + "],";
			
			for (Connection connection : node.getConnections()) {
				if (!connections.contains(connection)) {
					connections.add(connection);
				}
			}
		}
		returnString = returnString.substring(0, returnString.length() - 1); // remove last comma
		returnString += "],\"lines\":[";
		
		for (Connection connection : connections) {
			returnString += "[[" + connection.getStart().getPoint().getX() + "," + connection.getStart().getPoint().getY() 
						 + "],[" + connection.getEnd().getPoint().getX() + "," + connection.getEnd().getPoint().getY() + "]],";
		}
		returnString = returnString.substring(0, returnString.length() - 1) + "],"; // remove last comma
		
		returnString += "\"obstacles\":[";
		for (Quad obstacle : quadController.getObstacles()) {
			returnString += "[" + obstacle.getIndex().getX() + "," + obstacle.getIndex().getY() + "],";
		}
		returnString = returnString.substring(0, returnString.length() - 1) + "]"; // remove last comma
		
		returnString += "}";
		return returnString;
	}

	public QuadController getQuadController() {
		return quadController;
	}
}
