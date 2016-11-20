package gameEnginge;

import main.Driver;
import utils.Vector3f;

public class Field extends GameObject{
	
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
	
	public Field(float[] vertices, byte[] indices, int isOb, Driver driver) {
        super(vertices, indices, isOb, driver);
        this.vertices = vertices;
        this.indices = indices;
        position = new Vector3f();
    }
}
