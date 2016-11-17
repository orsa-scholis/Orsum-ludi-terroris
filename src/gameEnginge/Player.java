package gameEnginge;

import graphicEngine.VertexArrayObject;
import input.KeyboardInput;
import utils.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

public class Player extends GameObject {

	private VertexArrayObject vao;
	public Vector3f position;

	private static float[] vertices = { -1.0f, -0.75f, 0.0f, -1.0f, -1.0f, 0.0f, -0.75f, -1.0f, 0.0f, -0.75f, -0.75f,
			0.0f };

	private static byte[] indices = { 0, 1, 2, 2, 3, 0 };

	public Player() {
		super(vertices, indices);
		position = new Vector3f();
	}

	public Player(float[] vertices, byte[] indices) {
		super(vertices, indices);
		Player.vertices = vertices;
		Player.indices = indices;
		position = new Vector3f();
	}

	public void update() {
		if (KeyboardInput.isKeyDown(GLFW_KEY_W)) {
			if ((position.y += 0.01f) > 1.9f) {
				position.y = 1.9f;
			}
		}
		if (KeyboardInput.isKeyDown(GLFW_KEY_S)) {
			if ((position.y -= 0.01f) < 0.0f) {
				position.y = 0.0f;
			}
		}
		if (KeyboardInput.isKeyDown(GLFW_KEY_D)) {
			if((position.x += 0.01f) > 1.9f){
				position.x = 1.9f;
			}
		}
		if (KeyboardInput.isKeyDown(GLFW_KEY_A)) {
			if((position.x -= 0.01f) < 0.0f){
				position.x = 0.0f;
			}
		}
	}

}
