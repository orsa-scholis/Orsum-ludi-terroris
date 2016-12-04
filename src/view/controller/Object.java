package view.controller;

import view.graphicEngine.VertexArrayObject;
//ToADD MAIN!!
import view.utils.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Object {

	public int vaoID;
	public int count;
    public Vector3f position;

	private VertexArrayObject vao;
	private static byte[] indices = {
			0, 1, 2,
			2, 3, 0
	};

	public Object(float[] vertices) {
		this.count = indices.length;
		vao = new VertexArrayObject(vertices, indices);
		this.vaoID = vao.getVaoID();
        position = new Vector3f();
	}


	public void draw(){
		glBindVertexArray(this.vaoID);
		glEnableVertexAttribArray(0);
		glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
	}
}
