package logic.algorithm;

import java.rmi.UnexpectedException;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

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
			for (int j = i + 1; j < this.graph.getNodes().size(); j++) {
				Node nodeToConnect = this.graph.getNodes().get(j);

				Point p1 = currentNode.getPoint();
				Point p2 = nodeToConnect.getPoint();

				if (!quadController.testLineForObstacles(p1, p2)) {
					currentNode.connectTo(nodeToConnect);
				}
			}
		}
	}
	private void integratePlayerAndMonsterIntoGraph(Player player, Monster monster, QuadController quadController, Graph graph) {
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

	private void findAdditionalGraphNodesWithPerpendicularLineAtPointOfInterception(Player player, Monster monster, QuadController quadController, Graph graph) throws UnexpectedException {
		Object clone = player.getConnections().clone();
		if (!(clone instanceof ArrayList<?>)) {
			throw new UnexpectedException("Clone of ArrayList is not an Array");
		}

		@SuppressWarnings("unchecked")
		ArrayList<Connection> playerConnections = (ArrayList<Connection>)clone;

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
				node.connectTo(connection.getEnd());
				graph.addNode(node);

				if (!quadController.testLineForObstacles(interception, monster.getPoint())) {
					node.connectTo(monster);
					// Hier müsste man eigentlich auch die anderen Nodes mit der neuen Verbinden,
					// doch für Performance überspringen wir dies
				} else {
					for (Node graphNode : graph.getNodes()) {
						if (graphNode.equals(node)) {
							continue;
						}

						if (!quadController.testLineForObstacles(graphNode.getPoint(), node.getPoint())) {
							node.connectTo(graphNode);
						}
					}
				}
			}
		}
	}

	public Path getBestPathToShootForMonster(Monster monster, Player player, QuadController quadController) {
		try {
			Graph graph = this.graph.clone();
			integratePlayerAndMonsterIntoGraph(player, monster, quadController, graph);
			try {
				findAdditionalGraphNodesWithPerpendicularLineAtPointOfInterception(player, monster, quadController, graph);
			} catch (UnexpectedException e) {
				e.printStackTrace();
				return null;
			}

			//return null;
			Dijkstra dijkstra = new Dijkstra(graph);
			return dijkstra.getShortestWaysMap();
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();

			return null;
		}
	}

	public Graph getGraph() {
		return graph;
	}
}
