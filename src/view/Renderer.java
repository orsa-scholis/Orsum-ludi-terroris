package view;

import java.util.ArrayList;

import logic.Index2D;
import logic.Quad;
import main.Driver;
import view.controller.Object;
import logic.graph.Monster;
import logic.graph.Player;
import view.graphicEngine.Shader;

public class Renderer {

	private Driver driver;

	public Renderer(Driver driver) {
		this.driver = driver;
	}

	public void render() {

		for (Quad quady : getQuads()) {

			Index2D indy = quady.getIndex();

			if (quady.isObstacle()) {
				float x = (float)indy.getX() / (float)driver.getGame().getQuadController().getWidth();
				float y = (float)indy.getY() / (float)driver.getGame().getQuadController().getHeight();
				drawWithShader(driver.getShaderMan().shaderObstacle, calcVertices(getFieldSize(), x, y));
			} else {
				float x = (float)indy.getX() / (float)driver.getGame().getQuadController().getWidth();
				float y = (float)indy.getY() / (float)driver.getGame().getQuadController().getHeight();
				(new Object(calcVertices(getFieldSize(), x, y))).draw();
			}
		}

		Player player = driver.getGame().getPlayer();
		float playerSize = 0.05f;
		drawWithShader(driver.getShaderMan().shaderPlayer, calcVertices(playerSize, player.getPoint().getFX(), player.getPoint().getFY()));

		Monster monster = driver.getGame().getMonster();
		float monsterSize = 0.05f;
		drawWithShader(driver.getShaderMan().shaderMonster, calcVertices(monsterSize, monster.getPoint().getFX(), monster.getPoint().getFY()));


	}

	private void drawWithShader(Shader shader, float[] vertices){
		Object object = new Object(vertices);

		shader.start();
		shader.setUniform3f("pos", object.position);
		object.draw();
		shader.stop();
	}

	private float[] calcVertices(float size, float x, float y){

		float px = (x * 2.0f) - 1.0f;
		float py = (y * 2.0f) - 1.0f;

		float[] vertices = {
				px, py + size, 0f,
				px, py, 0f,
				px + size, py, 0f,
				px + size, py + size, 0f
		};

		return vertices;
	}

	private float getFieldSize() {
		return 2.0f / driver.getGame().getQuadController().getWidth();
	}

	private ArrayList<Quad> getQuads() {
		return driver.getGame().getQuadController().getQuads();
	}
}
