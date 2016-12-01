package view.controller;

import view.utils.Vector3f;

public class Player extends Field {

	private static float[] vertices = { -1.0f, -0.75f, 0.0f, -1.0f, -1.0f, 0.0f, -0.75f, -1.0f, 0.0f, -0.75f, -0.75f,
			0.0f };

	public Player() {
		super(vertices);
		position = new Vector3f();
	}
}
