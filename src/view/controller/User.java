package view.controller;

import logic.Game;
import main.Driver;

public class User {
	private Driver driver;

	public User(Driver driver) {

	}

	private Game getGame(){
		return driver.getGame();
	}

}
