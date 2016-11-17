package gameEnginge;

import graphicEngine.VertexArrayObject;

public class Field extends GameObject{
	
	private VertexArrayObject vao;
	
	private float[] vertices = {
			0f, 0f, 0f,
			0f, 0f, 0f,
			0f, 0f, 0f,
			0f, 0f, 0f
	};
	
	private byte[] indices = {
			0, 1, 2,
			2, 3, 0
	};
	
	public Field(float[] vertices, byte[] indices){
		super(vertices, indices);
		this.vertices = vertices;
		this.indices = indices;
	}
	
	public float[] getVertices(){
		return vertices;
	}
}
