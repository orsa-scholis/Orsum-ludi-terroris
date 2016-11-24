package gameEnginge;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;

import graphicEngine.VertexArrayObject;
import input.KeyboardInput;
import main.Driver;
import utils.Vector3f;

public class Monster extends GameObject {

	private VertexArrayObject vao;

	private static float[] vertices = {
			0f, 0f, 0f,
			0f, 0f, 0f,
			0f, 0f, 0f,
			0f, 0f, 0f
	};

	private static byte[] indices = {
			0, 1, 2,
			2, 3, 0
	};

	public Monster(float[] vertices, byte[] indices, Driver driver) {
		super(vertices, indices, 3, driver);
		this.vertices = vertices;
		this.indices = indices;
		setPosition(new Vector3f());
	}

	public void update() {
		if (KeyboardInput.isKeyDown(GLFW_KEY_W)&& getPosition().y < 1.0) {
			getPosition().y += 0.1f;
		}
		if (KeyboardInput.isKeyDown(GLFW_KEY_S)&& getPosition().y > -1.0) {
			getPosition().y -= 0.1f;
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}
}
