import java.util.ArrayList;

import gameEnginge.Field;
import gameEnginge.Monster;
import gameEnginge.Player;

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
		for (Field field : fields) {
			System.out.println("\nNew Field:");
			int i = 0;
			for (Float floaty : field.getVertices()) {
				if(i < 3) {
					System.out.print(floaty + " : ");
					i++;
				}
				else{
					System.out.println("");
					i = 0;
				}
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

	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

}
