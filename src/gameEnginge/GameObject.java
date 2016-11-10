package gameEnginge;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import graphicEngine.VertexArrayObject;

public class GameObject {

	public int vaoID;
	public int count;
	public float SIZE = 1.0f;

	private VertexArrayObject vao;

	public GameObject(float[] vertices, byte[] indices) {
		this.count = indices.length;
		vao = new VertexArrayObject(vertices, indices);
		this.vaoID = vao.getVaoID();
	}


	public void draw(){
		glBindVertexArray(this.vaoID);
		glEnableVertexAttribArray(0);
		glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
	}
}
