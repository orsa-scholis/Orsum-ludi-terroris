package logic;

import logic.graph.Point;

public class Index2D {
	private int x;
	private int y;
	
	public Index2D(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Point toPoint(double fieldSize) {
		return toPoint(fieldSize, fieldSize);
	}
	
	public Point toPoint(double fieldWidth, double fieldHeight) {
		return new Point((double)getX() / fieldWidth, (double)getY() / fieldHeight);
	}

	@Override
	public String toString() {
		return "Index2D [x=" + x + ", y=" + y + "]";
	}
}
