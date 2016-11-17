package gameEnginge;

import graphicEngine.VertexArrayObject;
import utils.Vector3f;

public class Field extends GameObject{
	
	private VertexArrayObject vao;
	private boolean isObstacle;
    public Vector3f position;
	
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
	
	public Field(float[] vertices, byte[] indices, boolean isOb){
		super(vertices, indices);
		this.vertices = vertices;
		this.indices = indices;
		this.isObstacle = isOb;
        position = new Vector3f();
	}
	
	public float[] getVertices(){
		return vertices;
	}

	public boolean isObstacle() {
		return isObstacle;
	}
}
