package gameEnginge;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.util.Observable;

import graphicEngine.VertexArrayObject;
import main.Driver;
import utils.Vector3f;

public class GameObject {

	public int vaoID;
	public int count;
    public Vector3f position;

	protected Driver driver;
    protected int positionX;
    protected int positionY;
	private VertexArrayObject vao;
    private int goType;

	public GameObject(float[] vertices, byte[] indices, int goType,Driver driver) {
		this.count = indices.length;
		vao = new VertexArrayObject(vertices, indices);
		this.vaoID = vao.getVaoID();
        this.driver = driver;
        positionX = 0;
        positionY = 0;
        this.goType = goType;
        position = new Vector3f();
	}


	public void draw(){
		glBindVertexArray(this.vaoID);
		glEnableVertexAttribArray(0);
		glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
	}


    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void updatePosition(int posX, int posY) {
        positionX = posX;
        positionY = posY;

        position.x = positionX * driver.game.getFieldSize();
        System.out.println(positionX * driver.game.getFieldSize());
        position.y = positionY * driver.game.getFieldSize();
        System.out.println(positionY * driver.game.getFieldSize());

    }

    public int getGoType() {
        return goType;
    }

    public void update() {

    }
}
