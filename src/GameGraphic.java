import java.util.ArrayList;

import gameEnginge.Field;
import gameEnginge.Monster;
import gameEnginge.Player;
import graphicEngine.ShaderManager;
import logic.Game;

public class GameGraphic {
	private Driver driver;
	private Player player;
	private ArrayList<Monster> monsters;
	private ArrayList<Field> fields;
	private ArrayList<Field> obstacles;

	private int width = 1000;
	private int height = 1000;

	public GameGraphic(Driver driver) {
		this.driver = driver;
		monsters = new ArrayList<>();
		fields = new ArrayList<>();
//		int[][] field = new int[][] {
//			{ 0, 0, 0, 0, 0, 0, 0, 0 },
//			{ 0, 0, 0, 0, 0, 0, 0, 0 },
//			{ 0, 0, 1, 0, 0, 0, 0, 0 },
//			{ 0, 0, 0, 0, 0, 0, 0, 0 },
//			{ 0, 0, 0, 0, 0, 0, 0, 0 },
//			{ 0, 1, 1, 0, 0, 1, 0, 0 },
//			{ 0, 0, 0, 0, 0, 0, 0, 0 },
//			{ 0, 0, 0, 0, 0, 0, 0, 0 }
//		};

		//Game game = new Game(field);
		//System.out.println(game.getQuadController());
	}

	public void init() {
		ShaderManager.loadAll();
		for (float i = 0; i < 8; i++) {
			for (float j = 0; j < 8; j++) {

				float[] vertices = {
						i / 4f - 1f, j / 4f - 0.75f, 0f,
						i / 4f - 1f, j / 4f - 1f, 0f,
						i / 4f - 0.75f,	j / 4f - 1f, 0f,
						i / 4f - 0.75f, j / 4f - 0.75f, 0f, };

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
		//ShaderManager.shaderMonster.start();

		for (Monster monster : monsters){
			monster.draw();
			//ShaderManager.shaderMonster.setUniform3f("pos", monster.getPosition());
		}
		//ShaderManager.shaderMonster.stop();
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

}
