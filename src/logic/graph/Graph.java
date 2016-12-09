package logic.graph;

import java.util.ArrayList;
import java.util.Iterator;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

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
	
	@SuppressWarnings("unchecked")
	public Graph clone() throws CloneNotSupportedException {
		Graph graph = new Graph();
		
		Object clone = this.nodes.clone();
		if (!(clone instanceof ArrayList<?>)) {
			return null;
		}
		
		graph.nodes = (ArrayList<Node>)clone;
		graph.root = (this.root != null) ? (Node)this.root.clone() : null;
		graph.end = (this.end != null) ? (Node)this.end.clone() : null;
		
		return graph;
	}
}
