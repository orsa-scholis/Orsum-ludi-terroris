package logic.graph;

import java.util.ArrayList;

public class Node {
	private ArrayList<Connection> connections = new ArrayList<>();
	private Point point;
	
	public Node(Point point) {
		super();
		
		this.point = point;
	}
	
	public Node(ArrayList<Connection> connections, Point point) {
		super();
		this.connections = connections;
		this.point = point;
	}
	
	public void connectTo(Node nodeToConnect) {
		if (nodeToConnect.equals(this)) {
			System.out.println("(Node) Cannot connecto to itself");
			return;
		}
		
		Connection connection = new Connection(this, nodeToConnect);
		this.connections.add(connection);
		nodeToConnect.getConnections().add(connection);
	}
	
	public ArrayList<Connection> getConnections() {
		return connections;
	}
	
	public void setConnections(ArrayList<Connection> connections) {
		this.connections = connections;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public void setPoint(Point point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Node [point=" + point + "]";
	}
}
