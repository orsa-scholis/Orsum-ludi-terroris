package main;
import java.util.ArrayList;

import gameEnginge.*;
import graphicEngine.ShaderManager;

public class GameGraphic {
	private Driver driver;
    private MovementController movCont;
	private Player player;
    private ArrayList<GameObject> objects;
    private int[][] field;
    private int gameSize;
    private float fieldSize;

	private int width = 1000;
	private int height = 1000;

    public static int FIELD = 0;
    public static int OBSTACLE = 1;
    public static int PLAYER = 2;
    public static int MONSTER = 3;


	public GameGraphic(Driver driver, int gameSize) {
		this.driver = driver;
        movCont = new MovementController(this);
		objects = new ArrayList<>();
        field = new int[][] {
                { PLAYER, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD },
                { FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD },
                { FIELD, FIELD, OBSTACLE,FIELD, FIELD, FIELD, FIELD, FIELD },
                { FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD },
                { FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD },
                { FIELD, OBSTACLE,OBSTACLE,FIELD, FIELD, OBSTACLE,FIELD, FIELD },
                { FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD },
                { FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, MONSTER }
        };
        this.gameSize = gameSize;
        this.fieldSize = 2.0f / gameSize;
	}

	public void init() {
		ShaderManager.loadAll();

        for (float x = 0; x < gameSize; x++) {
			for (float y = 0; y < gameSize; y++) {

				float[] vertices = {
						x / 4f - 1f, y / 4f - ( 1f - fieldSize), 0f,
						x / 4f - 1f, y / 4f - 1f, 0f,
						x / 4f - ( 1f - fieldSize ), y / 4f - 1f, 0f,
						x / 4f - (1f - fieldSize), y / 4f - ( 1f - fieldSize ), 0f,};

				byte[] indices = {
						0, 1, 2,
						2, 3, 0};

                GameObject tmp;
                switch(field[(int)y][(int)x]) {
                    case 0:
                        tmp = new Field(vertices, indices, 1, driver);
                        break;
                    case 1:
                        tmp = new Field(vertices, indices, 0, driver);
                        break;
                    case 2:
                        tmp = new Player(driver);
                        player = (Player)tmp;
                        break;
                    case 3:
                        tmp = new Monster(vertices, indices, driver);
                        break;
                    default:
                        tmp = null;
                        break;
                }
				objects.add(tmp);
			}
		}

		player = new Player(driver);

	}

	public void update() {
        player.update();
	}

	public void draw() {
		for (GameObject gmO : objects) {
			switch (gmO.getGoType()) {
                case 0:
                    ShaderManager.shaderField.start();
                    System.out.println(gmO.position.x);
                    ShaderManager.shaderField.setUniform3f("pos", gmO.position);
                    gmO.draw();
                    ShaderManager.shaderField.stop();
                    break;
                case 1:
                    gmO.draw();
                    break;
                case 2:
                    ShaderManager.shaderPlayer.start();
                    ShaderManager.shaderPlayer.setUniform3f("pos", player.position);
                    player.draw();
                    ShaderManager.shaderPlayer.stop();
                    break;
                case 3:
                    //ShaderManager.shaderMonster.start();
                    //ShaderManager.shaderMonster.setUniform3f("pos", gmO.position);
                    gmO.draw();
                    //ShaderManager.shaderMonster.stop();
                    break;
                default:
                    System.err.println("Wrong GO-TYPE!!!");
                    break;
            }
		}

	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int[][] getField() { return  field; }

	public void changeGameObject(int oldX, int newX, int oldY, int newY, int gOType) {
        if(gOType == 0 || gOType == 1 || gOType == 2) {
            field[oldX][oldY] = 0;
            field[newX][newY] = gOType;
        }
    }

    public int getGameSize() {
        return gameSize;
    }

    public float getFieldSize() {
        return fieldSize;
    }

    public MovementController getMovCont() {
        return movCont;
    }
}
