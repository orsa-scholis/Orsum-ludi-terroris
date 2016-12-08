package logic.algorithm;

import logic.graph.Node;

class DijkstraNode {
	private Node me;
	private float length;
	private boolean checked;
	private Node previous;

	public DijkstraNode(Node me) {
		this.me = me;
		this.length = 10000;
		this.checked = false;
		this.previous = null;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked() {
		this.checked = true;
	}

	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}

	public Node getMe() {
		return me;
	}

}
