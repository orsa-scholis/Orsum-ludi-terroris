package view.controller;

import gameEnginge.MovementController;
import input.KeyboardInput;
import main.Driver;
import utils.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends Field {

    private Movement mvC;

	private static float[] vertices = { -1.0f, -0.75f, 0.0f, -1.0f, -1.0f, 0.0f, -0.75f, -1.0f, 0.0f, -0.75f, -0.75f,
			0.0f };

	private static byte[] indices = { 0, 1, 2, 2, 3, 0 };

	public Player(Driver driver) {
		super(vertices, indices, 2, driver);
		position = new Vector3f();
        mvC = driver.gameG.getMovCont();
	}

	@Override
	public void update() {
		System.out.println("Update Player!! ---------------");
		if (KeyboardInput.isKeyDown(GLFW_KEY_W)) {
            mvC.move(this, mvC.UP);
		}
		if (KeyboardInput.isKeyDown(GLFW_KEY_S)) {
            mvC.move(this, mvC.DOWN);
		}
		if (KeyboardInput.isKeyDown(GLFW_KEY_D)) {
            mvC.move(this, mvC.RIGHT);
		}
		if (KeyboardInput.isKeyDown(GLFW_KEY_A)) {
            mvC.move(this, mvC.LEFT);
		}
	}

}
