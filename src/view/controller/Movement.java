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

	public Movement(Driver driver) {
		this.driver = driver;
	}

	public boolean move(Node nd, int direction) {
		System.out.println("-------------Move----------------");
		double posX = nd.getPoint().getX();
		double posY = nd.getPoint().getY();
		switch (direction) {
		case 0:
			if (isThereNoObstacle(posX, posY + 0.1)) {
				if ((posY += 0.1) > 1.0) {
					posY = 0.9;
				}
			}
			break;
		case 1:
			if (isThereNoObstacle(posX + 0.1, posY)) {
				if ((posX += 0.1) > 1.0) {
					posX = 0.9;
				}
			}
			break;
		case 2:
			if (isThereNoObstacle(posX, posY - 0.1)) {
				if ((posY -= 0.1) < 0) {
					posY = 0;
				}
			}
			break;
		case 3:
			if (isThereNoObstacle(posX - 0.1, posY)) {
				if ((posX -= 0.1) < 0) {
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

	public void moveTo(Node nd, Point point) {
		nd.setPoint(point);
	}

}
