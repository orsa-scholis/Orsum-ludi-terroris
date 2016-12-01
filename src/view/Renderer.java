package view;

import java.util.ArrayList;

import logic.Index2D;
import logic.Quad;
import main.Driver;
import view.controller.Field;
import view.graphicEngine.ShaderManager;

public class Renderer {

	private Driver driver;

	public Renderer(Driver driver) {
		this.driver = driver;
	}

	public void render() {

		for (Quad quady : getQuads()) {

			Index2D indy = quady.getIndex();

			float[] vertices = { indy.getX() / 4f - 1f, indy.getY() / 4f - (1f - getFieldSize()), 0f,
					indy.getX() / 4f - 1f, indy.getY() / 4f - 1f, 0f, indy.getX() / 4f - (1f - getFieldSize()),
					indy.getY() / 4f - 1f, 0f, indy.getX() / 4f - (1f - getFieldSize()),
					indy.getY() / 4f - (1f - getFieldSize()), 0f, };

			if (quady.isObstacle()) {
				Field tmp = new Field(vertices);

				driver.getShaderMan().shaderField.start();
                driver.getShaderMan().shaderField.setUniform3f("pos", tmp.position);
                tmp.draw();
                ShaderManager.shaderField.stop();

			} else {
				(new Field(vertices)).draw();
			}
		}



	}

	private int getGameSize() {
		return driver.getGame().getQuadController().getWidth();
	}

	private float getFieldSize() {
		return 2.0f / driver.getGame().getQuadController().getWidth();
	}

	private ArrayList<Quad> getQuads() {
		return driver.getGame().getQuadController().getQuads();
	}

}
