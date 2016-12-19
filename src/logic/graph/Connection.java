package logic.graph;

import java.lang.ref.WeakReference;

public class Connection {
	private WeakReference<Node> start;
	private WeakReference<Node> end;
	private float length;
	private boolean lengthDirty;
	
	public Connection(Node start, Node end) {
		super();
		setStart(start);
		setEnd(end);
	}
	
	public Node getStart() {
		return start.get();
	}
	
	public void setStart(Node start) {
		this.start = new WeakReference<Node>(start);
		this.lengthDirty = true;
	}
	
	public Node getEnd() {
		return end.get();
	}
	
	public void setEnd(Node end) {
		this.end = new WeakReference<>(end);
		this.lengthDirty = true;
	}
	
	public Node getCounterpart(Node thisNode) {
		if (thisNode.equals(start.get())) {
			return end.get();
		} else if (thisNode.equals(end.get())) {
			return start.get();
		} else {
			return null;
		}
	}
	
	public float getLength() {
		if (lengthDirty) {
			Point p1 = getStart().getPoint();
			Point p2 = getEnd().getPoint();
			
			length = (float)Math.sqrt(Math.pow((p1.getX() - p2.getX()), 2.0) + Math.pow((p1.getY() - p2.getY()), 2.0));
		}
		
		return length;
	}
}
