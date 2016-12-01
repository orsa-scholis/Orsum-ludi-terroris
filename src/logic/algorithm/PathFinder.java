package logic.algorithm;

import java.util.ArrayList;

import logic.Quad;
import logic.QuadController;
import logic.graph.Connection;
import logic.graph.Graph;
import logic.graph.Monster;
import logic.graph.Node;
import logic.graph.Player;
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
	
	private void integratePlayerAndMonsterIntoGraph(Player player, Monster monster, QuadController quadController) {
		graph.setRoot(monster);
		graph.setEnd(player);
		
		Point monsterPoint = monster.getPoint();
		Point playerPoint = player.getPoint();
		for (Node node : graph.getNodes()) {
			if (!quadController.testLineForObstacles(monsterPoint, node.getPoint())) {
				monster.connectTo(node);
			}
			
			if (!quadController.testLineForObstacles(playerPoint, node.getPoint())) {
				player.connectTo(node);
			}
		}
	}
	
	public Path getBestPathToShootForMonster(Monster monster, Player player, QuadController quadController) {
		integratePlayerAndMonsterIntoGraph(player, monster, quadController);
		
		return null;
	}

	public Graph getGraph() {
		return graph;
	}
}
