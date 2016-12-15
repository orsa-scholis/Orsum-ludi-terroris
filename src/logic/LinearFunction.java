package logic;

import logic.graph.Point;

public class LinearFunction {
	private float m;
	private float c;
	
	public LinearFunction(Point p1, Point p2) {
		float deltaY = (float)(p2.getY() - p1.getY());
		float deltaX = (float)(p2.getX() - p1.getX());
		
		this.m = deltaY / deltaX;
		this.c = (float)p1.getY() - (m * (float)p1.getX());
	}
	
	protected LinearFunction(float m, float c) {
		super();
		this.m = m;
		this.c = c;
	}
	
	public LinearFunction getPerpendicularFunction(Point interception) {
		float newM = -(1.0f / this.m);
		float newC = (float)(interception.getY() - newM * interception.getX());
		
		return new LinearFunction(newM, newC);
	}
	
	public Point getInterceptionPoint(LinearFunction secondFunction) {
		if (secondFunction.getM() == this.getM()) { // Die Funktionen sind parallel => Entweder keine oder unendlich viele Schnittpunkte
			return null;
		}
		
		double x = (secondFunction.getC() - this.c) / (this.m - secondFunction.getM());
		double y = (double)this.getY((float)x);
		
		return new Point(x, y);
	}
	
	public float getY(float x) {
		return (m * x) + c;
	}

	public float getM() {
		return m;
	}

	public void setM(float m) {
		this.m = m;
	}

	public float getC() {
		return c;
	}

	public void setC(float c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "LinearFunction: f(x)="+m+" * x + "+c;
	}
}
