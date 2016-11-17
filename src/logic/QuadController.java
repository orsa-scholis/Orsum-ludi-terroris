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
	
	@Override
	public String toString() {
		String retString = "QuadController, field:";
		int row = 0;
		for (int y = height - 1; y >= 0; y--) {
			for (int x = 0; x < width; x++) {
				Quad quad = quadAtIndex(new Index2D(x, y));
				if (quad.getIndex().getY() != row) {
					retString += "\n|";
					row = quad.getIndex().getY();
				}
				
				retString += quad.isObstacle() ? "x|" : " |";
			}
		}
		
		return retString;
	}
}
