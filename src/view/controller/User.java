package view.controller;

import logic.Game;
import logic.Index2D;
import logic.QuadController;
import logic.graph.Point;
import main.Driver;
import view.input.KeyboardInput;

public class User {
	private Driver driver;
	private KeyboardInput keyIn;

	public User(Driver driver, KeyboardInput keyIn) {
		this.driver = driver;
		this.keyIn = keyIn;
	}

	public void update(){
//		if(keyIn.isKeyDown()){
//
//		}

		QuadController c = driver.getGame().getQuadController();
		c.quadAtIndex(c.indexForPoint(new Point(0.5, 0.5))).isObstacle();
	}

	private Game getGame(){
		return driver.getGame();
	}

	private Movement getMove(){
		return driver.getMove();
	}

}
