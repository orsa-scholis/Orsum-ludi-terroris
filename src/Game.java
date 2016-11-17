import java.util.ArrayList;

import gameEnginge.Field;
import gameEnginge.Monster;
import gameEnginge.Player;
import graphicEngine.ShaderManager;

public class Game {
	private Player player;
	private ArrayList<Monster> monsters;
	private ArrayList<Field> fields;

	private int width = 720;
	private int height = 720;

	public Game() {
		monsters = new ArrayList<>();
		fields = new ArrayList<>();
	}

	public void init() {
		ShaderManager.loadAll();
		for (float i = 0; i < 20; i++) {
			for (float j = 0; j < 20; j++) {

				float[] vertices = {
						i / 10f - 1f, j / 10f - 0.9f, 0f,
						i / 10f - 1f, j / 10f - 1f, 0f,
						i / 10f - 0.9f,	j / 10f - 1f, 0f,
						i / 10f - 0.9f, j / 10f - 0.9f, 0f, };

				byte[] indices = {
						0, 1, 2,
						2, 3, 0 };

				Field field = new Field(vertices, indices);
				fields.add(field);
			}
		}
		player = new Player();
	}

	public void update() {
		player.update();

		for (Monster monster : monsters) {
			monster.update();
		}
	}

	public void draw() {
		for (Field field : fields) {
			field.draw();
		}
		ShaderManager.shaderPlayer.start();
		ShaderManager.shaderPlayer.setUniform3f("pos", player.position);
		player.draw();
		ShaderManager.shaderPlayer.stop();
		ShaderManager.shaderMonster.start();

		for (Monster monster : monsters){
			monster.draw();
			ShaderManager.shaderMonster.setUniform3f("pos", monster.getPosition());
		}
		ShaderManager.shaderMonster.stop();
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

}
