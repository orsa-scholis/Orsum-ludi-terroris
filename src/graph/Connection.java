package graph;

import java.lang.ref.WeakReference;

public class Connection {
	private WeakReference<Node> start;
	private Node end;
	private float length;
	
	
	
	public WeakReference<Node> getStart() {
		return start;
	}
	
	public void setStart(WeakReference<Node> start) {
		this.start = start;
	}
	
	public Node getEnd() {
		return end;
	}
	
	public void setEnd(Node end) {
		this.end = end;
	}
	
	public float getLength() {
		return length;
	}
	
	public void setLength(float length) {
		this.length = length;
	}
}
