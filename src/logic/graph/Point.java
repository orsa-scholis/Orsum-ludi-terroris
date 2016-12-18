package logic.graph;

import logic.Index2D;

public class Point {
	private double x;
	private double y;


	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}


	public double getX() {
		return x;
	}


	public float getFX(){
		return (float) x;
	}

	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}

	public float getFY(){
		return (float) y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Index2D toIndex2d(double fieldSize) {
		return new Index2D((int)Math.floor(x * fieldSize), (int)Math.floor(y * fieldSize));
	}


	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
			return false;
		}
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
			return false;
		}
		return true;
	}
	
	@Override
	public Object clone() {
		return new Point(this.x, this.y);
	}
}
