package main;
import java.util.ArrayList;

import gameEnginge.Field;
import gameEnginge.Monster;
import gameEnginge.MovementController;
import gameEnginge.Player;
import graphicEngine.ShaderManager;
import logic.Game;

public class GameGraphic {
	private Driver driver;
    public MovementController movCont;
	private Player player;
	private ArrayList<Monster> monsters;
	private ArrayList<Field> fields;
	private ArrayList<Field> obstacles;
    private int[][] field;

	private int width = 1000;
	private int height = 1000;

	public GameGraphic(Driver driver) {
		this.driver = driver;
        movCont = new MovementController(this);
		monsters = new ArrayList<>();
		fields = new ArrayList<>();
		obstacles = new ArrayList<>();
        field = new int[][] {
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 }
        };
	}

	public void init() {
		ShaderManager.loadAll();
		for (float i = 0; i < 8; i++) {
			for (float j = 0; j < 8; j++) {

				float[] vertices = {
						i / 4f - 1f, j / 4f - 0.75f, 0f,
						i / 4f - 1f, j / 4f - 1f, 0f,
						i / 4f - 0.75f, j / 4f - 1f, 0f,
						i / 4f - 0.75f, j / 4f - 0.75f, 0f,};

				byte[] indices = {
						0, 1, 2,
						2, 3, 0};

                Field tmp;
                if(field[(int)i][(int)j] == 1){
				     tmp = new Field(vertices, indices, true);
                }
                else{
                    tmp = new Field(vertices, indices, false);
                }
				fields.add(tmp);
			}
		}

		player = new Player(driver);

	}

	public void update() {
		player.update();

		for (Monster monster : monsters) {
			monster.update();
		}
	}

	public void draw() {
		for (Field field : fields) {
			if(field.isObstacle()){
                ShaderManager.shaderFields.start();
                ShaderManager.shaderFields.setUniform3f("pos",field.position);
                field.draw();
                ShaderManager.shaderFields.stop();
            }
            else {
                field.draw();
            }
		}
		ShaderManager.shaderPlayer.start();
		ShaderManager.shaderPlayer.setUniform3f("pos", player.position);
		player.draw();
		ShaderManager.shaderPlayer.stop();
//		//ShaderManager.shaderMonster.start();
//
		for (Monster monster : monsters){
			monster.draw();
////			ShaderManager.shaderMonster.setUniform3f("pos", monster.getPosition());
		}
////		ShaderManager.shaderMonster.stop();
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int[][] getField() { return  field; }

}
