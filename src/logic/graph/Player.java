package logic.graph;

import java.util.ArrayList;

public class Player extends Node {

	public Player(ArrayList<Connection> connections, Point point) {
		super(connections, point);
	}
	
	public Player(Point point) {
		super(point);
	}
	
	
}
