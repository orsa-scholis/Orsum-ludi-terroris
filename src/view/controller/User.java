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

	public User(Driver driver) {
		this.driver = driver;
		points = null;
		pointsCount = 0;

		if (points == null) {
			points = getGame().getPathForMonster().getPoints();
		}
	}

	public void update() {
		if (points == null) {
			points = getGame().getPathForMonster().getPoints();
			pointsCount = 0;
		} else if (!points.equals(getGame().getPathForMonster().getPoints())) {
			points = getGame().getPathForMonster().getPoints();
			pointsCount = 0;
		} else {
			getMove().moveTo(getGame().getMonster(), points.get(pointsCount));
			pointsCount++;
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
