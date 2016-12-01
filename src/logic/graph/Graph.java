package logic.graph;

import java.util.ArrayList;
import java.util.Iterator;

public class Graph {
	private Node root;
	private Node end;
	private ArrayList<Node> nodes;
	
	public Graph() {
		super();
		this.nodes = new ArrayList<>();
	}
	
	public Graph(Node root, ArrayList<Node> nodes) {
		super();
		this.root = root;
		this.nodes = nodes;
	}
	
	public void addNode(Node node) {
		this.nodes.add(node);
	}
	
	public void removeNode(Node node) {
		this.nodes.remove(node);
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}
	
	public Node getEnd() {
		return end;
	}

	public void setEnd(Node end) {
		this.end = end;
	}

	public String toString(double fieldSize) {
		String returnString = "nodes: {";
		for (Iterator<Node> iterator = nodes.iterator(); iterator.hasNext();) {
			Node node = (Node)iterator.next();
			
			double x = node.getPoint().getX() * fieldSize;
			double y = node.getPoint().getY() * fieldSize;
			
			returnString += "\n" + x + "\t" + y;
		}
		
		returnString += "\n}";
		return returnString;
	}
	
	public String export() {
		String returnString = "{nodes:[";
		
		ArrayList<Connection> connections = new ArrayList<>();
		ArrayList<Node> allNodes = new ArrayList<>();
		allNodes.addAll(nodes);
		allNodes.add(root);
		allNodes.add(end);
		for (Node node : allNodes) {
			returnString += "[" + node.getPoint().getX() + "," + node.getPoint().getY() + "],";
			
			for (Connection connection : node.getConnections()) {
				if (!connections.contains(connection)) {
					connections.add(connection);
				}
			}
		}
		returnString = returnString.substring(0, returnString.length() - 1); // remove last comma
		returnString += "],lines:[";
		
		for (Connection connection : connections) {
			returnString += "[[" + connection.getStart().getPoint().getX() + "," + connection.getStart().getPoint().getY() 
						 + "],[" + connection.getEnd().getPoint().getX() + "," + connection.getEnd().getPoint().getY() + "]],";
		}
		returnString = returnString.substring(0, returnString.length() - 1) + "]"; // remove last comma
		
		returnString += "}";
		return returnString;
	}
}
