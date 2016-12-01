package logic.algorithm;

import java.util.ArrayList;

import logic.QuadController;
import logic.graph.Connection;
import logic.graph.Graph;
import logic.graph.Node;
import logic.graph.Point;

public class PathFinder {
	private Graph graph;
	private QuadController quadController;

	public PathFinder(ArrayList<Node> nodes, QuadController quadController) {
		super();
		this.graph = new Graph(null, nodes);
		this.quadController = quadController;
		
		connectNodes();
	}
	
	private void connectNodes() {
		for (int i = 0; i < this.graph.getNodes().size(); i++) {
			Node currentNode = this.graph.getNodes().get(i);
			
			for (int j = i; j < this.graph.getNodes().size(); j++) {
				Node nodeToConnect = this.graph.getNodes().get(j);
				
				Point p1 = currentNode.getPoint();
				Point p2 = nodeToConnect.getPoint();
				
				if (!quadController.testLineForObstacles(p1, p2)) {
					currentNode.connectTo(nodeToConnect);
				}
			}
		}
	}

	public Graph getGraph() {
		return graph;
	}
}
