package view.controller;

import view.graphicEngine.VertexArrayObject;
//ToADD MAIN!!
import view.utils.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Field {

	public int vaoID;
	public int count;
    public Vector3f position;

    //TOADD
    protected int positionX;
    protected int positionY;
	private VertexArrayObject vao;
    private int goType;
	private static byte[] indices = {
			0, 1, 2,
			2, 3, 0
	};

	public Field(float[] vertices) {
		this.count = indices.length;
		vao = new VertexArrayObject(vertices, indices);
		this.vaoID = vao.getVaoID();
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

        //position.x = positionX * driver.gameG.getFieldSize();
        //System.out.println(positionX * driver.gameG.getFieldSize());
        //position.y = positionY * driver.gameG.getFieldSize();
        //System.out.println(positionY * driver.gameG.getFieldSize());

    }

    public int getGoType() {return goType; }

    public void update() {};

}
