package view.controller;

import logic.QuadController;
import logic.graph.Node;
import logic.graph.Point;
import main.Driver;

public class Movement {

	public static int UP = 0;
	public static int DOWN = 2;
	public static int RIGHT = 1;
	public static int LEFT = 3;

	private Driver driver;
	private int moveCount;
	private double moveDistanceX;
	private double moveDistanceY;

	public Movement(Driver driver) {
		this.driver = driver;
		this.moveCount = 120;
		this.moveDistanceX = 0.0;
		this.moveDistanceY = 0.0;
	}

	public boolean move(Node nd, int direction) {
		System.out.println("-------------Move----------------");
		double posX = nd.getPoint().getX();
		double posY = nd.getPoint().getY();
		switch (direction) {
		case 0:
			if (isThereNoObstacle(posX, posY + 0.01)) {
				if ((posY += 0.01) > 1.0) {
					posY = 0.99;
				}
			}
			break;
		case 1:
			if (isThereNoObstacle(posX + 0.01, posY)) {
				if ((posX += 0.01) > 1.0) {
					posX = 0.99;
				}
			}
			break;
		case 2:
			if (isThereNoObstacle(posX, posY - 0.01)) {
				if ((posY -= 0.01) < 0) {
					posY = 0;
				}
			}
			break;
		case 3:
			if (isThereNoObstacle(posX - 0.01, posY)) {
				if ((posX -= 0.01) < 0) {
					posX = 0;
				}
			}
			break;
		default:
			return false;
		}
		nd.setPoint(new Point(posX, posY));
		return true;
	}

	public boolean isThereNoObstacle(double x, double y) {
		QuadController c = driver.getGame().getQuadController();
		try {
			if (c.quadAtIndex(c.indexForPoint(new Point(x, y))).isObstacle()) {
				return false;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return true;
	}

	public void setupMonsterMovement(Node mst, Point point){
		moveDistanceX = (point.getX() - mst.getPoint().getX()) / moveCount;
		moveDistanceY = (point.getY() - mst.getPoint().getY()) / moveCount;
	}

	public boolean moveTo(Node nd, Point point) {
		Point newP = new Point(nd.getPoint().getX() + moveDistanceX, nd.getPoint().getY() + moveDistanceY);

		nd.setPoint(newP);

		return false;
	}

	private double getFieldSize() {
		return 2.0 / driver.getGame().getQuadController().getWidth();
	}

	private double calcDistance(Point p1, Point p2) {
		return Math.sqrt(Math.pow((p1.getX() - p2.getX()), 2.0) + Math.pow((p1.getY() - p2.getY()), 2.0));
	}

}
