package logic.algorithm;

import java.util.ArrayList;

import logic.graph.Graph;
import logic.graph.Node;

public class PathFinder {
	private Graph graph;

	public PathFinder(ArrayList<Node> nodes) {
		super();
		this.graph = new Graph(null, nodes);
	}

	public Graph getGraph() {
		return graph;
	}
}
