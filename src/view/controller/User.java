<<<<<<< Updated upstream
package view.controller;

import logic.Game;
import logic.QuadController;
import logic.graph.Point;
import main.Driver;
import view.input.KeyboardInput;

import static view.controller.Movement.*;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

public class User {
	private Driver driver;
	private ArrayList<Point> points;
	private int pointsCount;
	private double pointMoveCount;

	public User(Driver driver) {
		this.driver = driver;
		points = null;
		pointsCount = 1;
		pointMoveCount = 0;

		if (points == null) {
			points = getGame().getPathForMonster().getPoints();
		}
	}

	public void update() {
		if (points == null) {
			points = getGame().getPathForMonster().getPoints();
			pointsCount = 1;
		} else if(points.size() - 1 > pointsCount){
			if(!getMove().moveTo(getGame().getMonster(), points.get(pointsCount), pointMoveCount)){
				pointMoveCount ++;
			}
			else{
				pointsCount++;
				pointMoveCount = 0;
			}
		}
		else{
			//System.out.println("Target reached!");
		}

		if (KeyboardInput.isKeyDown(GLFW_KEY_W)) {
			getMove().move(getGame().getPlayer(), UP);
		}
		if (KeyboardInput.isKeyDown(GLFW_KEY_S)) {
			getMove().move(getGame().getPlayer(), DOWN);
		}
		if (KeyboardInput.isKeyDown(GLFW_KEY_A)) {
			getMove().move(getGame().getPlayer(), LEFT);
		}
		if (KeyboardInput.isKeyDown(GLFW_KEY_D)) {
			getMove().move(getGame().getPlayer(), RIGHT);
		}

		QuadController c = getGame().getQuadController();
		c.quadAtIndex(c.indexForPoint(new Point(0.5, 0.5))).isObstacle();

	}

	private Game getGame() {
		return driver.getGame();
	}

	private Movement getMove() {
		return driver.getMove();
	}

}
=======
package view.controller;

import logic.Game;
import logic.QuadController;
import logic.graph.Point;
import main.Driver;
import view.input.KeyboardInput;

import static view.controller.Movement.*;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

public class User {
	private Driver driver;
	private ArrayList<Point> points;
	private int pointsCount;
	private double pointMoveCount;

	public User(Driver driver) {
		this.driver = driver;
		points = null;
		pointsCount = 1;
		pointMoveCount = 0;

		if (points == null) {
			points = cleanPoints(getGame().getPathForMonster().getPoints());
		}
	}

	public void update() {
		if (points == null) {
			points = cleanPoints(getGame().getPathForMonster().getPoints());
			pointsCount = 1;
		} else if(points.size() - 1 > pointsCount){
			if(!getMove().moveTo(getGame().getMonster(), points.get(pointsCount), pointMoveCount)){
				pointMoveCount++;
			}
			else{
				pointsCount++;
				pointMoveCount = 0;
			}
		}
		else{
			//System.out.println("Target reached!");
		}

		if (KeyboardInput.isKeyDown(GLFW_KEY_W)) {
			getMove().move(getGame().getPlayer(), UP);
		}
		if (KeyboardInput.isKeyDown(GLFW_KEY_S)) {
			getMove().move(getGame().getPlayer(), DOWN);
		}
		if (KeyboardInput.isKeyDown(GLFW_KEY_A)) {
			getMove().move(getGame().getPlayer(), LEFT);
		}
		if (KeyboardInput.isKeyDown(GLFW_KEY_D)) {
			getMove().move(getGame().getPlayer(), RIGHT);
		}

		QuadController c = getGame().getQuadController();
		c.quadAtIndex(c.indexForPoint(new Point(0.5, 0.5))).isObstacle();

	}

	private ArrayList<Point> cleanPoints(ArrayList<Point> points){
		ArrayList<Point> clean = new ArrayList<>();
		for(Point pt : points){
			if(pt != null){
				clean.add(pt);
			}
		}
		return clean;
	}

	private Game getGame() {
		return driver.getGame();
	}

	private Movement getMove() {
		return driver.getMove();
	}

}
>>>>>>> Stashed changes
