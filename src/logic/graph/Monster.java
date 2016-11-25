package logic.graph;

import java.util.ArrayList;

public class Monster extends Node {

	// Methoden für den Graph
	public Monster(ArrayList<Connection> connections, Point point) {
		super(connections, point);
	}
	
	public Monster(Point point) {
		super(point);
	}
}
