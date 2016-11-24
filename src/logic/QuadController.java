package logic;

import java.util.ArrayList;
import logic.graph.Point;

public class QuadController {
	private ArrayList<Quad> quads = new ArrayList<>();
	private int width;
	private int height;
	
	/**
	 * Constructor for the class QuadController
	 * @param width	The width of the field. It currently must be equal to the height to result in a square field
	 * @param height	The height of the field. It currently must be equal to the width to result in a square field
	 */
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
	
	public boolean testLineForObstacles(Point start, Point end) {
		Index2D startIndex = indexForPoint(start);
		Index2D endIndex = indexForPoint(end);
		boolean hasObstacle = false;
		
		System.out.println(startIndex);
		System.out.println(endIndex);
		if (startIndex.getY() == endIndex.getY()) {
			System.out.println("gleiche Zeile");
			// gleiche Zeile
			
			int yIndex = (startIndex.getY() * width); // Der Index des ersten Quads der Zeile
			for (int i = startIndex.getX(); i <= endIndex.getX(); i++) {
				if (quads.get(yIndex + i).isObstacle()) {
					hasObstacle = true;
					break;
				}
			}
		} else if (startIndex.getX() == endIndex.getX()) {
			System.out.println("gleiche Spalte");
			// gleiche Spalte
			
			for (int i = startIndex.getY(); i <= endIndex.getY(); i++) {
				if (quadAtIndex(new Index2D(startIndex.getX(), i)).isObstacle()) {
					hasObstacle = true;
					break;
				}
			}
		} else {
			System.out.println("Querlinie");
			// Querlinie
			
			// Immer bei dem tieferen X-Wert anfangen
			Index2D oldStartIndex = startIndex;
			startIndex = startIndex.getX() < endIndex.getX() ? startIndex : endIndex;
			endIndex = oldStartIndex.getX() > endIndex.getX() ? oldStartIndex : endIndex;
			
			LinearFunction function = new LinearFunction(
					new Point(start.getX() * width, start.getY() * height), 
					new Point(end.getX() * width, end.getY() * height)
			);
			System.out.println(function);
			for (int x = startIndex.getX(); x <= endIndex.getX(); x++) {
				int entryY = (int)Math.floor(function.getY((float)x));
				int exitY = (int)Math.floor(function.getY((float)(x + 1)));
				
				System.out.println("entry: " + entryY);
				System.out.println("exit: " + exitY);
				
				int add;
				if (function.getM() > 0) {
					// entryY < exitY
					
					add = 1;
					
					// Falls wir noch in der gleichen Zeile sind, in der sich der Start-Punkt befindet, ist der "entry" kleiner als die Y-Komponente
					// des Punkt selbst. 
					if (entryY < startIndex.getY()) {
						entryY = startIndex.getY();
					}
					
					// ditto
					if (exitY > endIndex.getY()) {
						exitY = endIndex.getY();
					}
				} else {
					// exitY < entryY
					
					add = -1;
					
					// Falls wir noch in der gleichen Zeile sind, in der sich der Start-Punkt befindet, ist der "entry" kleiner als die Y-Komponente
					// des Punkt selbst. 
					if (entryY > startIndex.getY()) {
						entryY = startIndex.getY();
					}
					
					// ditto
					if (exitY < endIndex.getY()) {
						exitY = endIndex.getY();
					}
				}
				
				for (int j = entryY; (add == 1 ? j <= exitY : j >= exitY); j += add) {
					Quad quad = quadAtIndex(new Index2D(x, j));
					if (quad.isObstacle()) {
						hasObstacle = true;
					}
					
					break;
				}
				
				// exit outer loop
				if (hasObstacle)
					break;
			}
		}
		
		return hasObstacle;
	}
	
	
	/* *** GETTERS *** */
	public Index2D indexForPoint(Point point) {
		return point.toIndex2d(width);
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
	
	public String toStringWithIndices() {
		String retString = "QuadController, field:";
		int row = 0;
		for (int y = height - 1; y >= 0; y--) {
			for (int x = 0; x < width; x++) {
				Quad quad = quadAtIndex(new Index2D(x, y));
				if (quad.getIndex().getY() != row) {
					retString += "\n"+y+" |";
					row = quad.getIndex().getY();
				}
				
				retString += quad.isObstacle() ? "x|" : " |";
			}
		}
		
		retString += "\n   ";
		for (int x = 0; x < width; x++) {
			retString += x + " ";
		}
		
		return retString + "\n";
	}
}
