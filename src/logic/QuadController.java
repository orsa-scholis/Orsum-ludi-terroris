package logic;

import java.util.ArrayList;

public class QuadController {
	private ArrayList<Quad> quads = new ArrayList<>();
	private int width;
	private int height;
	
	public QuadController(int width, int height) {
		super();
		
		this.width = width;
		this.height = height;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				quads.add(new Quad(new Index2D(j, i))); // x; y
			}
		}
	}
	
	public Quad quadAtIndex(Index2D index) {
		return quads.get((width * index.getY()) + index.getX());
	}

	public ArrayList<Quad> getQuads() {
		return quads;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
