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
		pointMoveCount = 0;

		if (points == null) {
			points = getGame().getPathForMonster().getPoints();
			points.remove(0);
			points.remove(points.size()-1);
		}
	}

	public void update() {
		if (points == null) {
			points = getGame().getPathForMonster().getPoints();
			points.remove(0);
			points.remove(points.size() - 1);
		}
		else if(getGame().getMonster().getPoint().getX() != points.get(points.size()-1).getX() && getGame().getMonster().getPoint().getY() != points.get(points.size()-1).getY()){
			if (pointsCount == 0) {
				getMove().setupMonsterMovement(getGame().getMonster(), points.get(0));
				getMove().moveTo(getGame().getMonster(), points.get(0));
				pointsCount++;
			}
			else if(pointsCount < 60 && pointsCount != 0){
				getMove().moveTo(getGame().getMonster(), points.get(0));
				pointsCount++;
			}
			else{
				pointsCount = 0;
				points.remove(0);
			}

			if (points.size() == 0) {
				points = null;
			}
		}
		else{
			points = null;
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
