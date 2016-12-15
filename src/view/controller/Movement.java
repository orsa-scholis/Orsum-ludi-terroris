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

	public Movement(Driver driver) {
		this.driver = driver;
		this.moveCount = 30;
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

	public boolean moveTo(Node nd, Point point, double count) {
		Point newP = null;
		if (count != moveCount - 1) {
			System.out.println("--------: " + count + " :--------");
			newP = new Point((point.getX() + (getFieldSize() / moveCount * (moveCount - (count + 1.0)))),
					(point.getY() + (getFieldSize() / moveCount * (moveCount - (count + 1.0)))));

			System.out.println("ND-P: " + nd.getPoint().getX() + " | " + nd.getPoint().getY());
			System.out.println(
					"Minus: " + (newP.getX() - nd.getPoint().getX()) + " | " + (newP.getY() - nd.getPoint().getY()));
			System.out.println("NewP: " + newP.getX() + " | " + newP.getY());
			System.out.println("Point: " + point.getX() + " | " + point.getY());
			System.out.println((getFieldSize() / moveCount * (moveCount - (count + 1.0))));
		}
		else {
			System.out.println("--------: true :--------");
			System.out.println("");
			return true;
		}
		nd.setPoint(newP);
		System.out.println("--------: false :--------");
		System.out.println("");
		return false;
	}

	private double getFieldSize() {
		return 2.0 / driver.getGame().getQuadController().getWidth();
	}

	private double calcDistance(Point p1, Point p2) {
		return Math.sqrt(Math.pow((p1.getX() - p2.getX()), 2.0) + Math.pow((p1.getY() - p2.getY()), 2.0));
	}

}
