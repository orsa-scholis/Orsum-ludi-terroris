package logic.algorithm;

import java.util.ArrayList;

import logic.LinearFunction;
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
	
	private void findAdditionalGraphNodesWithPerpendicularLineAtPointOfInterception(Player player, Monster monster, QuadController quadController) {
		@SuppressWarnings("unchecked")
		ArrayList<Connection> clone = (ArrayList<Connection>)player.getConnections().clone();
		ArrayList<Connection> playerConnections = clone;
		
		Point playerPosition = player.getPoint();
		System.out.println(playerConnections);
		for (Connection connection : playerConnections) {
			Point connectionNodePosition = connection.getEnd().getPoint();
			
			LinearFunction function = new LinearFunction(playerPosition, connectionNodePosition);
			LinearFunction perpendicularFunction = function.getPerpendicularFunction(monster.getPoint());
			Point interception = function.getInterceptionPoint(perpendicularFunction);
			
			if (interception.getX() < 0 || interception.getX() > 1 ||
				interception.getY() < 0 || interception.getY() > 1) {
				continue;
			}
			
			if (!quadController.testLineForObstacles(playerPosition, interception)) {
				Node node = new Node(interception);
				node.connectTo(player);
				graph.addNode(node);
			}
		}
	}
	
	public Path getBestPathToShootForMonster(Monster monster, Player player, QuadController quadController) {
		System.out.println(monster);
		System.out.println(player);
		integratePlayerAndMonsterIntoGraph(player, monster, quadController);
		findAdditionalGraphNodesWithPerpendicularLineAtPointOfInterception(player, monster, quadController);
		
		return null;
	}

	public Graph getGraph() {
		return graph;
	}
}
